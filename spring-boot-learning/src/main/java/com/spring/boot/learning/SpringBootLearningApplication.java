package com.spring.boot.learning;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * author: yangyk
 * date:2020/6/7 10:23
 * description:
 **/
@SpringBootApplication
@EnableTransactionManagement
@Log4j2
@MapperScan(basePackages = "com.spring.boot.learning.dao")
@EnableCaching
@EnableRabbit
public class SpringBootLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearningApplication.class, args);
	}

}
