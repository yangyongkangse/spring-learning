package com.spring.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/22 19:05
 * @description: gateway全局过滤器
 */

public class GateWayGlobalFilter implements GlobalFilter, Ordered {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		//token校验
		ServerHttpRequest req = exchange.getRequest().mutate()
				.header("from", "gateway").build();
		return chain.filter(exchange.mutate().request(req.mutate().build()).build());
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}
