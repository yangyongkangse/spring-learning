package com.spring.boot.learning.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yangyongkang
 * Date: 2020/1/6
 * Time: 10:34
 * Description: 精准匹配交换机
 */
@Configuration
public class RabbitDirectConfig {
	public static final String QUEUE_NAME = "directHello";

	@Bean
	public Queue helloQueue() {
		return new Queue(QUEUE_NAME);
	}

	/**
	 * 配置默认的交换机模式，可以不需要配置以下
	 * @return
	 */
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange("directExchange");
	}

	/**
	 * 绑定一个key "direct"，当消息匹配到就会放到这个队列中
	 * @param helloQueue
	 * @param directExchange
	 * @return
	 */
	@Bean
	public Binding bindingExchangeDirectQueue(Queue helloQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(helloQueue).to(directExchange).with(QUEUE_NAME);
	}
}
