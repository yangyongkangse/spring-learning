package com.spring.boot.pay.service;

import com.spring.boot.pay.model.PayInfoModel;

/**
 * author: yangyk
 * date:2020/6/20 15:06
 * description:
 **/
public interface PayService {

	/**
	 * author: yangyk
	 * date:2020/6/20 15:06
	 * description:
	 **/
	PayInfoModel getPayInfo(Long id);
}
