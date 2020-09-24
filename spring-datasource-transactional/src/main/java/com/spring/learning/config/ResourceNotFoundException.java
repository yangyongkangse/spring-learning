package com.spring.learning.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Author: yangyk
 * Date: 2020/9/24 14:47
 * Description:
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
