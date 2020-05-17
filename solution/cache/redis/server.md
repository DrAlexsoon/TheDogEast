#数据库[source][1]
>redisServer.db 数组
>client 通过修改目标数据库指针访问不同库
>dict 和expires两个主要字典
>>dict 中key总是string，value 是任意其他类型
>>expires key指向某个键，值为过期时间unix时间戳
>#过期方式
>>1.定时删除，内存友好，cpu不友好，创建定时器list
>>2.惰性删除，cpu友好，内存不友好，只有访问的建过期才删除
>>3.每隔一段时间主动查找并删除 default 10/s
>>> - 贪心策略 :随机挑选20个，删除其中的过期key,超过1/4重复执行
>>> - 扫描上线25 ,为保证不过度
>>>slave，通过master的del删除过期，key中心化保证数据一致性
>#LRU策略
>> - noeviction default
>> - volatile-lru
>> - volatile-ttl
>> - **volatile-lfu**
>> - volatile-random
>> - allkeys-lru
>> - allkeys-random
>> - **allkeys-lfu**
>>近似LRU算法（LRU算法需要消耗大量内存），随机采样，3.0中增加了淘汰池？每次随机出来的key会和淘汰池中的key做比对，淘汰最老
>> 4.0 新增**LFU**
>#惰性删除（lazyfree）
>>redis中删除大key耗时 4.0中引入了 *unlink* *flushdb async* *flushall async*

#TODO
redis把处理网络收发和执行命令这些操作都放在了主工作线程，还有许多bio后台线程
处理关闭文件和刷盘这些比较重的IO，lazyfree

```c

typedef struct redisDb {
    dict *dict;                 /* The keyspace for this DB */
    dict *expires;              /* Timeout of keys with a timeout set */
    dict *blocking_keys;        /* Keys with clients waiting for data (BLPOP)*/
    dict *ready_keys;           /* Blocked keys that received a PUSH */
    dict *watched_keys;         /* WATCHED keys for MULTI/EXEC CAS */
    int id;                     /* Database ID */
    long long avg_ttl;          /* Average TTL, just for stats */
    unsigned long expires_cursor; /* Cursor of the active expire cycle. */
    list *defrag_later;         /* List of key names to attempt to defrag one by one, gradually. */
} redisDb;

```
