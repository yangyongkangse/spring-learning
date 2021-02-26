package com.spring.boot.elasticsearch.utils;

/**
 * author: yangyk
 * date: 2021/2/25 17:05
 * description:
 */
public class Util {

	/**
	 * Author: yangyk
	 * Date: 2021/2/25 17:07
	 * Description: 获取id
	 */
	public static long getSnowFlakeId() {
		SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1);
		return idWorker.nextId();
	}
}
