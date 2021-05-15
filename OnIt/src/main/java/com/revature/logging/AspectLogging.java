package com.revature.logging;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.JoinPoint;

import org.springframework.stereotype.Component;

@Component 
@Aspect
public class AspectLogging {
	
	//@Pointcut("execution(* com.revature.*.*(..))")
	@Pointcut("execution(* *(..))")
	private void selectAll(){}
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	
	@Before("selectAll()")
	   public void beforeAdvice(JoinPoint thisJoinPoint){
	      System.out.println(LocalDateTime.now()+ " before: " + thisJoinPoint.getSignature().toShortString() + " with args: " + thisJoinPoint.getArgs());
	}
	
	@After("selectAll()")
	   public void afterAdvice(JoinPoint thisJoinPoint){
	      System.out.println(LocalDateTime.now() + " after: " + thisJoinPoint.getSignature().toShortString() + " with args: " + thisJoinPoint.getArgs());
	}
 
}