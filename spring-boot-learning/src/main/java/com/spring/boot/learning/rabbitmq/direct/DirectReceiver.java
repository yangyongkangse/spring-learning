package com.spring.boot.learning.rabbitmq.direct;

import com.spring.boot.learning.config.RabbitDirectConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: yangyongkang
 * Date: 2020/1/6
 * Time: 10:54
 * Description:
 */
@Component
public class DirectReceiver {

	@RabbitListener(queues = RabbitDirectConfig.QUEUE_NAME)
	public void receiveMessage(String message) {
		System.out.println("接收者 DirectReceiver------" + message);
	}
}
