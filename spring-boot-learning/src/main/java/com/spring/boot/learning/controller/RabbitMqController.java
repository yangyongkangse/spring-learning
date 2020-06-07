package com.spring.boot.learning.controller;

import com.spring.boot.learning.rabbitmq.direct.DirectSender;
import com.spring.boot.learning.rabbitmq.fanout.FanoutSender;
import com.spring.boot.learning.rabbitmq.topic.TopicSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yangyongkang
 * Date: 2020/1/6
 * Time: 11:08
 * Description: rabbitmq测试
 */
@Api(tags = "rabbitmq测试")
@RestController
@RequestMapping("/api/rabbitmq")
@Log4j2
public class RabbitMqController {
	private DirectSender directSender;

	@Autowired
	public void setDirectSender(DirectSender directSender) {
		this.directSender = directSender;
	}

	private FanoutSender fanoutSender;

	@Autowired
	public void setFanoutSender(FanoutSender fanoutSender) {
		this.fanoutSender = fanoutSender;
	}

	private TopicSender topicSender;

	@Autowired
	public void setTopicSender(TopicSender topicSender) {
		this.topicSender = topicSender;
	}


	@ApiOperation("精确匹配测试")
	@GetMapping(value = "/direct")
	@ResponseBody
	public void testDirect() {
		directSender.sendHello();
	}

	@ApiOperation("广播测试")
	@GetMapping(value = "/fanout")
	@ResponseBody
	public void testFanout() {
		fanoutSender.send();
	}

	@ApiOperation("通配符匹配测试1")
	@GetMapping(value = "/topic1")
	@ResponseBody
	public void testTopic1() {
		topicSender.sendMessage();
	}

	@ApiOperation("通配符匹配测试2")
	@GetMapping(value = "/topic2")
	@ResponseBody
	public void testTopic2() {
		topicSender.sendMessages();
	}

	@ApiOperation("通配符匹配测试3")
	@GetMapping(value = "/topic3")
	@ResponseBody
	public void testTopic3() {
		topicSender.sendInfo();
	}
}
