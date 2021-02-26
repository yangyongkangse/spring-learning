package com.spring.boot.elasticsearch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.elasticsearch.model.Books;
import com.spring.boot.elasticsearch.model.ResponseEntity;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * author: yangyk
 * date: 2021/2/26 16:17
 * description: 查询方式
 */
@RestController
@RequestMapping("/api/els")
public class SearchController {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Resource(name = "elasticsearchClient")
	private RestHighLevelClient highLevelClient;


	/**
	 * Author: yangyk
	 * Date: 2021/2/26 16:10
	 * Description: 查询文档信息
	 */
	@PostMapping(value = "/searchDocument")
	public ResponseEntity searchDocument(@RequestBody Books books) throws IOException {
		SearchRequest searchRequest = new SearchRequest(books.getIndexName());
		//构建搜索条件
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		//QueryBuilders.matchAllQuery查询所有
		MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
		searchSourceBuilder.query(matchAllQueryBuilder);
		//每页10个数据
		searchSourceBuilder.size(10);
		//起始位置从10开始
		searchSourceBuilder.from(10);
		searchSourceBuilder.timeout(new TimeValue(3, TimeUnit.SECONDS));
		searchRequest.source(searchSourceBuilder);
		SearchResponse search = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		List<Books> list = new ArrayList<>();
		for (SearchHit searchHit : search.getHits().getHits()) {
			System.out.println("searchHit = " + searchHit);
		}
		return ResponseEntity.success("接口调用成功", search);
	}
}
