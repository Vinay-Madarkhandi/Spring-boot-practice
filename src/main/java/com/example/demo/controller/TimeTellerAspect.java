package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeTellerAspect {
    @Around("@annotation(TimeTeller)")
    public void calculateExecutionTime(ProceedingJoinPoint joinPoint){
        long start = System.currentTimeMillis();

        try{
            joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            long end = System.currentTimeMillis();
            long total = end - start;

            log.info("Total time required for execution is " + total + " ms");
        }
    }
}
