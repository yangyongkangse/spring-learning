package com.spring.nacos.openfeign.client;

import com.spring.api.model.ResponseEntity;
import com.spring.nacos.openfeign.fallback.PayServiceRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Created with IntelliJ IDEA
 * @date: 2020/6/21 9:02
 * @description: FeignClient注解指定被调用的服务端名称
 * fallback 进行远程调用的熔断和降级处理
 * Primary 确定要注入的bean
 */
@FeignClient(name = "spring-boot-pay", fallback = PayServiceRemoteHystrix.class)
@Primary
public interface PayServiceClient {
	/**
	 * author: yangyk
	 * date:2020/6/21 9:14
	 * description: 此处的接口地址为服务提供方的接口地址
	 **/
	@RequestMapping(value = "/api/pay/getPayInfo")
	ResponseEntity getPayInfo(@RequestParam("id") Long id);


}
