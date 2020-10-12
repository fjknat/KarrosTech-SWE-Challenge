/*******************************************************************************
 * Copyright (C) 2020, Thai Phung
 * 
 * This is the Karros's demo project which develop by Thai Phung. 
 ******************************************************************************/
package com.example.demo.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020			1.0				Initialize
 */

@Aspect
@Component
public class LoggingAspect {
	private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

	@AfterThrowing(pointcut = "execution(* com.example.demo..*.*(..))", throwing = "ex")
	public void catchAllError(Exception ex) {
		logger.error(ex);
	}

	@Around("@annotation(com.example.demo.annotation.ExecutionTimeLogging)")
	public Object logExecutionRunTime(ProceedingJoinPoint joinPoint) throws Throwable {
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		String taskName = String.format("Run_%s.%s", className, methodName);

		StopWatch stopWatch = new StopWatch(taskName);
		stopWatch.start(taskName);
		Object res = joinPoint.proceed();
		stopWatch.stop();
		logger.info(stopWatch.shortSummary());
		return res;
	}

}
