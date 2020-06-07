package com.spring.boot.learning.config;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * @author: yangyongkang
 * Date: 2020/1/3
 * Time: 10:51
 * Description:
 * 解决RestTemplate对于相应500及403之类的错误直接排抛出异常
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
