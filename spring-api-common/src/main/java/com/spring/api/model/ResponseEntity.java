package com.spring.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity implements Serializable {

	private static final long serialVersionUID = 8393453747357048638L;
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

}
