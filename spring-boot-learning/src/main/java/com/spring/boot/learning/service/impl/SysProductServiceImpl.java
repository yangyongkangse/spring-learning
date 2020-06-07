package com.spring.boot.learning.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.boot.learning.dao.SysProductDao;
import com.spring.boot.learning.model.SysProduct;
import com.spring.boot.learning.service.SysProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: yangyongkang
 * date:2020/1/7
 * time:16:08
 * description:
 **/
@Service
public class SysProductServiceImpl extends ServiceImpl<SysProductDao, SysProduct> implements SysProductService {

	@Resource
	private SysProductDao sysProductDao;

	/**
	 * @param page
	 * @param sysProduct
	 * @author: yangyongkang
	 * date:2020/1/7
	 * time:16:11
	 * description:分页查询
	 */
	@Override
	public Page<SysProduct> getProductList(Page<SysProduct> page, SysProduct sysProduct) {
		return page.setRecords(sysProductDao.getSysProductList(page, sysProduct));
	}
}
