#SAGA
Saga事务模型又叫做长时间运行的事务（Long-running-transaction）
每个Saga由一系列sub-transaction Ti 组成

#Ti和

#优点
- 一阶段提交本地事务
- 参与者可执行异步，提高吞吐
- 补偿服务易于实现
#缺点
 - **不保证隔离性** 因为没有准备阶段
    - 1.通过业务实现隔离
    - 2.事务顺序排列
 - **全局锁**
    - 1.热点数据
    - 2.回滚释放锁的时间
    - 3.死锁
##实现
 - seata[git][1]
##特点
> 优点
>> - 应用层基于SQL解析实现了自动补偿，从而最大程度的降低业务侵入性
>>- 将分布式事务中TC（事务协调者）独立部署，负责事务的注册、回滚 
>> - 通过全局锁实现了写隔离与读隔离
> 缺点
>> - 性能损耗
>>> 一条Update的SQL，则需要全局事务xid获取（与TC通讯）、before image（解析SQL，查询一次数据库）、after image（查询一次数据库）、insert undo log（写一次数据库）、before commit（与TC通讯，判断锁冲突），这些操作都需要一次远程通讯RPC，而且是同步的
>> - 性价比(2/8原则)
>> - 全局锁 

 
[1] https://github.com/seata/seata
