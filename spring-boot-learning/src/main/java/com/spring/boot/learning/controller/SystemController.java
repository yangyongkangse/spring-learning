package com.spring.boot.learning.controller;

import com.spring.api.model.ResponseEntity;
import com.spring.api.tools.Constant;
import com.spring.boot.learning.model.SysMenuModel;
import com.spring.boot.learning.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/3 18:47
 * @description:
 */
@RestController
@RequestMapping("/api/system")
public class SystemController {


	private SysMenuService sysMenuService;

	@Autowired
	private void setSysMenuService(SysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}


	@GetMapping(value = "/getUserMenuInfo")
	public ResponseEntity getUserMenuInfo(@RequestParam("username") String username) {
		//获取权限菜单
		List<SysMenuModel> menuModels = sysMenuService.getUserMenuInfo(username);
		List<SysMenuModel> menu = new ArrayList<>();
		for (SysMenuModel item : menuModels) {
			if (Constant.STRING0.equals(item.getParentId())) {
				//查找子集
				List<SysMenuModel> child = new ArrayList<>();
				for (SysMenuModel vars : menuModels) {
					if (vars.getParentId().equals(item.getId().toString())) {
						child.add(vars);
					}
				}
				item.setChild(child);
				menu.add(item);
			}
		}
		return ResponseEntity.build(200, Constant.SUCCESS_MSG, menu);
	}
}
