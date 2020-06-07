package com.spring.boot.learning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.boot.learning.model.SysUserModel;
import com.spring.boot.learning.model.UserEntity;

/**
* author: yangyk
* date:2020/5/31 11:23
* description:
**/
public interface SysUserService extends IService<SysUserModel> {

	/**
	 * author: yangyk
	 * date:2020/5/31 11:03
	 * description:
	 **/
	UserEntity loadUserByUsername(String username);
}
