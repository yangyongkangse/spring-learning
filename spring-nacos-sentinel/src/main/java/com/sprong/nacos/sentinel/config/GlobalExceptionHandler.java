package com.sprong.nacos.sentinel.config;

import com.spring.api.model.ResponseEntity;
import com.spring.api.tools.Constant;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * 全局异常处理
 * Created with IntelliJ IDEA.
 *
 * @Author: yangyongkang
 * Date: 2018/8/12
 * Time: 14:36
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity exceptionHandler(Exception e) {
		return ResponseEntity.build(Constant.ERROR_CODE, e.getMessage(), null);
	}

	@ExceptionHandler(value = UndeclaredThrowableException.class)
	public ResponseEntity blockExceptionHandler(UndeclaredThrowableException e) {
		return ResponseEntity.build(Constant.ERROR_CODE, "拒绝访问!", null);
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
		return ResponseEntity.build(Constant.ERROR_CODE, e.getMessage(), null);
	}
}