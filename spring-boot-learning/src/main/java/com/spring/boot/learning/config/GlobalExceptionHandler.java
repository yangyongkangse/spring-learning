package com.spring.boot.learning.config;

import com.spring.api.model.ResponseEntity;
import com.spring.api.tools.Constant;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * Created with IntelliJ IDEA.
 *
 * @Author: yangyongkang
 * Date: 2018/8/12
 * Time: 14:36
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity exceptionHandler(Exception e) {
		return ResponseEntity.build(Constant.ERROR_CODE, e.getMessage(), null);
	}

	@ResponseBody
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
		return ResponseEntity.build(Constant.ERROR_CODE, e.getMessage(), null);
	}
}
