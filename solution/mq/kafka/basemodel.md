#Server
##broker
##SocketServer
    - Acceptor 
    1.监听服务器端口,并生成serverChannel,注册监听ACCEPT事件,
    2.RundRobin,把ScoketChannel注册并绑定到对应的Processor
    3.
    - Processor
      ArrayBlockQueue保存了newConnection
      LinkedBlockingDeque 响应队列responseQueue
      ArrayBlockingQueue 保存了请求队列
      1.注册并绑定到Processor中的selector,并监听SelectionKey.OP_READ事件
      2.消费responseQueue,并生成对应的事件,remove Selector中空闲/close的channel,设置应答状态和应答内容
      3.如果有读取事件,生成Req入队,OP_WRITE事件通过channel写回response,Close释放对应资源
    - RequestChannel 通过channel和ApiControl解耦
`KafkaController 解耦`
##KafkaApis
###LogManager
    pool[TopicPartition,Log] 通过TopicPartition来索引Log
    - 'kafka-recovery-point-checkpoint' 定时初始化log,用来保存TopicPartition的offset
    - 'kafka-log-start-offset-checkpoint' 定时将logSemgent的offset更新持久化到
    - 'kafka-log-retention' 定时删除日志
    - 'kafka-log-flusher' 定时将脏的segment刷新到磁盘持久化
    - 'kafka-delete-logs' 删除被标记的log,删除完成后重新delay
    
    - cleaner partition中segment压缩 ？将多条相同
####Log
    ConcurrentSkipListMap[long,Segment]
####Segment
    Iter<Record> Record[offest]
    OffsetIndex .log中的相对偏移量
    BaseOffset .log中的起始偏移量
    timeIndex ?
    txnIndex ?[TransactionIndex]
    indexIntervalBytes 多少字节后写入索引[稀疏索引]
    - 通过skipMap查找 segment,通过二分查找找到index中的偏移量,顺序读取
###ReplicaManager
    ReplicaFetcherManager 线程池定时fetch ReplicData
    - 'isr-expiration' Leader驱逐ISR中 时间和数量限制的不同步的Replic
    - 'isr-change-propagation' 通过zk传播Isr信息
###SocketServer
###GroupCoordinator
###TransactionCoordinator
###KafkaController
###TokenManager


##DataPlaneHandler&ControlPlaneHandler
    聚合KafkaApi和Socket.requestChannel
    - DataPlaneHandler 对ScoketServer.requestChannel中的事件进行消费,handlerPool.size = config.numIoThreads
    - ControlPlaneHandler 通过配置文件对controlPlane事件进行处理 handlerPool.size = 1

- scheduler
    - cleanLog
    - flushDirtyLogs  

- ReplicaManager
- KafkaController
- SocketServer
