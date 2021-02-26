package com.spring.boot.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;

/**
 * author: yangyk
 * date: 2021/2/25 15:14
 * description:
 */
@Data
@JsonFilter("jsonFilter")
public class Books {

	private String indexName;
	private String id;
	private String brandName;
	private String brandCode;
	private String productName;
	private String typeCode;
	private String typeName;
	private String materialCode;
	private String materialDes;
	private String modelCode;
	private String modelName;
	private String barCode;
	private String createDate;
}
