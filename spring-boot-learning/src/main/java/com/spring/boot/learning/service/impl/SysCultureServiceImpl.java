package com.spring.boot.learning.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.boot.learning.dao.SysCultureDao;
import com.spring.boot.learning.model.SysCulture;
import com.spring.boot.learning.service.SysCultureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: yangyongkang
 * date:2020/1/7
 * time:14:42
 * description:
 **/
@Service
public class SysCultureServiceImpl extends ServiceImpl<SysCultureDao, SysCulture> implements SysCultureService {

	@Resource
	private SysCultureDao sysCultureDao;

	/**
	 * @param page
	 * @param sysCulture
	 * @author: yangyongkang
	 * date:2020/1/7
	 * time:15:12
	 * description:分页查询
	 */
	@Override
	public Page<SysCulture> getSysCultureList(Page<SysCulture> page, SysCulture sysCulture) {
		return page.setRecords(sysCultureDao.getSysCultureList(page, sysCulture));
	}
}
