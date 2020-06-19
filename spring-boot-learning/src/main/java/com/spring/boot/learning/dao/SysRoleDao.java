package com.spring.boot.learning.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.learning.model.SysRoleModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* author: yangyk
* date:2020/6/19 10:32
* description:
**/
public interface SysRoleDao extends BaseMapper<SysRoleModel> {

	/**
	 * author: yangyk
	 * date:2020/6/3 16:25
	 * description:获取用户所属角色
	 **/
	List<SysRoleModel> getUserRoleInfo(@Param("userId") Long userId);
}
