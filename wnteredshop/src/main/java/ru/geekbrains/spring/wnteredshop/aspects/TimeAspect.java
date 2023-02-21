package ru.geekbrains.spring.wnteredshop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
//@Aspect
//@Component
public class TimeAspect {

    @Pointcut("@within(ru.geekbrains.spring.wnteredshop.annotations.Time)")
    public void classAnnotatedPointcut(){}

    @Pointcut("@annotation(ru.geekbrains.spring.wnteredshop.annotations.Time)")
    public void methodAnnotatedPointcut(){}

    @Pointcut("methodAnnotatedPointcut() ||classAnnotatedPointcut()")
    public void targetPointcut(){}

    @Around("targetPointcut()")
    public Object aroundTimeAnnotation(ProceedingJoinPoint pjp) throws Throwable{
        Long start = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        Long delta = System.currentTimeMillis()-start;
        Class<?> beanClass = pjp.getTarget().getClass();
        String methodName = pjp.getSignature().getName();
        log.info("{}#{}:{}ms",beanClass,methodName,delta);
        return  proceed;

    }
}
