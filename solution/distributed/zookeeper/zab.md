#ZAB协议(Zookeeper Atomic Broadcast)
 - 所有的事务请求必须由一个全局唯一的服务器来协调处理
 - 向所有 Follower 节点发送数据广播请求

Zab 协议包括两种基本的模式：崩溃恢复 和 消息广播