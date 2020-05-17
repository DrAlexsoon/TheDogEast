
##旧版本复制功能实现
>>1.sync
>>> - 1.slave重连需要dump所有数据
>>> - 2.master执行bgsave,发送大量数据,slave载入慢
>>2.commahnd propagate(命令传播)
##新版复制
>>适用psync代替sync命令，具有完整同步(full resynchronization)和部分同步(partial resynchronization)
>>> - **master复制偏移量(replication offset)从复制偏移量** 双方分别维护一个偏移缓冲区
>>> - **master复制挤压缓冲区** master 维护一个FIFO队列 command同时写入缓冲区
>>> - 服务器运行 ：slave重链后会携带master的id
>>> - 实现:slave发送 psync ? (-1 ==full) || psync master_id offset
>>实现
>>> - 1.设置master ip:port
>>> - 2.建立scoket
>>> - 3.发送ping
>>> - 4.auth
>>> - 5.发送端口信息
>>> - 6.psync
>>> - 7.command propagate
##心跳检测（command propagate）阶段
>>1.主从服务器通过 **REPLCONF** **ACK**命令来检测网络连接是否正常
>>2.通过 min-slaves-to-write(从服务器数量)和 min-slave-max-lag(从服务器的延迟)
>>3.检查命令丢失，master 会根据slave提供的offset补全数据

#Sentinel
是redis的Availablitily解决方案，由一个过多个sentinel组成可以监控任意master,slave。并在master下线后从slave中选出代替
##初始化[source][1]
>>redis-sentinel == redis-server --sentinel
>> - sentinel.masters 记录了所有被监控的服务器的信息[instance][2]
>> - **建立master的网络连接**
>>> 1.命令连接(向主服务器发送命令，并接收回复)
>>> 2.订阅连接()
>>> **redis发布订阅发送的信息不会保存在服务器中，为了不丢失任何信息sentinel消息**
>> - 获取master信息（10/s **INFO** 命令）
>>> 1.主服务器的信息,run_id域下的服务器运行run_id,role 下服务器角色
>>> 2.获取slaves信息用于更新slaves字典
>> - 获取slave信息
>>> 1.获取从服务器信息run_id ,role
>>> 2.主从服务信息 master_host,master_port ,master_link_status, slave_priority, slave_repl_offset
>> - 接收来自主从服务器信息
>>> 1.更新sentinel字典
>>> 2.创建其他sentinel命令连接
>> - **检测主观下线**
>>> 1.每秒向master,slave ,sentinel发送ping命令 ，回复[PONG,LOADING,MASTERDOWN]
>>> 2.通过配置文件down-after-millseconds判断是否主管下线 **sentinel可以设置不同的值**
>>- 检测客观下线
>>> sentinel会向其他sentinel询问，当接受足够多下线数量
>>> 1.发送is-master-down-by-addr 询问其他sentinel
>>> 2.接收is-master-down-by-addr 检查目标状态
>>> 3.接收回复 并设置客观下线
>> - 选举leader-sentinel(raft)
>>> 1.发现服务器客观下线的服务器向其他sentinel申请未局部leader
>>> 2.接收回复检查leader_epoch是否相同
>>> 3.半数以上设置为局部leader
>> - 故障迁移 
>>> 1.在已经下线的服务器中挑选一个为master服务器
>>>> exclude 主管下线，最近5s没有回复info
>>>> 通过优先级，复制偏移量，repoffset最大的为主服务器
>>> 2.所有从服务器复制主服务器
>>> 3.将已下线的服务器设置为从


[1]: https://github.com/antirez/redis/blob/unstable/src/sentinel.c#L242 
[2]: https://github.com/antirez/redis/blob/unstable/src/sentinel.c#L172
