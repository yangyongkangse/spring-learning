package com.spring.learning.controller;

import com.spring.learning.annonation.SystemLog;
import com.spring.learning.model.ResponseEntity;
import com.spring.learning.service.CreateQrcodeInterfaceService;
import com.spring.learning.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: yangyk
 * Date: 2020/9/24 14:52
 * Description: 多数据源测试
 */
@RestController
@RequestMapping("/api/demo")
@Log4j2
@Api(tags = "demo演示-提供用户相关的Rest API")
public class DemoController {


	private com.spring.learning.service.CreateQrcodeInterfaceService createQrcodeInterfaceService;

	@Autowired
	private void setCreateQrcodeInterfaceService(CreateQrcodeInterfaceService createQrcodeInterfaceService) {
		this.createQrcodeInterfaceService = createQrcodeInterfaceService;
	}


	@GetMapping("/createQrcode")
	@ApiOperation("createQrcode")
	@SystemLog(description = "生成二维码")
	public ResponseEntity getBarcodeByHttp() {
		createQrcodeInterfaceService.createQrcode();
		return ResponseEntity.build(Constant.SUCCESS_CODE, Constant.SUCCESS_MSG, "");
	}
}
