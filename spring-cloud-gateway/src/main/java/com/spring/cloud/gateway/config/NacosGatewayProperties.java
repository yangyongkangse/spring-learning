package com.spring.cloud.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/23 10:40
 * @description: 读取nacos配置
 */
@ConfigurationProperties(prefix = "nacos")
@Configuration
@Data
public class NacosGatewayProperties {

	private String address;

	private String dataId;

	private String groupId;

	private Long timeout;
}
