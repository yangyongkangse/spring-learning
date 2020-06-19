package com.spring.boot.learning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.boot.learning.model.SysMenuModel;

import java.util.List;

/**
* author: yangyk
* date:2020/6/19 10:32
* description:
**/
public interface SysMenuService extends IService<SysMenuModel> {

	/**
	 * author: yangyk
	 * date:2020/6/3 16:41
	 * description:
	 **/
	List<SysMenuModel> getUserMenuInfo(String username);
}
