package com.spring.boot.learning.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/17 12:33
 * @description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7157365335770975645L;
	private Integer code;

	private String message;

	public ResourceNotFoundException(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
