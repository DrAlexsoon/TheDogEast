#键值的分区
- 根据键的范围 Hbase,RethinkDB
- hash 无法进行范围查询 cassandraz中通过复合索引，只有第一个Key作为散列依据，可以对其他键进行范围查找

##分区和索引
次级索引的问题在不能整齐的映射到分区
- 基于 **文档** 的分区 在这种方法下每个分区都是独立的，每个分区维护自己的二级索引(local index),通常被称为(scatter/gather) MongoDB,Raik[15],Cassandra[16],ES[17],SolrCloud[18],VoltDB[19]
- 基于 **关键词** 的 全局索引进行分区,通过关键词进行分区,
    - 优势:可以使读取更有效率
    - 缺点:因为单个文档写入可能会影响多个分区(跨分区事务)，通常对全局的二级索引是采用异步更新的 Amazon DynamDB
#分区再平衡
##平衡策略
- 固定数量的分区
- 动态分区 分区数量适应总数据量
- **按节点比例分区**:

##路由|服务发现
- 允许客户端联系任何节点
- 增加路由层，决定应该处理请求的节点
- 客户端知道分区和节点的分配

分布式系统依赖于协调服务，Liknedin Espresso使用Helix[31] 
Cassandra/riak/redis使用了gossip 在数据库节点增加了复杂性，但是避免了对外部协调的依赖
##并行查询
大规模并行处理(MMP)
Mysql中 condition push down/ multi range read()

