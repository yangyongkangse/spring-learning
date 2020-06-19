package com.spring.boot.learning.rabbitmq.topic;

import com.spring.boot.learning.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: yangyongkang
 * Date: 2020/1/6
 * Time: 11:03
 * Description:
 */
@Component
public class TopicReceiver {

	@RabbitListener(queues = RabbitConfig.MESSAGE)
	@RabbitHandler
	public void processMessage(String message) {
		System.out.println("接收者 RabbitTopicConfig.MESSAGE," + message);
	}

	@RabbitListener(queues = RabbitConfig.MESSAGES)
	@RabbitHandler
	public void processMessages(String message) {
		System.out.println("接收者 RabbitTopicConfig.MESSAGES," + message);
	}

	@RabbitListener(queues = RabbitConfig.INFO)
	@RabbitHandler
	public void processInfo(String message) {
		System.out.println("接收者 RabbitTopicConfig.INFO," + message);
	}
}
