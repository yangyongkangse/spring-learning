package com.spring.boot.learning.rabbitmq.fanout;

import com.spring.boot.learning.config.RabbitFanoutConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: yangyongkang
 * Date: 2020/1/6
 * Time: 11:00
 * Description:
 */
@Component
@RabbitListener(queues = RabbitFanoutConfig.QUEUE_NAME)
public class FanoutReceiveName {
	@RabbitHandler
	public void process(String message) {
		System.out.println("接收者 FanoutReceiveName," + message);
	}
}
