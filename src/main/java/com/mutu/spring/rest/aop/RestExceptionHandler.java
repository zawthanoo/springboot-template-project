package com.mutu.spring.rest.aop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mutu.spring.rest.aop.dto.ApiStatus;
import com.mutu.spring.rest.aop.dto.InvalidField;
import com.mutu.spring.rest.aop.exception.ApiError;
import com.mutu.spring.rest.aop.exception.BusinessLogicException;
import com.mutu.spring.rest.aop.exception.DAOException;
import com.mutu.spring.rest.aop.exception.MessageCode;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018 <br/>
 *        It is used to hand an exception for the controller. <br/>
 *        Which is used to translate
 *        <code>Exception<code> to custom <code>ApiError</code>.<br/>
 *        If the system is thrown any exception or occurred error, this class
 *        automatically response <code>ApiError</code> object to client. <br/>
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessLogicException.class)
	protected ResponseEntity<ApiError> handleBusinessLogicNotFound(BusinessLogicException ex) {
		ApiError apiError = new ApiError(ApiStatus.FAILED);
		apiError.setMessage(ex.getMessage());
		apiError.setMessageCode(ex.getErrorCode());
		apiError.setPayLoad(ex.getResponse());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(DAOException.class)
	protected ResponseEntity<ApiError> handleDAOException(DAOException ex) {
		ApiError apiError = new ApiError(ApiStatus.FAILED);
		apiError.setMessage(ex.getMessage());
		apiError.setMessageCode(ex.getErrorCode());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ApiError> handle(Exception ex) {
		ApiError apiError = new ApiError(ApiStatus.FAILED);
		apiError.setMessage(ex.getMessage());
		apiError.setMessageCode(MessageCode.UNEXPECTED_ERROR);
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<InvalidField> invalidFieldList = new ArrayList<InvalidField>();
		for(FieldError error : ex.getBindingResult().getFieldErrors()) {
			invalidFieldList.add(new InvalidField(error.getField(), error.getCode(), error.getDefaultMessage()));
		}
		ApiError apiError = new ApiError(ApiStatus.FAILED);
		apiError.setMessage("Request parameter is invalid.");
		apiError.setMessageCode(MessageCode.INVALID_REQUEST_PARAMETER);
		apiError.setPayLoad(invalidFieldList);
		return (ResponseEntity)buildResponseEntity(apiError);
	}

	private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<ApiError>(apiError, HttpStatus.EXPECTATION_FAILED);
	}

}
