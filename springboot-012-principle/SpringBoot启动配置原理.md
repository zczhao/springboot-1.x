# Spring Boot启动配置原理

## 1、启动原理

### SpringApplication.run(主程序类)

new SpringApplication(主程序类)

- 判断是否web应用
- 加载并保存所有ApplicationContextInitializer(META-INF/spring.factories)
- 加载并保存所有ApplicationListener
- 获取到主程序类

run()

- 回调所有的SpringApplicationRunListener(META-INF/spring.factories)的starting
- 获取ApplicationArguments
- 准备环境&回调所有监听器(SpringApplicationRunListener)的environmentPrepared
- 打印banner信息
- 创建ioc容器对象
  AnnotationConfigEmbeddedWebApplicationContext(Web环境容器)
  AnnotationConfigApplicationContext(普通环境容器)

## 2、运行流程

run()

- 准备环境
  执行ApplicationContextInitializer.initialize()
  监听器SpringApplicationRunListener回调contextPrepared
  加载主配置类定义信息
  监听器SpringApplicationRunListener回调contextLoaded
- 刷新启动IOC容器
  扫描加载所有容器中的组件
  包括从META-INF/spring.factories中获取的所有EnableAutoConfiguration组件
- 回调容器中所有的ApplicationRunner、CommandLineRunner的run方法
- 监听器SpringApplicationRunListener回调finished

## 3、自动配置原理