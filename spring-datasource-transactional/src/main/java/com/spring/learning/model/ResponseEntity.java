package com.spring.learning.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * author: yangyk
 * date:2020/7/1 16:16
 * description:
 **/
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

	public static com.spring.learning.model.ResponseEntity build(Integer status, String msg, Object data) {
		return new com.spring.learning.model.ResponseEntity(status, msg, data);
	}

}
