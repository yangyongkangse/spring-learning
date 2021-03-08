package com.spring.boot.elasticsearch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.spring.boot.elasticsearch.model.Books;
import com.spring.boot.elasticsearch.model.ResponseEntity;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


	// 必须匹配，匹配会增加score。MatchQuery 这里表示"名字"必须包含“大卫”或者“David”其中一个, 多个匹配会增加其结果的score。
	// boolQueryBuilder.must(QueryBuilders.matchQuery("名字","大卫 David"))
    // 每一个query类型都可以添加多个子Query，其关系是“AND”。并且可以是BoolQuery
	//   boolQueryBuilder.must(QueryBuilders.boolQuery())
    // 必须匹配，匹配不影响score。TermsQuery 这里表示"id"必须等于“123”或者“321”
	//   boolQueryBuilder.filter(QueryBuilders.termsQuery("id","123"， "321"))
    // 非必须匹配，匹配增加score。
	//   boolQueryBuilder.should()
	// 设定最少匹配“should”的数量，如果没有规定“must”/“filter”默认值是1，有的话默认值是0
	//   boolQueryBuilder.minimalShouldMatch(1)
	// 必须不包含，不影响score。
	//   boolQueryBuilder.must_not()


	/**
	 * Author: yangyk
	 * Date: 2021/3/8 10:35
	 * Description: 查询所有文档
	 */
	@PostMapping(value = "/searchDocument")
	public ResponseEntity searchDocument(@RequestBody Books books) throws IOException {
		//设置查询索引库
		SearchRequest searchRequest = new SearchRequest(books.getIndexName());
		//构建搜索条件
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		//QueryBuilders.matchAllQuery查询所有
		searchSourceBuilder.sort("brandCode", SortOrder.ASC);
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		//要查询的出来的映射和不想要查询出来的映射
		searchSourceBuilder.fetchSource(new String[]{"brandName", "brandCode", "productName", "typeCode"}, new String[]{"materialCode", "materialDes"});
		//索引绑定条件
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = searchResponse.getHits();
		SearchHit[] hitsHits = hits.getHits();
		List<Books> booksList = new ArrayList<>();
		for (SearchHit searchHit : hitsHits) {
			String source = searchHit.getSourceAsString();
			Books books1 = objectMapper.readValue(source, Books.class);
			booksList.add(books1);
		}
		// 创建一个不序列化sex和weight的过滤器
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("indexName", "id");
		// 将上面的过滤器和ID为myFilter的注解进行关联
		FilterProvider filters = new SimpleFilterProvider().addFilter("jsonFilter", filter);
		return ResponseEntity.success("接口调用成功", objectMapper.writer(filters).writeValueAsString(booksList));
	}

	/**
	 * Author: yangyk
	 * Date: 2021/2/26 16:10
	 * Description: 分页查询文档信息
	 */
	@PostMapping(value = "/searchPageDocument")
	public ResponseEntity searchPageDocument(@RequestBody Books books) throws IOException {
		//设置查询索引库
		SearchRequest searchRequest = new SearchRequest(books.getIndexName());
		//构建搜索条件
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		//QueryBuilders.matchAllQuery查询所有
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		//设置分页参数
		int page = 1;
		int size = 5;
		int from = (page - 1) * size;
		searchSourceBuilder.from(from);
		searchSourceBuilder.size(size);
		//索引绑定条件
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = searchResponse.getHits();
		SearchHit[] hitsHits = hits.getHits();
		List<Books> booksList = new ArrayList<>();
		for (SearchHit searchHit : hitsHits) {
			String source = searchHit.getSourceAsString();
			Books books1 = objectMapper.readValue(source, Books.class);
			booksList.add(books1);
		}
		PageImpl<Books> pageList = new PageImpl<>(booksList);
		// 创建一个不序列化sex和weight的过滤器
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("indexName", "id");
		// 将上面的过滤器和ID为myFilter的注解进行关联
		FilterProvider filters = new SimpleFilterProvider().addFilter("jsonFilter", filter);
		return ResponseEntity.success("接口调用成功", objectMapper.writer(filters).writeValueAsString(pageList));
	}


	/**
	 * Author: yangyk
	 * Date: 2021/3/8 11:00
	 * Description: 精确查找
	 */
	@PostMapping(value = "/exectlySearch")
	public ResponseEntity exectlySearch(@RequestBody Books books) throws IOException {
		//设置查询索引库
		SearchRequest searchRequest = new SearchRequest(books.getIndexName());
		//设置查询范围
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		//根据品牌码查询
		//searchSourceBuilder.query(QueryBuilders.termQuery("brandCode", "139"));
		//根据id查询
		String[] ids = {"163412618101395456", "163418340226764800"};
		searchSourceBuilder.query(QueryBuilders.termsQuery("_id", ids));
		//索引绑定条件
		searchRequest.source(searchSourceBuilder);
		//开始搜素
		SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = searchResponse.getHits();
		SearchHit[] hitsHits = hits.getHits();
		List<Books> booksList = new ArrayList<>();
		for (SearchHit searchHit : hitsHits) {
			String source = searchHit.getSourceAsString();
			Books books1 = objectMapper.readValue(source, Books.class);
			booksList.add(books1);
		}
		PageImpl<Books> pageList = new PageImpl<>(booksList);
		// 创建一个不序列化sex和weight的过滤器
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("indexName", "id");
		// 将上面的过滤器和ID为myFilter的注解进行关联
		FilterProvider filters = new SimpleFilterProvider().addFilter("jsonFilter", filter);
		return ResponseEntity.success("接口调用成功", objectMapper.writer(filters).writeValueAsString(pageList));
	}


	/**
	 * Author: yangyk
	 * Date: 2021/3/8 11:13
	 * Description: match Query即全文检索，它的搜索方式是先将搜索字符串分词，再使用各各词条从索引中搜索。 match query与Term query区别是match query在搜索前先将搜索关键字分词，再拿各各词语去索引中搜索。
	 * operator：or 表示 只要有一个词在文档中出现则就符合条件，and表示每个词都在文档中出现则才符合条件。
	 */
	@PostMapping(value = "/matchSearch1")
	public ResponseEntity matchSearch1(@RequestBody Books books) throws IOException {
		//设置查询索引库
		SearchRequest searchRequest = new SearchRequest(books.getIndexName());
		//设置查询范围
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		//operation可以是AND或者OR，根据字面理解
		searchSourceBuilder.query(QueryBuilders
				.matchQuery("materialCode", "DH1VC0M09 CEAAF101B")
				.operator(Operator.OR));
		//索引绑定条件
		searchRequest.source(searchSourceBuilder);
		//开始搜素
		SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = searchResponse.getHits();
		SearchHit[] hitsHits = hits.getHits();
		List<Books> booksList = new ArrayList<>();
		for (SearchHit searchHit : hitsHits) {
			String source = searchHit.getSourceAsString();
			Books books1 = objectMapper.readValue(source, Books.class);
			booksList.add(books1);
		}
		PageImpl<Books> pageList = new PageImpl<>(booksList);
		// 创建一个不序列化sex和weight的过滤器
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("indexName", "id");
		// 将上面的过滤器和ID为myFilter的注解进行关联
		FilterProvider filters = new SimpleFilterProvider().addFilter("jsonFilter", filter);
		return ResponseEntity.success("接口调用成功", objectMapper.writer(filters).writeValueAsString(pageList));
	}

	/**
	 * Author: yangyk
	 * Date: 2021/3/8 13:21
	 * Description: minimum_should_match 可以指定文档匹配词的占比。
	 */
	@PostMapping(value = "/matchSearch2")
	public ResponseEntity matchSearch2(@RequestBody Books books) throws IOException {
		//设置查询索引库
		SearchRequest searchRequest = new SearchRequest(books.getIndexName());
		//设置查询范围
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders
				.matchQuery("materialCode", "AD0MR6E001")
				.minimumShouldMatch("3"));
		//索引绑定条件
		searchRequest.source(searchSourceBuilder);
		//开始搜素
		SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = searchResponse.getHits();
		SearchHit[] hitsHits = hits.getHits();
		List<Books> booksList = new ArrayList<>();
		for (SearchHit searchHit : hitsHits) {
			String source = searchHit.getSourceAsString();
			Books books1 = objectMapper.readValue(source, Books.class);
			booksList.add(books1);
		}
		PageImpl<Books> pageList = new PageImpl<>(booksList);
		// 创建一个不序列化sex和weight的过滤器
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("indexName", "id");
		// 将上面的过滤器和ID为myFilter的注解进行关联
		FilterProvider filters = new SimpleFilterProvider().addFilter("jsonFilter", filter);
		return ResponseEntity.success("接口调用成功", objectMapper.writer(filters).writeValueAsString(pageList));
	}

	/**
	 * Author: yangyk
	 * Date: 2021/3/8 14:15
	 * Description: 一次可以匹配多个字段进行查询,并可针对单个字段增加权重
	 */
	@PostMapping(value = "/multiSearch")
	public ResponseEntity multiSearch(@RequestBody Books books) throws IOException {
		//设置查询索引库
		SearchRequest searchRequest = new SearchRequest(books.getIndexName());
		//设置查询范围
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders
				//之前的Query构造方法都是字段名在前面，multiMatchQuery是字段名在后面
				.multiMatchQuery("空调 131", "brandCode", "productName", "typeName")
				//这里是将brandCode这个字段的相关度(权重)提高10倍
				.field("brandCode", 10));
		//索引绑定条件
		searchRequest.source(searchSourceBuilder);
		//开始搜素
		SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = searchResponse.getHits();
		SearchHit[] hitsHits = hits.getHits();
		List<Books> booksList = new ArrayList<>();
		for (SearchHit searchHit : hitsHits) {
			String source = searchHit.getSourceAsString();
			Books books1 = objectMapper.readValue(source, Books.class);
			booksList.add(books1);
		}
		PageImpl<Books> pageList = new PageImpl<>(booksList);
		// 创建一个不序列化sex和weight的过滤器
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("indexName", "id");
		// 将上面的过滤器和ID为myFilter的注解进行关联
		FilterProvider filters = new SimpleFilterProvider().addFilter("jsonFilter", filter);
		return ResponseEntity.success("接口调用成功", objectMapper.writer(filters).writeValueAsString(pageList));
	}


	/**
	 * Author: yangyk
	 * Date: 2021/3/8 14:23
	 * Description: 布尔查询对应于Lucene的BooleanQuery查询，实现将多个查询组合起来。
	 * must：表示必须，多个查询条件必须都满足。（通常使用must）
	 * should：表示或者，多个查询条件只要有一个满足即可。
	 * must_not：表示非。
	 */
	@PostMapping(value = "/mustSearch")
	public ResponseEntity mustSearch(@RequestBody Books books) throws IOException {
		//设置查询索引库
		SearchRequest searchRequest = new SearchRequest(books.getIndexName());
		//设置查询范围
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders
				//之前的Query构造方法都是字段名在前面，multiMatchQuery是字段名在后面
				.multiMatchQuery("空调 131", "brandCode", "productName", "typeName")
				//这里是将brandCode这个字段的相关度(权重)提高10倍
				.field("brandCode", 10);
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("brandName", "智家2");
		boolQuery.should(multiMatchQueryBuilder).should(termQueryBuilder);
		searchSourceBuilder.query(boolQuery);
		//索引绑定条件
		searchRequest.source(searchSourceBuilder);
		//开始搜素
		SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = searchResponse.getHits();
		SearchHit[] hitsHits = hits.getHits();
		List<Books> booksList = new ArrayList<>();
		for (SearchHit searchHit : hitsHits) {
			String source = searchHit.getSourceAsString();
			Books books1 = objectMapper.readValue(source, Books.class);
			booksList.add(books1);
		}
		PageImpl<Books> pageList = new PageImpl<>(booksList);
		// 创建一个不序列化sex和weight的过滤器
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("indexName", "id");
		// 将上面的过滤器和ID为myFilter的注解进行关联
		FilterProvider filters = new SimpleFilterProvider().addFilter("jsonFilter", filter);
		return ResponseEntity.success("接口调用成功", objectMapper.writer(filters).writeValueAsString(pageList));
	}
}
