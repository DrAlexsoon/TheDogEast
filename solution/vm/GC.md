##Reference Counter
> - Redis
##ReachAbility Analysis
> - VMStack 局部变量表
> - NativeStack 引用的对象
> - MethodArea 静态属性引用的对象
> - MethodArea 常量引用的对象
##Reference(TODO)

#MethodArea
> - 常量: 
> - 类: 
>> 1.实例被回收2.类加载器被回收3.class没有在任何地方引用,也不会通过反射访问
#
> - Mark-Sweep
> - Copy 分配担保(handle Promotion)
> - Mark-Compact
> - Generational-Collection
#GCRoot
准确试GC,使用OopMap,在类加载完成,把对象偏移量是什么数据类型计算出来.JIT也会记录
##SafePoint
HotPot没有为每条指令生成OopMap,在特定的位置记录(safePoint),GC只能发生在safePoint后
线程暂停在最近的安全点
> - 抢占式(Preempitive Suspension) 不需要线程配合,暂停所有线程,让非安全点的线程跑到安全点
> - 主动中断(Voluntary Suspension) 不直接操作线程,设置表示,线程轮训表示位进行配置
##SafePoint region
当线程处于Block/Wainting不会去等待线程执行.在一段引用关系不会发生变化的区域叫做safeRegion
#
> - Serial
> - ParNew
> - Parallel Scavenge
>> 1.最大暂停时间(MaxGCpauseMillis) 2.吞吐量大小(GCTimeRaito) AdapiveSizePolicy
> - Serial (MS)
> - Parallel old (MS)
> - CMS
>> - 1.initial mark 2. concurrent mark 3.remark 4.concurrent sweep
>> - 1.浮动垃圾 (Concurrent Mode File)(CMSInitiatingOccupancyFrction) 2.碎片整理(CMSFullGCSbeforeCompaction)3.cpu资源敏感会影响用户线程/使GC时间延长(交替运行)
> - G1