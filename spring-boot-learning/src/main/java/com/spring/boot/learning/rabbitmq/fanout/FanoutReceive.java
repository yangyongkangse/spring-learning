package com.spring.boot.learning.rabbitmq.fanout;

import com.spring.boot.learning.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: yangyongkang
 * Date: 2020/1/6
 * Time: 10:58
 * Description:
 */
@Component
public class FanoutReceive {
	@RabbitListener(queues = RabbitConfig.QUEUE_BLOG)
	@RabbitHandler
	public void processBlog(String message) {
		System.out.println("接收者 FanoutReceiveBlog," + message);
	}

	@RabbitListener(queues = RabbitConfig.QUEUE_NAME)
	@RabbitHandler
	public void processName(String message) {
		System.out.println("接收者 FanoutReceiveName," + message);
	}
}
