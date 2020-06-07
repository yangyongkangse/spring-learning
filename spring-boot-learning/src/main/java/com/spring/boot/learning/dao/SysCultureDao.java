package com.spring.boot.learning.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.boot.learning.model.SysCulture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author: yangyongkang
* date:2020/1/7
* time:14:42
* description:
*
**/
public interface SysCultureDao extends BaseMapper<SysCulture> {

	/**
	* @author: yangyongkang
	* date:2020/1/7
	* time:15:30
	* description:分页查询
	*
	**/
	List<SysCulture>  getSysCultureList(Page<SysCulture> page,@Param("sysCulture") SysCulture sysCulture);


}
