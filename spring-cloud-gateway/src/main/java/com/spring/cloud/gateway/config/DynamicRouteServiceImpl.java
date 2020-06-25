package com.spring.cloud.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/23 10:13
 * @description: 动态路由控制  发布事件
 * PropertiesRouteDefinitionLocator：从配置文件中读取路由信息(如YML、Properties等)
 * RouteDefinitionRepository：从存储器中读取路由信息(如内存、配置中心、Redis、MySQL等)
 * DiscoveryClientRouteDefinitionLocator：从注册中心中读取路由信息(如Nacos、Zookeeper等)
 */
@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

	private RouteDefinitionWriter routeDefinitionWriter;

	@Autowired
	public void setRouteDefinitionWriter(RouteDefinitionWriter routeDefinitionWriter) {
		this.routeDefinitionWriter = routeDefinitionWriter;
	}

	private ApplicationEventPublisher publisher;

	/**
	 * author: yangyk
	 * date:2020/6/23 10:27
	 * description:增加路由
	 **/
	public String add(RouteDefinition definition) {
		routeDefinitionWriter.save(Mono.just(definition)).subscribe();
		this.publisher.publishEvent(new RefreshRoutesEvent(this));
		return "add route success";
	}


	/**
	 * author: yangyk
	 * date:2020/6/23 10:27
	 * description:更新路由
	 **/
	public String update(RouteDefinition definition) {
		try {
			this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
		} catch (Exception e) {
			return "update fail,not find route routeId: " + definition.getId();
		}
		try {
			routeDefinitionWriter.save(Mono.just(definition)).subscribe();
			this.publisher.publishEvent(new RefreshRoutesEvent(this));
			return "success";
		} catch (Exception e) {
			return "update route fail";
		}
	}

	/**
	 * author: yangyk
	 * date:2020/6/23 10:27
	 * description:删除路由
	 **/
	public String delete(String id) {
		try {
			this.routeDefinitionWriter.delete(Mono.just(id));
			return "delete route  success";
		} catch (Exception e) {
			return "delete route  fail";
		}

	}

	@Override
	public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

}
