package com.spring.boot.pay.service.impl;

import com.spring.boot.pay.model.PayInfoModel;
import com.spring.boot.pay.service.PayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/20 15:06
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PayServiceImpl implements PayService {

	@Override
	public PayInfoModel getPayInfo(Long id) {
		PayInfoModel pay = new PayInfoModel();
		pay.setId(id);
		pay.setName("付款信息");
		return pay;
	}
}
