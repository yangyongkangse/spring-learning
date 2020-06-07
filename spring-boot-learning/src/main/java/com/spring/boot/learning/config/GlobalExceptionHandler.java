package com.spring.boot.learning.config;

import com.spring.api.model.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

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
	public ResponseEntity exceptionHandler(HttpServletResponse response, Exception e) {
		return ResponseEntity.build(response.getStatus(), e.getMessage(), null);
	}
}
