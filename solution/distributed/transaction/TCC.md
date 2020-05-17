#TCC(Try-Confirm-Cancel)

##对比XA(2PC)
 - TCC作用于应用上，2PC作用域DB上
 - TCC自定义操作粒度,降低锁冲突,提高吞吐量
 - 对业务侵入非常强，需要对confirm和cancel实现幂等返回
#实现
 - tcc-transaction
 - byte-TCC
 - easy-transaction
 - seata
 
 
##seata[git][1]
TCC
> - 允许空回滚 try丢失导致cancel触发
> - 防悬挂控制, cancel比try更早执行
> - 幂等控制
AT
 - 一阶段：业务数据和回滚日志记录在同一个本地事务中提交，释放本地锁和连接资源。
 - 二阶段：
    - 提交异步化，非常快速地完成。
    - 回滚通过一阶段的回滚日志进行反向补偿。
##servicecomb
#TODO
- ByteTCC
- tx-lcn
- EasyTransaction

[1] https://github.com/seata/seata
[2] https://github.com/apache/servicecomb-pack
[3] https://github.com/QNJR-GROUP/EasyTransaction
