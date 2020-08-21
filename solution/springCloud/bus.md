#消息代理(message broker)
##Spring 事件驱动模型
- Event:
> applicationEvent 继承自 EventObject
- Listener
> applicationListener 继承自 EventListener
- Publisher
> applicationEventPublisher(定义了对事件的操作) & ApplicationEventMulticaster(定义了对listener的操作)
##SpringBus
- RemoteApplicationEvent
> - refreshEvent:刷新应用配置信息
> - ackEvent: 需指定了消息源头 <? extend event>
> - enviromentChangeEvent:动态变更每个节点的spring环境
> - sentEvent:用来发送信号表示一个远程事件在系统中被发送
- Listener
> - environment: 用来获取事件中的map,更新env
> - trace:通过注解 traceRepository对信息进行trace
- publisher
> - MessageChannel :定义了发送消息的抽象接口
> - serviceMatcher :判断消息的source/target是否是自己
> - channelBindprop:定义了消息绑定的属性
> - BusProp：定义了springBus的属性
- Endpoint 
>