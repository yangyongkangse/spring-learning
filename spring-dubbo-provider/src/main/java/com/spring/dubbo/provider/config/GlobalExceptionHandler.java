package com.spring.dubbo.provider.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.spring.api.model.ResponseEntity;
import com.spring.api.tools.Constant;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
	@ExceptionHandler(value = BlockException.class)
	public ResponseEntity blockExceptionExceptionHandler(BlockException e) {
		return ResponseEntity.build(Constant.ERROR_CODE, e.getMessage(), null);
	}
}
