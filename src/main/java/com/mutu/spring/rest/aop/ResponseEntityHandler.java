package com.mutu.spring.rest.aop;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.mutu.spring.rest.aop.exception.ApiError;
import com.mutu.spring.rest.api.ApiStatus;
import com.mutu.spring.rest.api.Result;

@ControllerAdvice
public class ResponseEntityHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(final MethodParameter returnType, final Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(final Object body, final MethodParameter returnType, final MediaType selectedContentType,
        final Class<? extends HttpMessageConverter<?>> selectedConverterType, final ServerHttpRequest request,
        final ServerHttpResponse response) {
    	if(body instanceof ApiError) {
    		return body;
    	}
    	if(body instanceof String) {
    		return body;
    	}
    	Result result = new Result();
    	result.setData(body);
    	result.setStatus(ApiStatus.SUCCESS);
        return result;
    }
}