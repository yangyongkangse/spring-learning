package com.spring.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringNacosConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringNacosConfigApplication.class, args);
	}

}
