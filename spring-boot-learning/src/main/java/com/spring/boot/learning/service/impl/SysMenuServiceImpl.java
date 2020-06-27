package com.spring.boot.learning.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.api.tools.Constant;
import com.spring.boot.learning.dao.SysMenuDao;
import com.spring.boot.learning.model.SysMenuModel;
import com.spring.boot.learning.service.SysMenuService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

	/**
	 * author: yangyk
	 * date:2020/6/26 16:46
	 * description:
	 * Cacheable:添加缓存操作,如果存在缓存直接读取,不再调用方法
	 * CachePut:更新缓存
	 * CacheEvict:清除缓存
	 **/
	@Cacheable(cacheNames = "system", key = "#root.methodName")
	@Override
	public List<SysMenuModel> getUserMenuInfo(String username) {
		List<SysMenuModel> menuModels = sysMenuDao.getUserMenuInfo(username);
		List<SysMenuModel> menu = new ArrayList<>();
		for (SysMenuModel item : menuModels) {
			if (Constant.STRING0.equals(item.getParentId())) {
				//查找子集
				List<SysMenuModel> child = new ArrayList<>();
				for (SysMenuModel vars : menuModels) {
					if (vars.getParentId().equals(item.getId().toString())) {
						child.add(vars);
					}
				}
				item.setChild(child);
				menu.add(item);
			}
		}
		return menu;
	}
}
