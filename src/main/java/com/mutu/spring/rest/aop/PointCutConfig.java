package com.mutu.spring.rest.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCutConfig {
	
	@Pointcut("within(@org.springframework.stereotype.Service *)")
	protected void serviceBean() {}
	
	@Pointcut("within(@org.springframework.stereotype.Repository *)")
	protected void daoBean() {}
	
	@Pointcut("execution(* com.mutu.spring.rest.mapper.*.*(..)) || execution(* com.mutu.spring.rest.custommapper.*.*(..))")
	protected void mapper() {}

	@Pointcut("execution(public * *(..))")
	protected void publicMethod() {}

	@Pointcut("publicMethod() && serviceBean()")
	protected void publicMethodInsideServiceBean() {}
	
	@Pointcut("publicMethod() && daoBean()")
	protected void publicMethodInsideDaoBean() {}
	
	@Pointcut("publicMethod() && mapper()")
	protected void publicMethodInsideMapper() {}
}
