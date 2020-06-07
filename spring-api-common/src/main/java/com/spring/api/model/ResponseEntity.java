package com.spring.api.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
	 * 定义jackson对象
	 */
	private static final ObjectMapper MAPPER = new ObjectMapper();

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

	/**
	 * author: yangyongkang
	 * date:2018.05.19
	 * time:18:29
	 * description:将json结果集转化为ExecuteResult对象
	 * 需要转换的对象是一个类
	 **/
	public static ResponseEntity formatToModel(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, ResponseEntity.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isObject()) {
				obj = MAPPER.readValue(data.traverse(), clazz);
			} else if (data.isTextual()) {
				obj = MAPPER.readValue(data.asText(), clazz);
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * author: yangyongkang
	 * date:2018.05.19
	 * time:18:28
	 * description:没有object对象的转化
	 **/
	public static ResponseEntity format(String json) {
		try {
			return MAPPER.readValue(json, ResponseEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * author: yangyongkang
	 * date:2018.05.19
	 * time:18:28
	 * description:Object是集合转化
	 * 需要转换的对象是一个list
	 **/
	public static ResponseEntity formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isArray() && data.size() > 0) {
				obj = MAPPER.readValue(data.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

}
