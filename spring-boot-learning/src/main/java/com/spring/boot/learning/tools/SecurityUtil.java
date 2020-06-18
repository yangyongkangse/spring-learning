package com.spring.boot.learning.tools;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/18 10:21
 * @description:
 */
@Component
public class SecurityUtil {


	public static String getUserInfo() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
