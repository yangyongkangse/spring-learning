package com.spring.cloud.gateway.config;


import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.Executor;


/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/23 10:41
 * @description:
 */
@Component
public class DynamicRouteServiceImplByNacos implements CommandLineRunner {

	final ObjectMapper mapper = new ObjectMapper();

	private DynamicRouteServiceImpl dynamicRouteService;

	@Autowired
	public void setDynamicRouteService(DynamicRouteServiceImpl dynamicRouteService) {
		this.dynamicRouteService = dynamicRouteService;
	}

	private NacosGatewayProperties nacosGatewayProperties;

	@Autowired
	public void setNacosGatewayProperties(NacosGatewayProperties nacosGatewayProperties) {
		this.nacosGatewayProperties = nacosGatewayProperties;
	}

	/**
	 * author: yangyk
	 * date:2020/6/23 10:43
	 * description:监听Nacos Server下发的动态路由配置  一旦在nacos修改了配置文件会触发此监听
	 **/
	public void dynamicRouteByNacosListener() {
		try {
			ConfigService configService = NacosFactory.createConfigService(nacosGatewayProperties.getAddress());
			String content = configService.getConfig(nacosGatewayProperties.getDataId(), nacosGatewayProperties.getGroupId(), nacosGatewayProperties.getTimeout());
			//网关服务启动时初始化一遍路由
			if (!StringUtils.isEmpty(content)) {
				List<RouteDefinition> list = mapper.readValue(content, new TypeReference<>() {
				});
				list.forEach(definition -> {
					dynamicRouteService.update(definition);
				});
			}
			System.out.println(content);
			configService.addListener(nacosGatewayProperties.getDataId(), nacosGatewayProperties.getGroupId(), new Listener() {
				@Override
				public Executor getExecutor() {
					return null;
				}

				@SneakyThrows
				@Override
				public void receiveConfigInfo(String configInfo) {
					//路由配置改变时初始化一遍路由
					List<RouteDefinition> list = mapper.readValue(configInfo, new TypeReference<>() {
					});
					list.forEach(definition -> {
						dynamicRouteService.update(definition);
					});
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run(String... args) {
		dynamicRouteByNacosListener();
	}
}
