package com.spring.dubbo.provider.controller;

import com.spring.api.model.ResponseEntity;
import com.spring.api.tools.Constant;
import com.spring.dubbo.provider.service.DubboProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: yangyk
 * date:2020/6/26 14:55
 * description:
 **/
@RestController
@RequestMapping("/api/dubbo")
public class DubboController {

	private DubboProviderService dubboProviderService;

	@Autowired
	private void setDubboProviderService(DubboProviderService dubboProviderService) {
		this.dubboProviderService = dubboProviderService;
	}


	@GetMapping(value = "/getPortInfo")
	public ResponseEntity getPortInfo(@RequestParam("content") String content) {
		String result = dubboProviderService.getDubboService(content);
		return ResponseEntity.build(200, Constant.SUCCESS_MSG, result);
	}
}
