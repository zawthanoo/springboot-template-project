package com.mutu.spring.rest.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAspect extends PointCutConfig {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Before("publicMethodInsideServiceBean() || publicMethodInsideDaoBean()")
	public void beforeServiceDao(JoinPoint joinPoint) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String logMessage = String.format("%s %s method has been started.", joinPoint.getSignature().getDeclaringType(), joinPoint.getSignature().getName());
			String parameter = mapper.writeValueAsString(joinPoint.getArgs());
			logger.debug(logMessage + " Parameter => " + parameter);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	// only hit without throwing exception
	@AfterReturning(pointcut="publicMethodInsideServiceBean() || publicMethodInsideDaoBean()", returning = "result")
	public void afterReturningServiceDao(JoinPoint joinPoint, Object result) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String logMessage = String.format("%s %s method has been finished.", joinPoint.getSignature().getDeclaringType(), joinPoint.getSignature().getName());
			String returnResult = mapper.writeValueAsString(result);
			logger.debug(logMessage + " Return Result => " + returnResult);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}		
	}
	
}
