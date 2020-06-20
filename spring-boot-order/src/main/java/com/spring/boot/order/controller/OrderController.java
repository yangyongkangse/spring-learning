package com.spring.boot.order.controller;

import com.spring.boot.order.model.OrderInfoModel;
import com.spring.boot.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: yangyk
 * date:2020/6/20 15:05
 * description:
 **/
@RestController
@RequestMapping("/api/order")
public class OrderController {


	private OrderService orderService;

	@Autowired
	private void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}


	@GetMapping(value = "/getOrderInfo")
	public OrderInfoModel getOrderInfo(@RequestParam("id") Long id) {
		return orderService.getOrderInfo(id);
	}
}
