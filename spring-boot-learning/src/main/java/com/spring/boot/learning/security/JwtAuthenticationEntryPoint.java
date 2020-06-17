package com.spring.boot.learning.security;

import com.spring.api.tools.Constant;
import com.spring.boot.learning.exception.IoException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/17 9:37
 * @description: 定义 JwtAuthenticationEntryPoint 实现 AuthenticationEntryPoint 接口及其中的commence方法.
 * commence方法会在未认证用户视图访问需要认证的资源时触发.
 */
@Component
@Log4j2
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IoException {
		log.error("Responding with unauthorized error. Message - {}", authException.getMessage());
		throw new IoException(Constant.ERROR_CODE,"身份认证失败");
	}
}
