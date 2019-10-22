# eureka-server

port: 7002

@EnableEurekaServer

Console：http://localhost:7002/eureka

# eureka-client

port: 8001

@EnableEurekaClient

actuator 是spring boot的监控框架，只要引入这个dependency，就可以用/actuator/info访问应用的状态
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
这个info信息取自 application.yml
```yaml
info:
  app.name: service-provider
  company.name: helloworld
  build.artifactId: @project.artifactId@
  build.version: @project.version@
```
# ribbon

port：8004

启动两个服务：service-provider和service-provider2，这两个服务是同名的(service-provider)，port：8002，8003

通过服务名访问：restTemplate.getForObject("http://service-provider/hello", String.class);

restTemplate 使用注解 @LoadBalanced，客户端负载均衡

# hystrix

port: 8005

@EnableCircuitBreaker   //开启熔断器

@EnableHystrixDashboard //开启熔断器仪表盘监控

@HystrixCommand(fallbackMethod = "helloError")

HystrixDashboardConfiguration

熔断器仪表盘监控 
- Servlet: http://localhost:8005/hystrix.stream
- Console: http://localhost:8005/hystrix

# zuul

port: 8006

@EnableZuulProxy

application.yml 代理了 /service-provider/* 请求
```yaml
zuul:
  routes:
    api-a:
      path: /service-provider/*
      serviceId: service-provider
```

测试：http://localhost:8006/service-provider/hello

# config server

port: 8007

@EnableConfigServer

application.yml 本地管理配置文件，也可以是git 
```yaml
spring:
  application:
    name: config-server
  profiles:
    active: native
  cloud:
    config:
      server:
        native:
          searchLocations: /export/workspace/hello-springcloud/config-server/conf
```

# config client

它的配置文件是从 config-server 获得的，只需要建立bootstrap.yml，这个文件先于 application.yml 加载

bootstrap.yml，系统会从config-server加载client-dev.yml文件，然后继续启动
```yaml
spring:
  application:
    name: client
  cloud:
    config:
      uri: http://localhost:8007
      profile: dev
      label: master
```

client-dev.yml 注意 @project.artifactId@ 需要用引号
```yaml
server:
  port: 8008

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:7002/eureka
  healthcheck:
    enabled: true
  instance:
    prefer-ip-address: true

info:
  app.name: ${spring.application.name}
  company.name: helloworld
  build.artifactId: "@project.artifactId@"
  build.version: "@project.version@"

from: from client-dev.yml

```
测试: http://localhost:8008/hello

# 遇到的问题

问题：Your ApplicationContext is unlikely to start due to a @ComponentScan of the default package.

解决：src不能直接放Java，需要建立package

问题：No instances available for service xxx

解决：可能是service刚启动起来，还没有在eureka-server上注册完成，稍等一会再试
