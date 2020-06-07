package com.spring.boot.learning.rabbitmq.topic;

import com.spring.boot.learning.config.RabbitTopicConfig;
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
@RabbitListener(queues = RabbitTopicConfig.MESSAGES)
public class TopicReceiver2 {
	@RabbitHandler
	public void process(String message) {
		System.out.println("接收者 RabbitTopicConfig.MESSAGES," + message);
	}
}
