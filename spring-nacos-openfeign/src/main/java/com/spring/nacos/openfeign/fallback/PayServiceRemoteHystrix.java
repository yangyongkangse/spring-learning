package com.spring.nacos.openfeign.fallback;

import com.spring.api.model.ResponseEntity;
import com.spring.nacos.openfeign.client.PayServiceClient;
import org.springframework.stereotype.Component;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/21 9:06
 * @description:
 */
@Component
public class PayServiceRemoteHystrix implements PayServiceClient {
	@Override
	public ResponseEntity getPayInfo(Long id) {
		return ResponseEntity.build(500,"请求超时", null);
	}
}
