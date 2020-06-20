package com.spring.boot.learning.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/19 14:44
 * @description:
 */
@Component
public class RabbitConfig {

	public static final String DIRECT_HELLO = "directHello";
	public static final String DIRECT_ROUTER_KEY = "direct.hello";
	public final static String QUEUE_BLOG = "blog";
	public final static String QUEUE_NAME = "yangyk";
	public final static String MESSAGE = "mq.message";
	public static final String MESSAGE_ROUTER_KEY = "mq.message";
	public final static String MESSAGES = "mq.message.s";
	public static final String MESSAGES_ROUTER_KEY = "mq.message.#";
	public final static String INFO = "mq.info";
	public static final String INFO_ROUTER_KEY = "mq.#";

	private AmqpAdmin amqpAdmin;

	@Autowired
	private void setAmqpAdmin(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}


	/**
	 * author: yangyk
	 * date:2020/6/19 14:54
	 * description:创建交换机
	 **/
	@Bean
	@Order(1)
	public void createExchange() {
		//创建点对点交换机   精准匹配 通过路由key匹配
		amqpAdmin.declareExchange(new DirectExchange("directExchange"));
		//创建广播式交换机  fanout转发消息最快
		amqpAdmin.declareExchange(new FanoutExchange("fanoutExchange"));
		//创建主题交换机    模糊匹配
		amqpAdmin.declareExchange(new TopicExchange("topicExchange"));
	}

	/**
	 * author: yangyk
	 * date:2020/6/19 15:07
	 * description:创建队列
	 **/
	@Bean
	@Order(2)
	public void createQueue() {
		//创建点对点 精准匹配队列
		amqpAdmin.declareQueue(new Queue(DIRECT_HELLO, true));
		//创建广播式队列
		amqpAdmin.declareQueue(new Queue(QUEUE_BLOG, true));
		amqpAdmin.declareQueue(new Queue(QUEUE_NAME, true));
		//创建主题队列   模糊匹配
		amqpAdmin.declareQueue(new Queue(MESSAGE, true));
		amqpAdmin.declareQueue(new Queue(MESSAGES, true));
		amqpAdmin.declareQueue(new Queue(INFO, true));
	}

	/**
	 * author: yangyk
	 * date:2020/6/19 15:13
	 * description:开始队列绑定
	 * param:destination 目的地
	 * param:destinationType 目的地类型(队列,交换机)
	 * param:exchange 交换机
	 * param:routingKey 路由key
	 * param:arguments 参数头信息
	 * new Binding(String destination,DestinationType destinationType,String exchange,String routingKey,Map<String,Object> arguments)
	 **/
	@Bean
	@Order(3)
	public void startBinging() {
		//点对点绑定
		amqpAdmin.declareBinding(new Binding(DIRECT_HELLO, Binding.DestinationType.QUEUE,"directExchange",DIRECT_ROUTER_KEY,null));
		//广播式队列绑定
		amqpAdmin.declareBinding(new Binding(QUEUE_BLOG, Binding.DestinationType.QUEUE,"fanoutExchange","",null));
		amqpAdmin.declareBinding(new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE,"fanoutExchange","",null));
		//模糊匹配绑定
		amqpAdmin.declareBinding(new Binding(MESSAGE, Binding.DestinationType.QUEUE,"topicExchange",MESSAGE_ROUTER_KEY,null));
		amqpAdmin.declareBinding(new Binding(MESSAGES, Binding.DestinationType.QUEUE,"topicExchange",MESSAGES_ROUTER_KEY,null));
		amqpAdmin.declareBinding(new Binding(INFO, Binding.DestinationType.QUEUE,"topicExchange",INFO_ROUTER_KEY,null));
	}
}
