#多线程环境解决方案及原理
##ReentrantLock
ReentrantLock主要利用CAS+CLH队列来实现。它支持公平锁和非公平锁，两者的实现类似。
- CAS：Compare and Swap，比较并交换。CAS有3个操作数：内存值V、预期值A、要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做。该操作是一个原子操作，被广泛的应用在Java的底层实现中。在Java中，CAS主要是由sun.misc.Unsafe这个类通过JNI调用CPU底层指令实现。
- CLH队列：带头结点的双向非循环链表
###基本实现：
先通过CAS尝试获取锁。如果此时已经有线程占据了锁，那就加入CLH队列并且被挂起。当锁被释放之后，排在CLH队列队首的线程会被唤醒，然后CAS再次尝试获取锁。在这个时候，如果：
 - 非公平锁：如果同时还有另一个线程进来尝试获取，那么有可能会让这个线程抢先获取；
 - 公平锁：如果同时还有另一个线程进来尝试获取，当它发现自己不是在队首的话，就会排到队尾，由队首的线程获取到锁
#synchronized
- synchronized语句：当源代码被编译成字节码的时候，会在同步块的入口位置和退出位置分别插入monitorenter和monitorexit字节码指令;
- synchronized方法：在Class文件的方法表中将该方法的access_flags字段中的synchronized标志位置1。这个在specification中没有明确说明
```text
 monitorenter
 The objectref must be of type reference.
 Each object is associated with a monitor. A monitor is locked if and only if it has an owner. The thread that executes monitorenter attempts to gain ownership of the monitor associated with objectref, as follows:
 
 If the entry count of the monitor associated with objectref is zero, the thread enters the monitor and sets its entry count to one. The thread is then the owner of the monitor.
 If the thread already owns the monitor associated with objectref, it reenters the monitor, incrementing its entry count.
 If another thread already owns the monitor associated with objectref, the thread blocks until the monitor's entry count is zero, then tries again to gain ownership.
 ```
 
*每个对象都有一个锁，也就是监视器（monitor）。当monitor被占有时就表示它被锁定。线程执行monitorenter指令时尝试获取对象所对应的monitor的所有权，过程如下：
     - 如果monitor的进入数为0，则该线程进入monitor，然后将进入数设置为1，该线程即为monitor的所有者;
     - 如果线程已经拥有了该monitor，只是重新进入，则进入monitor的进入数加1;
     - 如果其他线程已经占用了monitor，则该线程进入阻塞状态，直到monitor的进入数为0，再重新尝试获取monitor的所有权。*
```text
monitorexit
  The objectref must be of type reference.
  The thread that executes monitorexit must be the owner of the monitor associated with the instance referenced by objectref.
  The thread decrements the entry count of the monitor associated with objectref. If as a result the value of the entry count is zero, the thread exits the monitor and is no longer its owner. Other threads that are blocking to enter the monitor are allowed to attempt to do so.
```
  
*执行monitorexit的线程必须是相应的monitor的所有者。
指令执行时，monitor的进入数减1，如果减1后进入数为0，那线程退出monitor，不再是这个monitor的所有者。其他被这个monitor阻塞的线程可以尝试去获取这个monitor的所有权。
Mutex Lock*
#多进程的解决方案
#Semaphores
信号量在POSIX标准下有两种，分别为有名信号量和无名信号量。无名信号量通常保存在共享内存中，而有名信号量是与一个特定的文件名称相关联。信号量是一个整数变量，有计数信号量和二值信号量两种。对信号量的操作，主要是P操作（wait）和V操作（signal）。
- P操作：先检查信号量的大小，若值大于零，则将信号量减1，同时进程获得共享资源的访问权限，继续执行；若小于或者等于零，则该进程被阻塞后，进入等待队列。
- V操作：该操作将信号量的值加1，如果有进程阻塞着等待该信号量，那么其中一个进程将被唤醒。