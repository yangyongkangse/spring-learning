package com.spring.boot.learning.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.boot.learning.model.SysProduct;

/**
* @author: yangyongkang
* date:2020/1/7
* time:16:06
* description:
*
**/
public interface SysProductService extends IService<SysProduct> {

	/**
	* @author: yangyongkang
	* date:2020/1/7
	* time:16:11
	* description:分页查询
	*
	**/
	Page<SysProduct> getProductList(Page<SysProduct> page, SysProduct sysProduct);
}
