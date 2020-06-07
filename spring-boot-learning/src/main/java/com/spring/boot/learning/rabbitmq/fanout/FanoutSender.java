package com.spring.boot.learning.rabbitmq.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yangyongkang
 * Date: 2020/1/6
 * Time: 11:00
 * Description:
 */
@Component
public class FanoutSender {

	private RabbitTemplate rabbitTemplate;

	@Autowired
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void send() {
		String context = "此消息在，广播模式或者订阅模式队列下，有 FanoutReceiveBlog FanoutReceiveName 可以收到 ";
		this.rabbitTemplate.convertAndSend("fanoutExchange", "", context);
	}
}
