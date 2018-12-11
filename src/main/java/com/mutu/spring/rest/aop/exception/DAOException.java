package com.mutu.spring.rest.aop.exception;


 /**
  * @author Zaw Than Oo
  */
 public class DAOException extends RuntimeException {
	private static final long serialVersionUID = -1402323572092762148L;
	private String errorCode;
     
     public DAOException(String errorCode, String message, Throwable throwable){
         super(message, throwable);
         this.errorCode = errorCode;
     }

     public String getErrorCode() {
         return errorCode;
     }
 }
