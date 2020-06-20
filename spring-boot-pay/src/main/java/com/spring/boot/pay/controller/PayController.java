package com.spring.boot.pay.controller;

import com.spring.api.model.ResponseEntity;
import com.spring.api.tools.Constant;
import com.spring.boot.order.model.OrderInfoModel;
import com.spring.boot.pay.model.PayInfoModel;
import com.spring.boot.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * author: yangyk
 * date:2020/6/20 15:06
 * description:
 **/
@RestController
@RequestMapping("/api/pay")
public class PayController {

	@Value("${service.nacos-order-service}")
	private String orderServiceUrl;

	private RestTemplate restTemplate;

	@Autowired
	private void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private PayService payService;

	@Autowired
	private void setPayService(PayService payService) {
		this.payService = payService;
	}


	@GetMapping(value = "/getPayInfo")
	public ResponseEntity getPayInfo(@RequestParam("id") Long id) {
		PayInfoModel payInfo = payService.getPayInfo(id);
		return ResponseEntity.build(200, Constant.SUCCESS_MSG, payInfo);
	}

	@GetMapping(value = "/getOrderInfo")
	public ResponseEntity getOrderInfo(@RequestParam("id") Long id) {
		org.springframework.http.ResponseEntity<OrderInfoModel> entity = restTemplate.getForEntity(orderServiceUrl + "/api/order/getOrderInfo?id=" + id, OrderInfoModel.class);
		return ResponseEntity.build(200, Constant.SUCCESS_MSG, entity.getBody());
	}
}
