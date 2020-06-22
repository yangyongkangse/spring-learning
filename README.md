## spring-boot-learning
  * Redis Cache
  * MongoDB
  * RabbitMQ
    1. DirectExchange 根据route key 直接找到队列 精确匹配
    1. Topic Exchange 根据route key 匹配队列 通配符匹配
    1. Fanout Exchange 不处理route key 全网发送,所有绑定的队列都发送
  * Docker
  * Spring Security JWT 安全
## spring-boot-admin
  * Spring Boot Admin 监控
## spring-nacos-config
  * Nacos Config Center
## spring-boot-order spring-boot-pay
  * 采用@LoadBalanced注解及RestTemplate进行微服务之间调用
## spring-nacos-openfeign
  * 采用openfeign进行微服务之间的调用
##  spring-sentinel-dashboard
  * 启用本地Sentinel dashboard  v.1.7.2
  * 进行Sentinel规则持久化
    * 流控配置说明:
       1. resource:资源名称；
       2. limitApp:来源应用 
          * default:表示不区分调用者,来自任何调用者的请求都将进行限流统计
          * {some_origin_name}:表示针对特定的调用者,只有来自这个调用者的请求才会进行流量控制
          * other:表示除 {some_origin_name} 以外的其余调用方的流量进行流量控制
       3. grade:阀值类型,0表示线程数,1表示QPS
       4. count:单机阀值；
       5. strategy:流控模式,0表示直接,1表示关联,2表示链路；
       6. controlBehavior:流控效果,0表示快速失败,1表示Warm Up,2表示排队等待；
       7. clusterMode:是否集群
    * 网关限流配置说明
       1. resource:资源名称,可以是网关中的 route 名称或者用户自定义的 API 分组名称.
       2. resourceMode:规则是针对 API Gateway 的 route(RESOURCE_MODE_ROUTE_ID)还是用户在 Sentinel 中定义的 API 分组(RESOURCE_MODE_CUSTOM_API_NAME),默认是 route.
       3. grade:限流指标维度,同限流规则的 grade 字段.
       4. count:限流阈值
       5. intervalSec:统计时间窗口,单位是秒,默认是 1 秒.
       6. controlBehavior:流量整形的控制效果,同限流规则的 controlBehavior 字段,目前支持快速失败和匀速排队两种模式,默认是快速失败.
       7. burst:应对突发请求时额外允许的请求数目.
       8. maxQueueingTimeoutMs:匀速排队模式下的最长排队时间,单位是毫秒,仅在匀速排队模式下生效.
       9. paramItem:参数限流配置.若不提供,则代表不针对参数进行限流,该网关规则将会被转换成普通流控规则；否则会转换成热点规则.其中的字段:
       10. parseStrategy:从请求中提取参数的策略,目前支持提取来源 IP(PARAM_PARSE_STRATEGY_CLIENT_IP)、Host(PARAM_PARSE_STRATEGY_HOST)、任意 Header(PARAM_PARSE_STRATEGY_HEADER)和任意 URL 参数(PARAM_PARSE_STRATEGY_URL_PARAM)四种模式.
       11. fieldName:若提取策略选择 Header 模式或 URL 参数模式,则需要指定对应的 header 名称或 URL 参数名称.
       12. pattern:参数值的匹配模式,只有匹配该模式的请求属性值会纳入统计和流控；若为空则统计该请求属性的所有值.(1.6.2 版本开始支持)
       13. matchStrategy:参数值的匹配策略,目前支持精确匹配(PARAM_MATCH_STRATEGY_EXACT)、子串匹配(PARAM_MATCH_STRATEGY_CONTAINS)和正则匹配(PARAM_MATCH_STRATEGY_REGEX).(1.6.2 版本开始支持)
    * 热点限流配置说明
       1. resource:资源名,必填	
       2. count:限流阈值,必填	
       3. grade:限流模式 同限流规则的 grade 字段
       4. durationInSec:统计窗口时间长度(单位为秒)
       5. controlBehavior:流控效果(支持快速失败和匀速排队模式),1.6.0 版本开始支持
       6. maxQueueingTimeMs:最大排队等待时长(仅在匀速排队模式生效),1.6.0 版本开始支持
       7. paramIdx:热点参数的索引,必填,对应 SphU.entry(xxx, args) 中的参数索引位置	
       8. paramFlowItemList:参数例外项,可以针对指定的参数值单独设置限流阈值,不受前面 count 阈值的限制.仅支持基本类型和字符串类型	
       9. clusterMode:是否是集群参数流控规则,默认false
       10. clusterConfig:集群流控相关配置
    * 授权规则配置说明
       1. resource:资源名,即限流规则的作用对象
       2. limitApp:对应的黑名单/白名单,不同 origin 用,分隔,如 appA,appB
       3. strategy:限制模式,AUTHORITY_WHITE 为白名单模式,AUTHORITY_BLACK 为黑名单模式,默认为白名单模式