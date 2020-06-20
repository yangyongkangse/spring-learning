package com.spring.boot.order.service;

import com.spring.boot.order.model.OrderInfoModel;

/**
 * author: yangyk
 * date:2020/6/20 15:06
 * description:
 **/
public interface OrderService {

	/**
	 * author: yangyk
	 * date:2020/6/20 15:06
	 * description:
	 **/
	OrderInfoModel getOrderInfo(Long id);
}
