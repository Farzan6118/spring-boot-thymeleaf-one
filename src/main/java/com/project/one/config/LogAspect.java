package com.project.one.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @After("execution(* com.project.one.controller.*.*(..))")
    public void afterExecution(JoinPoint joinPoint) {
        //Advice
        logger.info("execution for {}", joinPoint);
    }

}
