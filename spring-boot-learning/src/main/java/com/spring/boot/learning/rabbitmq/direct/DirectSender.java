package com.spring.boot.learning.rabbitmq.direct;

import com.spring.boot.learning.config.RabbitDirectConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yangyongkang
 * Date: 2020/1/6
 * Time: 10:55
 * Description:
 */
@Component
public class DirectSender {
	private RabbitTemplate rabbitTemplate;

	@Autowired
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendHello() {
		String context = "此消息在，默认的交换机模式队列下，有 DirectReceiver 可以收到";
		rabbitTemplate.convertAndSend(RabbitDirectConfig.QUEUE_NAME, context);
	}
}
