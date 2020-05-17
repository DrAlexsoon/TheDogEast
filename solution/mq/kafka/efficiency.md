#持久化(Persistence)
> - 顺序写磁盘，大于随机写内存[][1]
> - appendOnly 只会删除segment O(logN) -> O(1)
> 充分利用page cache 
>> - I/O Scheduler连续小块的写组成大块的物理写
>> - I/O Scheduler
> - 充分利用空闲内存(非JVM内存)
>> - 对象的内存开销非常高，通常使存储的数据大小增加一倍（或更糟）对应page cache
>> - 如果使用heap 会给Gc带来负担
>> - 读操作可以直接在pageCache进行，jvmCache会失效

#零拷贝(zero copy)
传统
file.read(fileDesc,buf,len)
Socket.sent(socket,buf,len)
4次copy 4次上下文切换
> *kernel* read systemcall DMA from disk -> read buffer
> *k* read buffer -> application buffer
> application buffer -> scoket buffer
> scoket buffer -> *MDA*nic buff 
NIO
transferTo/transFrom 系统调用sendfile
> disk -> read buffer
> read buffer -> NIC buffer
2次上下文切换一次系统调用
#BATCH&ZIP
>批量，减少overhead
>Snappy ,Gzip,Lz4

#Producer
- Load balancing
> 无需要中间层routing broker返回所有metadata
- Asynchronous send
#Consumer
- pull vs push
>> - 消费者根据消费能力调整消费策略
>> - 消费者空轮寻
- partition
实现并行处理，水平扩展
>partition是并行的最小单位
>不同partition可以位于不同的broker，充分利用多资源
>同一个broker，不同的partition可以用不同directory利用多个disk diver
#Replication
##*多数表决(majority vote) vs ISR(in sync replicas)*
可用性，和一致性的动态平衡（A,C）
> - zab raft,容忍n个故障需要2n+1个replica
> - leader只会从ISR中选出,只有收到所有ISR ack才可以提交 
##leader 选举
- 等待ISR中副本
- 选择第一个链接的副本(可以是非ISR)
##可用性，和一致性A,C）
#Log Compaction(日志压缩)


[1] https://deliveryimages.acm.org/10.1145/1570000/1563874/jacobs3.jpg
