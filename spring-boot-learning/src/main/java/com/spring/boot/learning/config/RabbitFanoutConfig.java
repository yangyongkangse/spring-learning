package com.spring.boot.learning.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yangyongkang
 * Date: 2020/1/6
 * Time: 10:36
 * Description: 广播交换机
 */
@Configuration
public class RabbitFanoutConfig {

	public final static String QUEUE_BLOG = "blog";
	public final static String QUEUE_NAME = "yangyk";

	@Bean
	public Queue queueBlog() {
		return new Queue(RabbitFanoutConfig.QUEUE_BLOG);
	}

	@Bean
	public Queue queueName() {
		return new Queue(RabbitFanoutConfig.QUEUE_NAME);
	}

	/**
	 * 任何发送到Fanout Exchange的消息都会被转发到与该Exchange绑定(Binding)的所有队列上。
	 */
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanoutExchange");
	}

	@Bean
	public Binding bindingExchangeQueueBlog(Queue queueBlog, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(queueBlog).to(fanoutExchange);
	}

	@Bean
	public Binding bindingExchangeQueueName(Queue queueName, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(queueName).to(fanoutExchange);
	}
}
