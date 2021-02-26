package com.spring.boot.elasticsearch.model;

import lombok.Data;

import java.util.List;

/**
 * author: yangyk
 * date: 2021/2/26 15:09
 * description:
 */
@Data
public class BookList {

	private String indexName;
	private List<Books> books;
}
