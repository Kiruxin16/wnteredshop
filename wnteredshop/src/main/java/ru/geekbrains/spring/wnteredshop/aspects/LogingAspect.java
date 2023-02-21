package ru.geekbrains.spring.wnteredshop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
//@Aspect
//@Component
public class LogingAspect {

    @Pointcut("execution(* ru.geekbrains.spring.wnteredshop.services.*.*(..))")
    public void loggingPointcut(){}

    @Before("loggingPointcut()")
    public void beforeMethodLoging(JoinPoint jp) throws Throwable {

        Class<?> beanClass = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        if(jp.getArgs()!=null){
            if(jp.getArgs().length==0){
                log.info("Был вызван метод {}#{} без аргументов", beanClass.getName(), methodName);
            }else {
                StringBuilder args = new StringBuilder("(");
                for (int i = 0; i < jp.getArgs().length; i++) {
                    args.append(jp.getArgs()[i].toString());
                    args.append(",");
                }
                args.deleteCharAt(args.length() - 1);
                args.append(")");
                log.info("Был вызван метод {}#{} с аргументами {}", beanClass.getName(), methodName, args);
            }
        }




    }
}
