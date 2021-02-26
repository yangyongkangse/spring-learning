package com.spring.boot.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * author: yangyk
 * date: 2021/2/25 14:11
 * description:elasticsearch客户端配置
 */
@Configuration
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {


	@Value("${elasticsearch.url}")
	private String url;

	@Value("${elasticsearch.port}")
	private String port;


	@Override
	@Bean
	public RestHighLevelClient elasticsearchClient() {
		ClientConfiguration clientConfiguration = ClientConfiguration.builder()
				.connectedTo(url + ":" + port)
				.build();
		return RestClients.create(clientConfiguration).rest();
	}
}
