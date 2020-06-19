package com.spring.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * ResponseEntity
 * Created with IntelliJ IDEA.
 *
 * @Author: yangyongkang
 * Date: 2018.05.19
 * Time: 14:45
 */
@Data
public class ResponseEntity implements Serializable {
	private static final long serialVersionUID = -2010412682466692050L;

	/**
	 * 响应业务状态
	 */
	private Integer status;

	/**
	 * 响应消息
	 */
	private String msg;

	/**
	 * 响应中的数据
	 */
	private Object data;

	public static ResponseEntity build(Integer status, String msg, Object data) {
		return new ResponseEntity(status, msg, data);
	}

	public static ResponseEntity success(Object data) {
		return new ResponseEntity(data);
	}

	public static ResponseEntity error(String msg, Object data) {
		return new ResponseEntity(msg, data);
	}

	private ResponseEntity(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	private ResponseEntity(String msg, Object data) {
		this.status = 500;
		this.msg = msg;
		this.data = data;
	}

	private ResponseEntity(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

}
