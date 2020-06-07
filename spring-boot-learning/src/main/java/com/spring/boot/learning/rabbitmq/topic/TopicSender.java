package com.spring.boot.learning.rabbitmq.topic;

import com.spring.boot.learning.config.RabbitTopicConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yangyongkang
 * Date: 2020/1/6
 * Time: 11:04
 * Description:
 */
@Component
public class TopicSender {
	private RabbitTemplate rabbitTemplate;

	@Autowired
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessage() {
		String context = "开始匹配推送--" + RabbitTopicConfig.MESSAGE;
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("topicExchange", RabbitTopicConfig.MESSAGE, context);
	}

	public void sendMessages() {
		String context = "开始匹配推送--" + RabbitTopicConfig.MESSAGES;
		this.rabbitTemplate.convertAndSend("topicExchange", RabbitTopicConfig.MESSAGES, context);
	}

	public void sendInfo() {
		String context = "开始匹配推送--" + RabbitTopicConfig.INFO;
		this.rabbitTemplate.convertAndSend("topicExchange", RabbitTopicConfig.INFO, context);
	}
}
