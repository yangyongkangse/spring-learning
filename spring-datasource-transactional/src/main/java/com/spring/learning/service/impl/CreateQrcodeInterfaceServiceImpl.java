package com.spring.learning.service.impl;

import com.spring.learning.service.CreateQrcodeInterfaceService;
import com.spring.learning.service.QrcodeAsyncService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * author: yangyk
 * date: 2020/9/24 14:53
 * description:
 */
@Component
@Log4j2
public class CreateQrcodeInterfaceServiceImpl implements CreateQrcodeInterfaceService {

	private QrcodeAsyncService qrcodeAsyncService;

	@Autowired
	private void setQrcodeAsyncService(QrcodeAsyncService qrcodeAsyncService) {
		this.qrcodeAsyncService = qrcodeAsyncService;
	}


	@Override
	public void createQrcode() {
		qrcodeAsyncService.createQrcodeAsync();
	}
}
