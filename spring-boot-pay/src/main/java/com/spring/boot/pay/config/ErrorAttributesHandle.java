package com.spring.boot.pay.config;


import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/17 15:03
 * @description: 自定义错误信息参数
 */
@Component
public class ErrorAttributesHandle extends DefaultErrorAttributes {
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
		HashMap<String, Object> hashMap = new HashMap<>(3);
		hashMap.put("status", map.get("status"));
		hashMap.put("msg", map.get("message"));
		hashMap.put("data", null);
		return hashMap;
	}
}
