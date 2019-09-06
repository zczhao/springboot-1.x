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
        logger.info("response={}", object.toString());
    }
}
