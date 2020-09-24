package com.spring.learning.config;


import com.spring.learning.model.ResponseEntity;
import com.spring.learning.util.Constant;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * Author: yangyk
 * Date: 2020/9/24 16:05
 * Description: 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity exceptionHandler(Exception e) {
		return ResponseEntity.build(Constant.ERROR_CODE, "服务异常", null);
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
		return ResponseEntity.build(Constant.ERROR_CODE, "服务异常", null);
	}

	/**
	 * 自定义验证异常(参数传值)
	 */
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity validatedBindException(ConstraintViolationException e) {
		return ResponseEntity.build(Constant.ERROR_CODE, "服务异常", null);
	}
}
