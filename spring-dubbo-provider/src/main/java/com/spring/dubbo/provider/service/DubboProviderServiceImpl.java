package com.spring.dubbo.provider.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/26 14:58
 * @description:
 */
@DubboService
@Service
public class DubboProviderServiceImpl implements DubboProviderService {

	@Value("${server.port}")
	private String port;

	@Override
	public String getDubboService(String content) {
		return content + port;
	}
}
