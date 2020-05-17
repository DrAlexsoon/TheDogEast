#
- 1.创建命令
- 2.执行命令，execute()|queue() 通过RxJava返回future对象
- 3.结果是否被缓存
>当前命令请求缓存启动，并且命中，缓存结果返回observable对象
- 4.断路器是否打开
>如果断路器打开,不会执行命令，执行fallback(8)
>如果关闭，检查是否有资源执行命令(5)
 - 5.线程池/请求队列/信号量是否占满
>资源不满足会执行fallback
>通过bulkhead Pattern 模式来隔离线程池
- 6.根据方法采取不同方式请求服务
> - hystrixCommand.run() 返回单一结果对象
> - hystrixObservable.construct(),返回obserable对象/onError通知
> - timeoutexecption会执行fallback并忽略最终返回
- 7.计算断路器的健康状态
>hystrix通过维护一组计数器来统计调用结果，通过统计数据来决定是否打开断路器
- 8.fallback处理
>通用相应结果不能依赖网络服务，如果依赖需要封装成command形成及联降级。
- 返回相应
>将结果直接返回/Observable的形式返回
##断路器
- isOpen
>1.断路器标示打开返回true 2.healthCounts中请(qps)在范围内2.错误百分比
- allowRequest
>1.判断参数中是否强制中断/关闭设置，
>2.通过休眠时间,断路器处于半开状态，允许请求，请求成功将开关设置为false
##bulkhead pattern
- 1.保护自身应用不会被依赖服务影线
- 2.降低新服务引用风险
- 3.容器级别的清理更重量(服务失效后恢复)

##Command
将来自客户端的请求封装成一个对象，使行为请求着，和行为执行者节藕
##RXJava
- Objservable (事件源)
- Subscriber
