package com.spring.boot.learning.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yangyongkang
 * Date: 2020/1/6
 * Time: 10:37
 * Description: 通配符匹配
 */
@Configuration
public class RabbitTopicConfig {
	public final static String MESSAGE = "mq.message";

	public final static String MESSAGES = "mq.message.s";

	public final static String INFO = "mq.info";

	@Bean
	public Queue queueMessage() {
		return new Queue(RabbitTopicConfig.MESSAGE);
	}

	@Bean
	public Queue queueMessages() {
		return new Queue(RabbitTopicConfig.MESSAGES);
	}

	@Bean
	public Queue queueInfo() {
		return new Queue(RabbitTopicConfig.INFO);
	}

	/**
	 * 交换机(Exchange) 描述：接收消息并且转发到绑定的队列，交换机不存储消息
	 */
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange("topicExchange");
	}

	/**
	 * 綁定队列 queueMessage() 到 topicExchange 交换机,路由键只接受完全匹配 mq.message 的队列接受者可以收到消息
	 */
	@Bean
	Binding bindingExchangeMessage(Queue queueMessage, TopicExchange topicExchange) {
		return BindingBuilder.bind(queueMessage).to(topicExchange).with("mq.message");
	}

	/**
	 * 綁定队列 queueMessages() 到 topicExchange 交换机,路由键只要是以 mq.message 开头的队列接受者可以收到消息
	 */
	@Bean
	Binding bindingExchangeMessages(Queue queueMessages, TopicExchange topicExchange) {
		return BindingBuilder.bind(queueMessages).to(topicExchange).with("mq.message.#");
	}

	/**
	 * 綁定队列 queueInfo() 到 topicExchange 交换机,路由键只要是以 mq 开头的队列接受者可以收到消息
	 */
	@Bean
	Binding bindingExchangeInfo(Queue queueInfo, TopicExchange topicExchange) {
		return BindingBuilder.bind(queueInfo).to(topicExchange).with("mq.#");
	}
}
