package com.mutu.spring.rest.aop.exception;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 5856407050404971979L;
	
	private String errorCode;

	public ApiException() {
        super();
    }
	
	public ApiException(String message) {
        super(message);
    }
	
	public ApiException(Throwable cause) {
        super(cause);
    }
	
	public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public ApiException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
    }
	
	public String getErrorCode() {
		return errorCode;
	}

}
