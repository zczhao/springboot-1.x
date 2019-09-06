# Spring Boot使用AOP处理请求

## 1、导包

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

## 2、新增AOP处理类

```java
package zzc.springboot.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpAspect {
    // 调用controller方法之前执行
    @Before("execution(public * zzc.springboot.controller.GirlController.*(..))")
    public void aspectBefore() {
        System.out.println("aspectBefore...");
    }

    // 调用controller方法之后执行
    @After("execution(public * zzc.springboot.controller.GirlController.*(..))")
    public void aspectAfter() {
        System.out.println("aspectAfter...");
    }
}

```

下在是简写(推荐这中写法)：

```java
package zzc.springboot.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpAspect {

    // springboot自带的log，底层实现是logback
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * zzc.springboot.controller.GirlController.*(..))")
    public void pointcut() {
    }


    // 调用controller方法之前执行
    @Before("pointcut()")
    public void aspectBefore() {
        logger.info("aspectBefore...");
    }

    // 调用controller方法之后执行
    @After("pointcut()")
    public void aspectAfter() {
        logger.info("aspectAfter...");
    }
}

```

## 3、测试

```java
package zzc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zzc.springboot.entity.Girl;
import zzc.springboot.repository.GirlRepository;
import zzc.springboot.serivce.GirlService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    /**
     * 查询所有女生列表
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList() {
        System.out.println("girlList");
        return girlRepository.findAll();
    }
}
```

## 4、输出顺序

```
aspectBefore...
GirlController.girlList()...
aspectAfter...
```

## 5、在Aspect里记录请求参数

```java
package zzc.springboot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * zzc.springboot.controller.GirlController.*(..))")
    public void pointcut() {
    }


    // 调用controller方法之前执行
    @Before("pointcut()")
    public void aspectBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // url
        logger.info("url={}", request.getRequestURL());

        // method
        logger.info("method={}", request.getMethod());

        // ip
        logger.info("ip={}", request.getRemoteAddr());

        // 类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        // 参数
        logger.info("args={}", joinPoint.getArgs());
    }

    // 调用controller方法之后执行
    @After("pointcut()")
    public void aspectAfter() {
        logger.info("aspectAfter...");
    }

    // 获取返回内容
    @AfterReturning(returning = "object", pointcut = "pointcut()")
    public void afterReturning(Object object) {
        logger.info("response={}", object);
    }
}

```

```
请求地址：http://localhost:8080/girls/1
aspectBefore输出结果：
2019-09-06 09:45:10.139  INFO 14240 --- [nio-8080-exec-1] zzc.springboot.aspect.HttpAspect         : aspectAfter...
2019-09-06 09:46:54.241  INFO 14240 --- [nio-8080-exec-3] zzc.springboot.aspect.HttpAspect         : url=http://localhost:8080/girls/1
2019-09-06 09:46:54.241  INFO 14240 --- [nio-8080-exec-3] zzc.springboot.aspect.HttpAspect         : method=GET
2019-09-06 09:46:54.241  INFO 14240 --- [nio-8080-exec-3] zzc.springboot.aspect.HttpAspect         : ip=127.0.0.1
2019-09-06 09:46:54.241  INFO 14240 --- [nio-8080-exec-3] zzc.springboot.aspect.HttpAspect         : class_method=zzc.springboot.controller.GirlController.girlFindOne
2019-09-06 09:46:54.246  INFO 14240 --- [nio-8080-exec-3] zzc.springboot.aspect.HttpAspect         : args=1

afterReturning输出结果：
2019-09-06 09:52:14.945  INFO 8648 --- [nio-8080-exec-1] zzc.springboot.aspect.HttpAspect         : aspectAfter...
```



