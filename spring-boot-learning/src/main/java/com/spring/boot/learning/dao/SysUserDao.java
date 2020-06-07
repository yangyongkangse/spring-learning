package com.spring.boot.learning.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.learning.model.SysUserModel;
import com.spring.boot.learning.model.UserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * author: yangyk
 * date:2020/5/31 11:26
 * description:
 **/
public interface SysUserDao extends BaseMapper<SysUserModel> {
	/**
	 * author: yangyk
	 * date:2020/5/31 11:06
	 * description:
	 **/
	SysUserModel loginVerification(SysUserModel user);

	/**
	 * author: yangyk
	 * date:2020/5/31 11:04
	 * description:
	 **/
	UserEntity loadUserByUsername(@Param("username") String username);
}
