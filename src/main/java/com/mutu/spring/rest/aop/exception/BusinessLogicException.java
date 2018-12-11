package com.mutu.spring.rest.aop.exception;

import org.springframework.transaction.TransactionSystemException;

public class BusinessLogicException extends TransactionSystemException {
	private static final long serialVersionUID = -4636343176401289427L;
	private String errorCode;
	private Object response;

	public BusinessLogicException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public BusinessLogicException(String errorCode, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}

	public BusinessLogicException(String errorCode, Object response, String message) {
		super(message);
		this.errorCode = errorCode;
		this.response = response;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object getResponse() {
		return response;
	}
}
