package com.travelAlone.s20230404.aspect;

import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class TimeCheckAspect {

	    @Around("execution(* com.travelAlone.s20230404.controller..*(..))")
	    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws  Throwable{
	        String methodName = joinPoint.getSignature().getName();
	        String className = joinPoint.getTarget().getClass().getSimpleName();

	        log.info("START Class, className : "+ className +" "+LocalDateTime.now());
	        log.info("START Method, name : "+ methodName + " "+LocalDateTime.now());
	        long start = System.currentTimeMillis();

	        Object obj = joinPoint.proceed();

	        long secDiffTime = (System.currentTimeMillis() - start);
	        log.info("END Method, Execution time : " + secDiffTime+"ms"+",  "+LocalDateTime.now());
	        return obj;
	    }
	}

