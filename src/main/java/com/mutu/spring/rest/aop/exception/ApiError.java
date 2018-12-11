package com.mutu.spring.rest.aop.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ApiError {
	private String status;
	private String messageCode;
	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private Object payLoad;
	
	private ApiError() {
		timestamp = LocalDateTime.now();
	}

	public ApiError(String status) {
		this();
		this.status = status;
	}

	public ApiError(String status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
	}

	public ApiError(String status, String message, String messageCode, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
	}
}