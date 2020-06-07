package com.spring.boot.learning.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.api.model.ResponseEntity;
import com.spring.api.tools.Constant;
import com.spring.boot.learning.model.SysProduct;
import com.spring.boot.learning.service.SysProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
* @author: yangyongkang
* date:2020/1/7
* time:16:04
* description:
*
**/
@RestController
@RequestMapping("/api/sysProduct")
public class SysProductController {
	private SysProductService sysProductService;

	@Autowired
	public void setSysProductService(SysProductService sysProductService) {
		this.sysProductService = sysProductService;
	}

	@ApiOperation("查询产品列表")
	@GetMapping("/getProductList")
	public ResponseEntity getProductList(@RequestParam(value = "current", required = false, defaultValue = Constant.DEFAULT_CURRENT_PAGE) Integer current,
										 @RequestParam(value = "size", required = false, defaultValue = Constant.DEFAULT_PAGESIZE) Integer size, SysProduct sysProduct) {
		Page<SysProduct> page = new Page<>(current, size);
		Page<SysProduct> result = sysProductService.getProductList(page, sysProduct);
		return ResponseEntity.build(200, "OK!", result);
	}
}
