#SAGA
Saga事务模型又叫做长时间运行的事务（Long-running-transaction）
每个Saga由一系列sub-transaction Ti 组成

#Ti和

#优点
-一阶段提交本地事务
- 参与者可执行异步，提高吞吐
- 补偿服务易于实现
#缺点
 - **不保证隔离性** 因为没有准备阶段
    - 1.通过业务实现隔离
    - 2.事务顺序排列
    
##实现
 - seata[git][1]
 - easy
 
[1] https://github.com/seata/seata
