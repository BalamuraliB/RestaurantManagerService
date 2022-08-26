package com.swatkats.restaurantManager.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* com.swatkats.restaurantManager.*.*.*(..))")
	public void allMethods() {}
	
	@Around("allMethods()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("[Class: " + joinPoint.getTarget().getClass().getCanonicalName() + " ::: ]"
				+ joinPoint.getSignature().getName() + " method started execution");
		Object retVal = joinPoint.proceed();
		logger.info("[Class: " + joinPoint.getTarget().getClass().getCanonicalName() + " ::: ]"
				+ joinPoint.getSignature().getName() + " method completed execution");
		return retVal;
	}

	@AfterThrowing(pointcut = "allMethods()", throwing = "ex")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
		logger.error("[Class: " + joinPoint.getTarget().getClass().getCanonicalName() + " ::: ]"
				+ joinPoint.getSignature().getName() + " threw exception: "+ ex.getMessage());
		logger.error(ex.getMessage(), ex);
	}

}
