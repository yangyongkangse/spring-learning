package com.spring.boot.learning.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.boot.learning.dao.SysRoleDao;
import com.spring.boot.learning.model.SysRoleModel;
import com.spring.boot.learning.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author yangyk
 * @since 2020-06-03
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleModel> implements SysRoleService {

	@Resource
	private SysRoleDao sysRoleDao;

	@Override
	public List<SysRoleModel> getUserRoleInfo(Long userId) {
		return sysRoleDao.getUserRoleInfo(userId);
	}
}
