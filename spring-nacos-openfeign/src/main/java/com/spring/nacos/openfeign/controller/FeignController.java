package com.spring.nacos.openfeign.controller;

import com.spring.api.model.ResponseEntity;
import com.spring.api.tools.Constant;
import com.spring.nacos.openfeign.client.PayServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/21 9:00
 * @description:
 */
@RestController
@RequestMapping("/api/feign")
public class FeignController {

	private PayServiceClient payServiceClient;

	@Autowired
	private void setPayServiceClient(PayServiceClient payServiceClient) {
		this.payServiceClient = payServiceClient;
	}


	@GetMapping(value = "/getPayInfo")
	public ResponseEntity getPayInfo(@RequestParam("id") Long id) {
		ResponseEntity payInfo = payServiceClient.getPayInfo(id);
		return ResponseEntity.build(200, Constant.SUCCESS_MSG, payInfo);
	}


}
