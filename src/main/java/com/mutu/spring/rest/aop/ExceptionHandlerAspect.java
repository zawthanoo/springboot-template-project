package com.mutu.spring.rest.aop;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.mutu.spring.rest.aop.exception.DAOException;
import com.mutu.spring.rest.aop.exception.MessageCode;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018 <br/>
 *        It is a intercepter class. Which is used to translate SQLExceptoin to
 *        custom DAOException.<br/>
 *        When DAO or Mybatic Mapper get SQLExceptoin or error, this class
 *        automatically intercept to translate.<br/>
 */

@Aspect
@Component
public class ExceptionHandlerAspect extends PointCutConfig {
	private Logger logger = LogManager.getLogger(ExceptionHandlerAspect.class);

	@AfterThrowing(pointcut = "publicMethodInsideDaoBean()", throwing = "e")
	public void daoThrowing(JoinPoint joinPoint, Exception e) throws Throwable {
		logger.error("Unexpected error in dao", e);
		DAOException dae = null;
		Throwable throwable = e;
		while (throwable != null && !(throwable instanceof SQLException)) {
			throwable = throwable.getCause();
		}
		if (throwable instanceof SQLException) {
			dae = new DAOException(MessageCode.DB_PROCESS_ERROR, "Unexpected error in dao", (SQLException) throwable);
		}
		if (dae != null) {
			throw dae;
		} else {
			throw new DAOException(MessageCode.DB_PROCESS_ERROR, "Unexpected error in dao", e);
		}
	}

	@AfterThrowing(pointcut = "publicMethodInsideMapper()", throwing = "e")
	public void mapperThrowing(JoinPoint joinPoint, Exception e) throws Throwable {
		DAOException dae = null;
		Throwable throwable = e;
		while (throwable != null && !(throwable instanceof SQLException)) {
			throwable = throwable.getCause();
		}
		if (throwable instanceof SQLException) {
			dae = new DAOException(MessageCode.DB_PROCESS_ERROR, "Unexpected error in mapper.",
					(SQLException) throwable);
		}
		if (dae != null) {
			throw dae;
		} else {
			throw new DAOException(MessageCode.DB_PROCESS_ERROR, "Unexpected error in mapper", e);
		}
	}

}
