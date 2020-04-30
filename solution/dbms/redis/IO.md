#IO模型[source][1]
evport>epoll>kqueue=select
事件驱动模型
EventLoop

>文件事件
>>基于reactor模式的网络通信，基于socket的抽象，当scoket 状态是accept，write，read时产生相应的事件 AE_READEBLE,AE_WRITEABLE
>文件事件处理器
>> - 链接应答处理器 acceptTcpHandler [source][2] 与服务器监听scoket的AE_READEBLE事件绑定
>> - 命令请求处理器 readQueryFromClient[source][3] createClient后，会将客户端AE_READABLE事件与命令处理器绑定
>> - 命令回复处理器 sendReplyToClient[source][4] 当服务器有命令返回给，会将AE_WRITEABLE事件绑定

>时间事件[source][1]
>定时事件，周期事件
>事件处理器实例[source][5]serverCron函数
事件处理器和调度[source impl ][6] aeProcessEvents函数
> - 由 aeApipoll最大阻塞时间最接近当前时间决定，避免了繁忙/长时间阻塞
> - 文件事件处理完毕未有，时间时间，继续等待文件事件
> - 对文件事件和时间事件都是，*同步的 有序的 原子的*执行
> - 因为文件执行顺序导致：时间事件的执行时间会略晚于规定时间 

[1]: https://github.com/antirez/redis/blob/unstable/src/ae.c
[2]: https://github.com/antirez/redis/blob/unstable/src/networking.c#L944
[3]: https://github.com/antirez/redis/blob/unstable/src/networking.c#L1858
[4]: https://github.com/antirez/redis/blob/unstable/src/networking.c#L1358
[5]: https://github.com/antirez/redis/blob/unstable/src/server.c#l845
[6]: https://github.com/antirez/redis/blob/unstable/src/ae.c#L375