package com.spring.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/20 17:02
 * @description: 从nacos配置中心读取配置信息
 * RefreshScope注解支持nacos的动态刷新功能
 */
@RestController
@RefreshScope
@RequestMapping("/api/config")
public class ConfigController {

	@Value("${config.info}")
	private String configInfo;

	@GetMapping(value = "/getConfigInfo")
	public String getConfigInfo() {
		return configInfo;
	}
}
