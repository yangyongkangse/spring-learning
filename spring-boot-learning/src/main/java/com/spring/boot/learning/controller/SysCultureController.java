package com.spring.boot.learning.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.boot.learning.model.ResponseEntity;
import com.spring.boot.learning.model.SysCulture;
import com.spring.boot.learning.service.SysCultureService;
import com.spring.boot.learning.tools.Constant;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yangyongkang
 * date:2020/1/7
 * time:14:42
 * description:
 **/
@RestController
@RequestMapping("/api/sysCulture")
public class SysCultureController {

	private SysCultureService sysCultureService;

	@Autowired
	public void setSysCultureService(SysCultureService sysCultureService) {
		this.sysCultureService = sysCultureService;
	}

	/**
	 * author: yangyongkang
	 * date:2018/8/22
	 * time:13:58
	 * description:查询企业文化列表
	 */
	@ApiOperation("查询企业文化列表")
	@GetMapping("/getCultureList")
	public ResponseEntity getCultureList(@RequestParam(value = "current", required = false, defaultValue = Constant.DEFAULT_CURRENT_PAGE) Integer current,
										 @RequestParam(value = "size", required = false, defaultValue = Constant.DEFAULT_PAGESIZE) Integer size, SysCulture sysCulture) {
		Page<SysCulture> page = new Page<>(current, size);
		Page<SysCulture> result = sysCultureService.getSysCultureList(page, sysCulture);
		return ResponseEntity.build(200, "OK!", result);
	}

}
