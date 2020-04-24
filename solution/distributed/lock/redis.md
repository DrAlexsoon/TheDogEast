#实现原理
- `lock`
    SET resource_name my_random_value [EX seconds] [PX milliseconds] [NX|XX]
    守护线程续租
- `unlock`
    Lua:
    `if redis.get("resource_name") == " my_random_value"
     return redis.del("resource_name")
     else 
     return 0 `
#开源实现
##redisson
    `https://github.com/redisson/redisson`
#缺点
    redis 主从复制导致，导致锁信息丢失
    不支持阻塞
    