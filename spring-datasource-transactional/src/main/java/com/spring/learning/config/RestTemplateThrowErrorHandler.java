package com.spring.learning.config;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Author: yangyk
 * Date: 2020/9/24 14:47
 * Description: 解决RestTemplate对于相应500及403之类的错误直接排抛出异常
 */
public class RestTemplateThrowErrorHandler implements ResponseErrorHandler {
	@Override
	public boolean hasError(ClientHttpResponse clientHttpResponse) {
		return false;
	}

	@Override
	public void handleError(ClientHttpResponse clientHttpResponse) {

	}
}
