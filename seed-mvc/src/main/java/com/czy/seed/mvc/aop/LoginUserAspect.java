package com.czy.seed.mvc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoginUserAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * *.*(.., SecurityUser))")
    public void loginUserPointCut() {

    }

    @Before("loginUserPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println(args);


    }


}
