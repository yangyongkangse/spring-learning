package com.spring.boot.learning.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.boot.learning.model.SysRoleModel;

import java.util.List;

/**
* author: yangyk
* date:2020/6/19 10:32
* description:
**/
public interface SysRoleService extends IService<SysRoleModel> {

	/**
	 * author: yangyk
	 * date:2020/6/3 16:25
	 * description:获取用户所属角色
	 **/
	List<SysRoleModel> getUserRoleInfo(Long userId);
}
