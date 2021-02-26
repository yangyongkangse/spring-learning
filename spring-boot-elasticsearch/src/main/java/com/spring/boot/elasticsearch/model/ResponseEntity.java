package com.spring.boot.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity implements Serializable {

	private static final long serialVersionUID = 5727537381617248496L;
	/**
	 * 响应业务状态
	 */
	private Integer code;

	/**
	 * 响应消息
	 */
	private String msg;

	/**
	 * 响应中的数据
	 */
	private Object data;

	public static ResponseEntity build(Integer code, String msg, Object data) {
		return new ResponseEntity(code, msg, data);
	}

	public static ResponseEntity success(String msg, Object data) {
		return new ResponseEntity(0, msg, data);
	}

	public static ResponseEntity error(String msg, Object data) {
		return new ResponseEntity(1, msg, data);
	}
}
