package com.spring.learning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.spring.learning.dao")
@EnableAsync
public class SpringDatasourceTransactionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDatasourceTransactionalApplication.class, args);
	}

}
