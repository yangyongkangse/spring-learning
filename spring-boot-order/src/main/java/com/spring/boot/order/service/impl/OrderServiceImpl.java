package com.spring.boot.order.service.impl;

import com.spring.boot.order.model.OrderInfoModel;
import com.spring.boot.order.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/20 15:06
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

	@Override
	public OrderInfoModel getOrderInfo(Long id) {
		OrderInfoModel order=new OrderInfoModel();
		order.setId(id);
		order.setName("订单信息");
		return order;
	}
}
