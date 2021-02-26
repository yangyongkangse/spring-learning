package com.spring.boot.elasticsearch.config;

import com.spring.boot.elasticsearch.model.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Author: yangyk
 * Date: 2021/2/25 16:30
 * Description: 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity exceptionHandler(Exception e) {
		return ResponseEntity.error(e.getMessage(), null);
	}
}
