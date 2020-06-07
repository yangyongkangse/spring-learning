package com.spring.boot.learning.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.boot.learning.dao.SysMenuDao;
import com.spring.boot.learning.model.SysMenuModel;
import com.spring.boot.learning.service.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author yangyk
 * @since 2020-06-03
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuModel> implements SysMenuService {

	@Resource
	private SysMenuDao sysMenuDao;

	@Override
	public List<SysMenuModel> getUserMenuInfo(Long userId) {
		return sysMenuDao.getUserMenuInfo(userId);
	}
}
