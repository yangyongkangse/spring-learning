package com.spring.dubbo.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.spring.api.model.ResponseEntity;
import com.spring.api.tools.Constant;
import com.spring.dubbo.consumer.fallback.FallBackHandle;
import com.spring.dubbo.consumer.handle.BlockExceptionHandle;
import com.spring.dubbo.provider.service.DubboProviderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: yangyk
 * date:2020/6/26 15:25
 * description:
 **/
@RestController
@RequestMapping("/api/consumer/dubbo")
public class DubboController {

	@DubboReference
	private DubboProviderService dubboProviderService;

	@GetMapping(value = "/getPortInfo")
	@SentinelResource(value = "hotKey", blockHandlerClass = BlockExceptionHandle.class, blockHandler = "handleException", fallbackClass = FallBackHandle.class, fallback = "handleNotPermissionException")
	public ResponseEntity getPortInfo(@RequestParam("content") String content) {
		String result = dubboProviderService.getDubboService(content);
		return ResponseEntity.build(200, Constant.SUCCESS_MSG, result);
	}
}
