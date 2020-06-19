package com.spring.boot.learning.rabbitmq.topic;

import com.spring.boot.learning.config.RabbitConfig;
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
		String context = "开始匹配推送--" + RabbitConfig.MESSAGE;
		this.rabbitTemplate.convertAndSend("topicExchange", RabbitConfig.MESSAGE_ROUTER_KEY, context);
	}

	public void sendMessages() {
		String context = "开始匹配推送--" + RabbitConfig.MESSAGES;
		this.rabbitTemplate.convertAndSend("topicExchange", RabbitConfig.MESSAGES_ROUTER_KEY, context);
	}

	public void sendInfo() {
		String context = "开始匹配推送--" + RabbitConfig.INFO;
		this.rabbitTemplate.convertAndSend("topicExchange", RabbitConfig.INFO_ROUTER_KEY, context);
	}
}
