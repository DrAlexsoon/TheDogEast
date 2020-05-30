#GC模式
- yongGC 
> 选定所有年轻代里的Region。通过控制年轻代的region个数，即年轻代内存大小，来控制young GC的时间开销
>> 1.scan root
>> 2.通过RS(Remembered Set)找出被region引用的对象
>> 3.*复制算法*：拷贝存活对象到Survivor/Old Region，清理Eden Region空间
- mixedGC
> 选定所有年轻代里的Region，外加根据global concurrent marking统计得出收集收益高的若干老年代Region。
在用户指定的开销目标范围内尽可能选择收益高的老年代Region
##RS
- Collection Set（CSet）
> 记录了GC要收集的Region集合信息，Region可以是任意年代的（Young Gen Region总是在CSet内）
- Remembered Set
> 逻辑上说每个Region都有一个RSet，RSet记录了其他Region中的对象引用本Region中对象的关系，属于*points-into*结构
> - 1.实现部分垃圾收集时用于记录从非收集部分指向收集部分的指针的集合的抽象数据结构
> - 2.RSet的修改通过每次指针修改后执行一个write barrier（内存写屏障）实现
- CardTable(卡表)
> 一个比特位的集合，每一个比特位可以用来表示年老代的某一区域中的所有对象是否持有新生代对象的引用。*points-out*
> 每个Card 覆盖一定范围的Heap（一般为512Bytes）。G1的RSet是在Card Table的基础上实现的：每个Region会记录下别的Region有指向自己的指针，并标记这些指针分别在哪些Card的范围内。 这个RSet其实是一个Hash Table，Key是别的Region的起始地址，Value是一个集合，里面的元素是Card Table的Index。
> RSet究竟是怎么辅助GC的呢？在做YGC的时候，只需要选定young generation region的RSet作为根集，这些RSet记录了old->young的跨代引用，避免了扫描整个old generation。 而mixed gc的时候，old generation中记录了old->old的RSet，young->old的引用由扫描全部young generation region得到，这样也不用扫描全部old generation region。所以RSet的引入大大减少了GC的工作量。
- 初始标记（initial mark，STW）。它标记了从GC Root开始直接可达的对象。
- 根分区扫描（Root Region Scanning），找到依赖老年代对象的对象，必在下次YoungGC前完成。
- 并发标记（Concurrent Marking）。这个阶段从GC Root开始对heap中的对象标记，标记线程与应用程序线程并行执行，并且收集各个Region的存活对象信息（三色标记法，Cset）。
- 最终标记（Remark，STW）。标记那些在并发标记阶段发生变化的对象，将被回收（ SATB ）。
- 清除（Cleanup，STW）。清除空Region（没有存活对象的），加入到free list。
- 复制（Copying,STW)。复制存活对象
##SATB(Snapshot-At-The-Beginning)
GC开始时活着的对象的一个快照。它是通过Root Tracing得到的，作用是维持并发GC的正确性。 
三色标记算法，我们知道对象存在三种状态： 
* 白：对象没有被标记到，标记阶段结束后，会被当做垃圾回收掉。 
* 灰：对象被标记了，但是它的field还没有被标记或标记完。 
* 黑：对象被标记了，且它的所有field也被标记完了。
CMS: 只要在写屏障（write barrier）里发现要有一个白对象的引用被赋值到一个黑对象的字段里，那就把这个白对象变成灰色的。即插入的时候记录下来
G1: 在write barrier里把所有旧的引用所指向的对象都变成非白的.
#Pause Prediction Model
根据这个模型统计计算出来的历史数据来预测本次收集需要选择的Region数量，从而尽量满足用户设定的目标停顿时间