package com.spring.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Author: yangyk
 * Date: 2020/9/24 14:47
 * Description:
 */
@Component
public class RestTemplateConfig {
	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		RestTemplate restTemplate = new RestTemplate(factory);
		restTemplate.setErrorHandler(new com.spring.learning.config.RestTemplateThrowErrorHandler());
		return restTemplate;
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(300000);
		factory.setReadTimeout(300000);
		return factory;
	}
}
