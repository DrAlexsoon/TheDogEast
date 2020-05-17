#CAP
##Consistency
> 写操作对后面的读操作可见
> 强一致性: 并发访问的情况下可以立即获取到更新
> 弱一致性: 部分感知不到
> 最终一致性: 一段时间后,一定可以感知更新
##Availability
> 任何一个没有发生故障的节点在有限的时间内返回结果
##Partition tolerance
> 部分节点宕机,无法通信,各分区可以保持分布式功能
#BASE



#Master-slave
#MNR
- Quorum & Vector Clock
> W+R > N 强一致性
> W+R <= N 最总一致性
>> N: 复制的节点书
>> W :成功写的节点数
>> R :成功读的节点数
#PAXOS
##RAFT
##ZAB(ZooKeeper Atomic Broadcast)  
#Gossip
#幂等设计[url][1]
##MVCC
##去重表
##悲观锁
## select + insert
##状态机幂等
##token
##对外提供接口的api如何保证幂等
[1] https://www.cnblogs.com/wxgblogs/p/6639272.html


###KAFKA
##ISR

in-sync-replica leader 维护一个基本保持同步的replica列表

Propagate message