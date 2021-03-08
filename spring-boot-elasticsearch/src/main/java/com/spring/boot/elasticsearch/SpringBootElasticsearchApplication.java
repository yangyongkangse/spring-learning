package com.spring.boot.elasticsearch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SpringBootElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootElasticsearchApplication.class, args);
	}

}
