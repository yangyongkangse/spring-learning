package com.spring.boot.learning.controller;

import com.spring.boot.learning.tools.GuppyMongoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: yangyongkang
 * Date: 2020/1/3
 * Time: 11:07
 * Description:
 */
@Api(tags = "文件上传")
@RestController
@RequestMapping("/api/file")
@Log4j2
public class MongoDbLocalFileUploadController {

	private GuppyMongoUtils guppyMongoUtils;

	@Autowired
	public void setGuppyMongoUtils(GuppyMongoUtils guppyMongoUtils) {
		this.guppyMongoUtils = guppyMongoUtils;
	}

	@ApiOperation("开启文件上传")
	@GetMapping(value = "/uploadFile")
	@ResponseBody
	public String uploadFile() throws IOException {
		String filePath = "E:\\cosmo\\cosmosom-generator.zip";
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		log.info("======== 准备自动命名并保存文档: " + file.getName() + " ======");
		String mongoId = guppyMongoUtils.storeDoc(fis, file.getName());
		log.info("======== 文档: " + file.getName() + " 已成功保存[store]: ObjectID(" + mongoId + ") ========");
		return mongoId;
	}

	@ApiOperation("下载文件")
	@GetMapping(value = "/downFile")
	public void downFile(String fileId, HttpServletResponse response) throws IOException {
		guppyMongoUtils.downloadDoc(fileId, response);
	}
}
