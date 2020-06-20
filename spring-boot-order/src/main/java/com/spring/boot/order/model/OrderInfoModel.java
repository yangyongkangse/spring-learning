package com.spring.boot.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/20 15:16
 * @description:
 */
@Data
public class OrderInfoModel implements Serializable {
	private static final long serialVersionUID = 912104586178147107L;
	private Long id;
	private String name;
}
