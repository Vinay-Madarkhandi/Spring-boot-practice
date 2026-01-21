package com.example.demo.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

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

            System.out.println("Total time required for execution is " + total);
        }
    }
}
