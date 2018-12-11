package com.mutu.spring.rest.aop;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.mutu.spring.rest.aop.exception.DAOException;
import com.mutu.spring.rest.aop.exception.MessageCode;

@Aspect
@Component
public class ExceptionHandlerAspect extends PointCutConfig {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@AfterThrowing(pointcut = "publicMethodInsideDaoBean()", throwing = "e")
	public void daoThrowing(JoinPoint joinPoint, Exception e) throws Throwable {
		logger.error(e.getMessage());
		DAOException dae = null;
        Throwable throwable = e;
        while (throwable != null && !(throwable instanceof SQLException)) {
            throwable = throwable.getCause();
        }
        if (throwable instanceof SQLException) {
            dae =  new DAOException(MessageCode.DB_PROCESS_ERROR, "Unexpected error in dao", (SQLException) throwable);
        }
        if (dae != null) {
            throw dae;
        } else {
        	throw new DAOException(MessageCode.DB_PROCESS_ERROR, "Unexpected error in dao", e);
        }
	}
	
	@AfterThrowing(pointcut = "publicMethodInsideMapper()", throwing = "e")
	public void mapperThrowing(JoinPoint joinPoint, Exception e) throws Throwable {
		logger.error(e.getMessage());
		DAOException dae = null;
        Throwable throwable = e;
        while (throwable != null && !(throwable instanceof SQLException)) {
            throwable = throwable.getCause();
        }
        if (throwable instanceof SQLException) {
            dae =  new DAOException(MessageCode.DB_PROCESS_ERROR, "Unexpected error in mapper.", (SQLException) throwable);
        }
        if (dae != null) {
            throw dae;
        } else {
        	throw new DAOException(MessageCode.DB_PROCESS_ERROR, "Unexpected error in mapper", e);
        }
	}

}
