#对象头OOP
```java
//  64 bits:
//  --------
//  unused:25 hash:31 -->| unused:1   age:4    biased_lock:1 lock:2 (normal object)
//  JavaThread*:54 epoch:2 unused:1   age:4    biased_lock:1 lock:2 (biased object)
//  PromotedObject*:61 --------------------->| promo_bits:3 ----->| (CMS promoted object)
//  size:64 ----------------------------------------------------->| (CMS free block)
//
//  unused:25 hash:31 -->| cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && normal object)
//  JavaThread*:54 epoch:2 cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && biased object)
//  narrowOop:32 unused:24 cms_free:1 unused:4 promo_bits:3 ----->| (COOPs && CMS promoted object)
//  unused:21 size:35 -->| cms_free:1 unused:7 ------------------>| (COOPs && CMS free block)
```

##偏向锁(01)
###加锁
- 1.检测Mark Word是否为可偏向状态，即是否为偏向锁1，锁标识位为01；
- 2.若为可偏向状态，则测试线程ID是否为当前线程ID，如果是，则执行步骤（5），否则执行步骤（3）；
- 3.如果线程ID不为当前线程ID，则通过CAS操作竞争锁，竞争成功，则将Mark Word的线程ID替换为当前线程ID，否则执行线程（4）
- 4.通过CAS竞争锁失败，证明当前存在多线程竞争情况，当到达全局安全点，获得偏向锁的线程被挂起，偏向锁升级为轻量级锁，然后被阻塞在安全点的线程继续往下执行同步代码块；
- 5.执行同步代码块
###释放锁
- 当一个线程已经持有偏向锁，而另外一个线程尝试竞争偏向锁时，CAS 替换 ThreadID 操作失败，则开始撤销偏向锁。偏向锁的撤销，需要等待原持有偏向锁的线程到达全局安全点（在这个时间点上没有字节码正在执行），暂停该线程，并检查其状态
- 如果原持有偏向锁的线程不处于活动状态或已退出同步代码块，则该线程释放锁。将对象头设置为无锁状态（锁标志位为'01'，是否偏向标志位为'0'）
- 如果原持有偏向锁的线程未退出同步代码块，则升级为轻量级锁（锁标志位为'00'）
##轻量级锁
###加锁
- 1.线程在进入到同步代码块的时候，JVM 会先在当前线程的栈帧中建立一个名为锁记录（Lock Record）的空间，用于存储锁对象当前 Mark Word 的拷贝（官方称为 Displaced Mark Word），owner 指针指向对象的 Mark Word。
- 2.JVM 使用 CAS 操作尝试将对象头中的 Mark Word 更新为指向 Lock Record 的指针。如果更新成功，则执行步骤3；更新失败，则执行步骤4
- 3.如果更新成功，那么这个线程就拥有了该对象的锁，对象的 Mark Word 的锁状态为轻量级锁（标志位转变为'00'）
- 4.如果更新失败，JVM 首先检查对象的 Mark Word 是否指向当前线程的栈帧
  如果是，就说明当前线程已经拥有了该对象的锁，那就可以直接进入同步代码块继续执行
  如果不是，就说明这个锁对象已经被其他的线程抢占了，当前线程会尝试自旋一定次数来获取锁。如果自旋一定次数 CAS 操作仍没有成功，那么轻量级锁就要升级为重量级锁（锁的标志位转变为'10'），Mark Word 中存储的就是指向重量级锁的指针，后面等待锁的线程也就进入阻塞状态
###释放锁
- 通过 CAS 操作用线程中复制的 Displaced Mark Word 中的数据替换对象当前的 Mark Word
- 如果替换成功，整个同步过程就完成了
- 如果替换失败，说明有其他线程尝试过获取该锁，那就在释放锁的同时，唤醒被挂起的线程
##重量级锁
monitor 监视器锁本质上是依赖操作系统的 Mutex Lock 互斥量 来实现的，我们一般称之为重量级锁。
因为 OS 实现线程间的切换需要从用户态转换到核心态，这个转换过程成本较高，耗时相对较长，因此 synchronized 效率会比较低。
