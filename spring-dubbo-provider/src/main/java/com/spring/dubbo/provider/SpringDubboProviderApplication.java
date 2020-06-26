package com.spring.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* author: yangyk
* date:2020/6/26 15:22
* description:
 * 开启基于注解的dubbo功能（主要是包扫描@DubboComponentScan）也可以在配置文件中使用dubbo.scan.base-package来替代   @EnableDubbo
**/
@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.spring.dubbo.provider")
public class SpringDubboProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDubboProviderApplication.class, args);
	}

}
