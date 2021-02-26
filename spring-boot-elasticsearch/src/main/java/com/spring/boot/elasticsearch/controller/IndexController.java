package com.spring.boot.elasticsearch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.spring.boot.elasticsearch.model.BookList;
import com.spring.boot.elasticsearch.model.Books;
import com.spring.boot.elasticsearch.model.ResponseEntity;
import com.spring.boot.elasticsearch.utils.Util;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * author: yangyk
 * date: 2021/2/25 14:20
 * description:
 */
@RestController
@RequestMapping("/api/index")
public class IndexController {


	private final ObjectMapper objectMapper = new ObjectMapper();

	@Resource(name = "elasticsearchClient")
	private RestHighLevelClient highLevelClient;


	/**
	 * Author: yangyk
	 * Date: 2021/2/25 15:26
	 * Description: 创建索引
	 */
	@GetMapping(value = "/createIndex")
	public ResponseEntity createIndex(String indexName) throws IOException {
		//创建索引请求
		CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
		//创建执行请求
		CreateIndexResponse createIndexResponse = highLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
		return ResponseEntity.success("接口调用成功", objectMapper.writeValueAsString(createIndexResponse));
	}


	/**
	 * Author: yangyk
	 * Date: 2021/2/25 15:30
	 * Description: 获取索引
	 */
	@GetMapping(value = "/getIndex")
	public ResponseEntity getIndex(String indexName) throws IOException {
		GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
		boolean exists = highLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
		return ResponseEntity.success("接口调用成功", exists);
	}

	/**
	 * Author: yangyk
	 * Date: 2021/2/25 15:32
	 * Description: 删除索引
	 */
	@GetMapping(value = "/deleteIndex")
	public ResponseEntity deleteIndex(String indexName) throws IOException {
		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
		AcknowledgedResponse delete = highLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
		return ResponseEntity.success("接口调用成功", delete.isAcknowledged());
	}

	/**
	 * Author: yangyk
	 * Date: 2021/2/25 17:07
	 * Description: 添加文档信息
	 */
	@PostMapping(value = "/addDocument")
	public ResponseEntity addDocument(@RequestBody Books books) throws IOException {
		// 创建一个不序列化sex和weight的过滤器
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("indexName", "id");
		// 将上面的过滤器和ID为myFilter的注解进行关联
		FilterProvider filters = new SimpleFilterProvider().addFilter("jsonFilter", filter);
		long snowFlakeId = Util.getSnowFlakeId();
		IndexRequest indexRequest = new IndexRequest(books.getIndexName());
		indexRequest.id(Long.toString(snowFlakeId));
		indexRequest.timeout(TimeValue.timeValueSeconds(1));
		indexRequest.timeout("1s");
		indexRequest.source(objectMapper.writer(filters).writeValueAsString(books), XContentType.JSON);
		//发送请求
		IndexResponse indexResponse = highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
		return ResponseEntity.success("接口调用成功", indexResponse);
	}

	/**
	 * Author: yangyk
	 * Date: 2021/2/26 13:28
	 * Description: 删除文档信息
	 */
	@GetMapping(value = "/deleteDocument")
	public ResponseEntity deleteDocument(String indexName, String id) throws IOException {
		DeleteRequest deleteRequest = new DeleteRequest(indexName, id);
		deleteRequest.timeout("1s");
		DeleteResponse deleteResponse = highLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
		return ResponseEntity.success("接口调用成功", deleteResponse);
	}


	/**
	 * Author: yangyk
	 * Date: 2021/2/26 14:12
	 * Description: 根据索引获取文档
	 */
	@GetMapping(value = "/getDocument")
	public ResponseEntity getDocument(String indexName, String id) throws IOException {
		GetRequest getRequest = new GetRequest(indexName, id);
		boolean exists = highLevelClient.exists(getRequest, RequestOptions.DEFAULT);
		if (exists) {
			//获取文档信息
			GetResponse documentFields = highLevelClient.get(getRequest, RequestOptions.DEFAULT);
			return ResponseEntity.success("接口调用成功", documentFields);
		} else {
			return ResponseEntity.error("获取文档失败", null);
		}

	}

	/**
	 * Author: yangyk
	 * Date: 2021/2/26 15:03
	 * Description: 更新文档信息
	 */
	@PostMapping(value = "/updateDocument")
	public ResponseEntity updateDocument(@RequestBody Books books) throws IOException {
		// 创建一个不序列化sex和weight的过滤器
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("indexName", "id");
		// 将上面的过滤器和ID为myFilter的注解进行关联
		FilterProvider filters = new SimpleFilterProvider().addFilter("jsonFilter", filter);
		UpdateRequest updateRequest = new UpdateRequest(books.getIndexName(), books.getId());
		updateRequest.timeout("1s");
		updateRequest.doc(objectMapper.writer(filters).writeValueAsString(books), XContentType.JSON);
		UpdateResponse update = highLevelClient.update(updateRequest, RequestOptions.DEFAULT);
		return ResponseEntity.success("接口调用成功", update);
	}


	/**
	 * Author: yangyk
	 * Date: 2021/2/26 15:07
	 * Description: 批量插入文档信息
	 */
	@PostMapping(value = "/batchInsertDocument")
	public ResponseEntity batchInsertDocument(@RequestBody BookList bookList) throws IOException {
		// 创建一个不序列化sex和weight的过滤器
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("indexName", "id");
		// 将上面的过滤器和ID为myFilter的注解进行关联
		FilterProvider filters = new SimpleFilterProvider().addFilter("jsonFilter", filter);
		BulkRequest bulkRequest = new BulkRequest();
		bulkRequest.timeout("5s");
		if (!CollectionUtils.isEmpty(bookList.getBooks())) {
			for (Books book : bookList.getBooks()) {
				long snowFlakeId = Util.getSnowFlakeId();
				bulkRequest.add(new IndexRequest(bookList.getIndexName()).id(Long.toString(snowFlakeId)).source(objectMapper.writer(filters).writeValueAsString(book), XContentType.JSON));
			}
		}
		BulkResponse bulk = highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
		return ResponseEntity.success("接口调用成功", bulk);
	}
}
