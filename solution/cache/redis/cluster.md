#NODE
redis cluster由多个独立的node组成
 - **cluster meet \<ip\> \<port\>**  于目标节点进行handshake
 - **cluster node** 查看节点信息
> 1. 启动节点 [节点信息][1] [集群节点信息][2]
>> - 通过配置cluster-enabled判断是否启动集群模式
>> - 每个节点保存一个clusterState，记录当前视角下集群状态。
> 2.cluster实现
>> - server为新加入的节点创建clusterNode并加入到clusterstat_nodes
>> - servre根据ip和port发送message
>> - client为server创建clusterNode加入到nodes字典中
>> - 通过ping/pong建立双向通信
>> - 通过GOSSIP协议,将client的信息传播给其他节点，进行handshake
#SLOT指派
集群被分为 **2^14 solt**，所有的slot被处理，处于上线状态状态。
- 记录slot指派信息[clusterNode][3]
>> slots是二进制数据，数组长度是2^14/8 = 2048 
>> 通过二进制来检查是否负责该slot
- 传播节点slot指派信息
>> clusterState[source][4]
**clusterNode,clusterState分别保存slot原因**
1.将某个节点的slot信息派送给其他节点，只需要发送当前节点slot
2.clusterstate中记录了所有slot的指派信息
**cluster addslots**进行slot分配
可以将任意数量的slot指派给某个node(oneline)
1.对目标节点发送cluster setslot \<slot\> importing \<source id\> 倒入源节点键值对
    更新clusterState.importing_slots_from数组
2.对原节点发送 cluster setslot \<slot\> migrating \<target id\> 
    更新clusterState.migrating_slots_to数组
3.向源节点获取数据slot的keys cluster getkeysInSlot，对于每个key进行数据迁移
4.向集群中发送 cluster setslot \<slot\> node \<source id\>
**ASK 错误**
源节点在库查找，未查找到通过migrating_slots_to查看会否在迁移，并返回ASK错误
**ASKING 命令**
通过ASK错误转向目标节点时，先发送ASKING命令，目标节点会额外检查importing_slots_from数组中的数据否则返回MOVED错误
#命令
接收的node会计算slot是否指向自己
- 执行命令
- 返回 **moved错误**，redirect到正确的节点
1.计算slot 通过 **CRC16(key) & 16384 计算slot**
2.判断slot是否是自己负责
3.发送MOVED \<ip\> \<port\>
#复制和故障迁移
redis中master负责处理slot，slave负责复制数据
> - 设置从节点 更新clusterState.slaveof 通过slaveof进行复制
> - 故障检测 通过消息更新节点信息，online/pfail/fail，半
> - 故障迁移 类似于sentinel leader选举，
#消息
- MEET
- PING
- PONG
- FAIL
- PUBLISH

[1]: https://github.com/antirez/redis/blob/unstable/src/cluster.h#L112
[2]: https://github.com/antirez/redis/blob/unstable/src/cluster.h#L135
[3]: https://github.com/antirez/redis/blob/unstable/src/cluster.h#L117
[4]: https://github.com/antirez/redis/blob/unstable/src/cluster.h#L148