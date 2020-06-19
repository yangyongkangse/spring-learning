package com.spring.boot.learning.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.learning.model.SysMenuModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* author: yangyk
* date:2020/6/19 10:32
* description:
**/
public interface SysMenuDao extends BaseMapper<SysMenuModel> {

	/**
	 * author: yangyk
	 * date:2020/6/3 16:41
	 * description:
	 **/
	List<SysMenuModel> getUserMenuInfo(@Param("username") String username);
}
