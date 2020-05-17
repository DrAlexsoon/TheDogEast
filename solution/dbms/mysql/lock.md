#INNODB
##type
- record lock
> - S lock
> - X lock
- table lock
> - IS
> - IX
##算法
> - record lock
> - gap lock
> - next key lock
唯一索引锁定record，非唯一索引锁定区间，非索引锁定全表（RR）

##MVCC (multi version concurrency control )	
通过 undo 实现
RC 总是读取被锁定行最新的快照数据
RR 总是读取事务开始时的版本
##一致性锁定读
select ... lock in share mode
select ... for update


通过上锁使其他操作阻塞,通过版本控制解决事务的隔离级别 

> - select...from 一致性读取,不锁定行, SERIALIZABLE隔离级别下,shareLock,使用唯一索引搜索的仅锁定索引
> - select...for update ,select on share mode,DDL
>> - 唯一索引仅锁定索引
>> - 其他条件查询和非唯一索引，使用gap lock,next key lock,



#Transaction Scheduling
8.0.20之前使用FIFO队列
Contention-Aware Transaction Scheduling (CATS)算法
给阻塞更多的事务分配更大的权重

