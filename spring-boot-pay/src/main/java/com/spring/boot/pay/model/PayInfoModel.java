package com.spring.boot.pay.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/20 15:28
 * @description:
 */
@Data
public class PayInfoModel implements Serializable {
	private static final long serialVersionUID = -5020928074390475683L;
	private Long id;
	private String name;
}
