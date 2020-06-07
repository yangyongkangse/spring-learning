package com.spring.boot.learning.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.boot.learning.model.SysProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author: yangyongkang
* date:2020/1/7
* time:16:05
* description:
*
**/
public interface SysProductDao extends BaseMapper<SysProduct> {

	/**
	* @author: yangyongkang
	* date:2020/1/7
	* time:16:09
	* description:
	*
	**/
	List<SysProduct> getSysProductList(Page<SysProduct> page, @Param("sysProduct") SysProduct sysProduct);
}
