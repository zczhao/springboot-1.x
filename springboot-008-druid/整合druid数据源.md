# 整合基本JDBC与数据源

## 1、引入starter

spring-boot-starter-jdbc

## 2、配置application.yml

## 3、测试

## 4、高级配置，使用druid数据源

## 5、配置druid数据源监控

```yml
spring:
  datasource:
    # 数据源基本配置
    username: root
    password: 123456
    url: jdbc:mysql://192.168.156.60:3306/jdbc?useSSL=false
    driver-class-name: com.mysql.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
    # 数据源其他配置
    druid:
      # 连接池的配置信息
      # 初始化大小
      initial-size: 5
      # 最小值
      min-idle: 5
      # 最大值
      maxActive: 20
      # 最大等待时间，配置获取连接等待超时，时间单位都是毫秒ms
      maxWait: 60000
      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
      timeBetweenEvictionRunsMillis: 60000
      # 配置一个连接在池中最小生存的时间，单位是毫秒
      minEvictableIdleTimeMillis: 300000
      validationQuery: SELECT 1 FROM DUAL
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
      # 打开PSCache，并且指定每个连接上PSCache的大小
      poolPreparedStatements: true
      maxPoolPreparedStatementPerConnectionSize: 20
      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
      # DruidFilterConfiguration
      filter:
        slf4j:
          enabled: true
        wall:
          enabled: true
        stat:
          enabled: true
      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
      connectionProperties: druid.stat.mergeSql\=true;druid.stat.slowSqlMillis\=5000
      # 配置DruidStatFilter
      web-stat-filter:
        # 默认为false，设置为true启动
        enabled: true
        url-pattern: "/*"
        exclusions: "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"
      # 配置DruidStatViewServlet
      stat-view-servlet:
        url-pattern: "/druid/*"
        # IP白名单(没有配置或者为空，则允许所有访问)
        # allow: 127.0.0.1,192.168.46.120
        # IP黑名单 (存在共同时，deny优先于allow)
        # deny: 192.168.46.121
        #  禁用HTML页面上的“Reset All”功能
        reset-enable: false
        # 登录名
        login-username: admin
        # 登录密码
        login-password: 123456
        # 默认为false，设置为true启用
        enabled: true
```

访问地址：http://localhost:8080/druid