#BASE
    -string 字符数组
    -list quicklist->(ziplist,linkedList)
    -hash 渐进式rehash
    -zset 跳跃链表


#MODULE
    ##Bloomfilter

    ##red-Cell
     -GCRA
    ##Sessiongate
#PIPELINING
    pipelineClient
    客户端缓存，
#MULTI
    -https://juejin.im/post/5b42e025f265da0fa332d4dc
    服务端缓存,保证原子性
#memory-optimization
    -聚合数据类型压缩 redis.conf
    -使用32位
    -get/setRange|get/setBIT
    -小hash 每个hash按照
    -maxmemory设置
#EXPIRE
    -主动过期(用户访问)和被动过期(20/s删除已经过期的key)

