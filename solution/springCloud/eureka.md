#Provider
- 注册register
>通过table存储元信息<serviceName,serviceInstance>
- 同步sync
>通过转发sync同步
- 续约renew
>通过心跳防止server剔除
- 下线
>正常关闭触发剔除请求
#Consumer
 - 获取服务readOnly
>server维护一直只读清单，schdulded更行
 - 调用
>region&zone
#register
- 失效剔除
- 自我保护
>统计心跳失败比例是否在15分钟内低于85%，server会尽可能保存注册信息
