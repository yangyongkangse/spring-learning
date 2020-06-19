package com.spring.boot.learning.exception;

import com.spring.api.model.ResponseEntity;
import com.spring.api.tools.Constant;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

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

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
		return ResponseEntity.build(Constant.ERROR_CODE, e.getMessage(), null);
	}

	@ExceptionHandler(value = IoException.class)
	public ResponseEntity ioExceptionHandler(IoException e) {
		return ResponseEntity.build(Constant.ERROR_CODE, e.getMessage(), null);
	}
	@ExceptionHandler(value = IOException.class)
	public ResponseEntity IOExceptionHandler(IOException e) {
		return ResponseEntity.build(Constant.ERROR_CODE, e.getMessage(), null);
	}
}
