#server
命令执行过程
> - 发送命令 client通过scoket发送指定协议的命令
> - 读取命令 1.读取命令到缓冲区，2.对命令进行解析[commadn table][1] 3.调用命令执行器
> - 调用执行器函数 
> - 命令回复
服务器启动执行顺序 
 - 初始化服务器
 - 载入配置文件
 - 初始化数据结构
 - 还原数据库
 - 执行循环事件

#client
client中flags 表示不同的角色

[1]: https://github.com/antirez/redis/blob/unstable/src/server.c#L182