#XA协议
XA规范的目的是允许多个资源（如数据库，应用服务器，消息队列，等等）在同一事务中访问，这样可以使ACID属性跨越应用程序而保持有效。
此模型中定义了应用程序（Application，简称AP
全局事务管理器（Transaction Manager，简称TM）
本地资源管理器（Resource Manager,简称RM）
XA协议主要定义了TM和RM之间的接口规范。XA接口是双向接口，通过XA协议可以在TM与一个或多个RM之间建立通信桥梁

#2PC(The two-phase commit protocol)
##优点
- 对业务无入侵
##缺点:
 - 同步阻塞:所有参与节点都是事务阻塞型的
 - 单点问题: 协调者在提交阶段出现问题，其他参与者将会处于一直锁定事务资源的状态中，而无法继续完成事务操
 - 数据不一致：
    + 协调者向所有的参与者发送commit请求之后，发生了局部网络异常，或者是协调者在尚未发送完所有 commit请求之前自身发生了崩溃
    + 只有部分参与者收到commit，导致数据不一致
 - 容错性:
     二阶段提交协议没有设计较为完善的容错机制，任意一个节点是失败都会导致整个事务的失败。
        

#3PC(Three-phase commit)
##对比2pc
 - 引入超时机制
 - 在两个阶段中插入了一个准备阶段 ，CanCommit，PreCommit，doCommit
>>>CanCommit 等同于2PC第一阶段
>>>PreCommit 1.参与者收到后开始执行事务操作，并将Undo和Redo信息记录到事务日志中 2.参与者回复no,或者超时将发送abort
>>>doCommit 执行事务返回ack,避免了参与者和协调者通信超时
##缺点
 - 超时时间: 到这事务阻塞，或频繁失败
 - 数据不一致: pre->do消息丢失

#JTA(Java Transaction API) 
针对
- Atomikos[官网][1]
    
- bitronix


[1]:https://www.atomikos.com/