package com.spring.learning.config;


import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: yangyk
 * Date: 2020/9/24 14:37
 * Description: 自定义错误信息参数
 */
@Component
public class ErrorAttributesHandle extends DefaultErrorAttributes {
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
		Map<String, Object> map = super.getErrorAttributes(webRequest, options);
		HashMap<String, Object> hashMap = new HashMap<>(3);
		hashMap.put("status", map.get("status"));
		hashMap.put("msg", map.get("message"));
		hashMap.put("data", null);
		return hashMap;
	}
}
