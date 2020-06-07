package com.spring.boot.learning.controller;

import com.spring.api.tools.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Base64;
import java.util.Date;

/**
 * @author: yangyongkang
 * Date: 2020/1/3
 * Time: 11:04
 * Description:
 */
@Api(tags = "鉴权文件上传")
@RestController
@RequestMapping("/api/oss")
@Log4j2
public class RestTemplateFileUploadController {

	private RestTemplate restTemplate;
	@Autowired
	private void setRestTemplate(RestTemplate restTemplate){
		this.restTemplate=restTemplate;
	}
	/**
	* @author: yangyongkang
	* date:2020/1/3
	* time:11:04
	* description:开启文件上传
	*
	**/
	@ApiOperation("鉴权开启文件上传")
	@GetMapping("/fileUpload")
	public String upload() {
		log.info("======== 开始准备权限编码======");
		String authorization = Base64.getEncoder().encodeToString("MjA2MDdjNmQwZjE2NDI1YNmY3YWI3MjdkNTM=:OGY4MDM2ZDTllYTM1YmJjMTEwMDFhN2U=".getBytes());
		log.info("======== 权限编码准备完毕======");
		String filePath = "/Users/mr.l/Desktop/file.txt";
		FileSystemResource resource = new FileSystemResource(new File(filePath));
		//header参数
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);
		MediaType type = MediaType.parseMediaType("multipart/form-data");
		headers.setContentType(type);
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("file", resource);
		param.add("appName", "application");
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, headers);
		log.info("======== 接口调用所需参数准备完毕======");
		Date start = new Date();
		log.info("======== 开始上传======");
		ResponseEntity<String> responseEntity = restTemplate.exchange(Constant.FILE_UPLOAD_URL, HttpMethod.POST, httpEntity, String.class);
		Date end = new Date();
		log.info("======== 上传结束======");
		long between = end.getTime() - start.getTime();
		long day = between / (24 * 60 * 60 * 1000);
		long hour = (between / (60 * 60 * 1000) - day * 24);
		long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String uploadTime = day + "天" + hour + "小时" + min + "分" + s + "秒";
		log.info("======== 耗时" + uploadTime + "======");
		System.out.println("+++++++++++++++++++++++++++++++++++++" + responseEntity.getBody());
		return responseEntity.getBody() + "-----------" + uploadTime;
	}
}
