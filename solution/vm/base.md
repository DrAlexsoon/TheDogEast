#对象的创建
> - 指令解析: new指令,检查指令的参数能否在类常量池中含有类的符号引用,并且已经被加载
> - 内存分配: 
>> - 1.指针碰撞(bump the pointer) 针对内存规整 -> serial/parNew 
>> - 2.空闲列表(free list) 在空闲列表(table card?)分配内存给对象 -> CMS
> - 并发安全:
>> - 1.cas加失败重试保证更新的原子性
>> - 2.TLAB (thread local allocation buffer) 线程分配内存在TLAB上分配,当需要分配新的TLAB才需要同步锁定
>>>[TLAB][1]
#对象内存布局
> - Header : [markOop.cpp]
> - InstanceData : 存储顺序受到存储策略影响(Ordinary Object Pointers), 父类变量受到(CompactFields)
> - Padding : 对齐填充
#对象访问定位
reference(局部变量表)
> - 语柄池: heap中分配内存,reference中存储对象句柄的地址,*包含了对象实例数据和类型数据*
>> - 对象被移动(GC)只需改变句柄中实例数据的指针
> - 直接指针: heap对象布局中如何放置访问类型数据的相关信息
>> - 速度更快,节省了一次定位的时间开销(Hot Spot)
#ERROR
##nativeStack VMStack
栈帧过大/VMStack过小都会引发OOM
##MethodArea
>>> string.intern 首次出现原则 s = sb().toString();s.intern() != s 
##DirectMemory
Unsafe.getUnsafe()(只有rt.jar才能使用) DirectByteBuffer分配内存未向操作系统申请,手动抛出异常.真正申请是unsafe.allocateMemory()



from:深入理解java虚拟机
[1]: https://mp.weixin.qq.com/s/Wws24Fhg1nH4dHvtcFYi2g