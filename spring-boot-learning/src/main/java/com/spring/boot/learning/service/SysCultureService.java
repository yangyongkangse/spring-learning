package com.spring.boot.learning.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.boot.learning.model.SysCulture;

/**
 * @author: yangyongkang
 * date:2020/1/7
 * time:14:42
 * description:
 **/
public interface SysCultureService extends IService<SysCulture> {

	/**
	 * @author: yangyongkang
	 * date:2020/1/7
	 * time:15:12
	 * description:分页查询
	 **/
	Page<SysCulture> getSysCultureList(Page<SysCulture> page, SysCulture sysCulture);
}
