package com.mutu.spring.rest.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018 <br/>
 *        It is a intercepter class. Which is used to log for all DAO and
 *        Service classes.<br/>
 *        A method before start, this class automatically will log method name
 *        and parameter as JSON format.<br/>
 *        A method before end, this class automatically will log method name and
 *        result as JSON format.<br/>
 *        Note : A method before end will not be hit if there is occurred an
 *        exception.<br/>
 */
@Aspect
@Component
public class LoggingAspect extends PointCutConfig {

	private Logger logger = LogManager.getLogger(LoggingAspect.class);

	@Before("publicMethodInsideServiceBean() || publicMethodInsideDaoBean()")
	public void beforeServiceDao(JoinPoint joinPoint) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String logMessage = String.format("%s.%s() method has been finished.",
					joinPoint.getSignature().getDeclaringType(), joinPoint.getSignature().getName());
			String parameter = mapper.writeValueAsString(joinPoint.getArgs());
			logger.debug(logMessage + " Parameter => " + parameter);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	// only hit without throwing exception
	@AfterReturning(pointcut = "publicMethodInsideServiceBean() || publicMethodInsideDaoBean()", returning = "result")
	public void afterReturningServiceDao(JoinPoint joinPoint, Object result) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String logMessage = String.format("%s.%s() method has been finished.",
					joinPoint.getSignature().getDeclaringType(), joinPoint.getSignature().getName());
			String returnResult = mapper.writeValueAsString(result);
			logger.debug(logMessage + " Return Result => " + returnResult);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

}
