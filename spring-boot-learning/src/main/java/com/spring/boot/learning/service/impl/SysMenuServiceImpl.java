package com.spring.boot.learning.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.boot.learning.dao.SysMenuDao;
import com.spring.boot.learning.model.SysMenuModel;
import com.spring.boot.learning.service.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* author: yangyk
* date:2020/6/19 10:32
* description:
**/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuModel> implements SysMenuService {

	@Resource
	private SysMenuDao sysMenuDao;

	@Override
	public List<SysMenuModel> getUserMenuInfo(String username) {
		return sysMenuDao.getUserMenuInfo(username);
	}
}
