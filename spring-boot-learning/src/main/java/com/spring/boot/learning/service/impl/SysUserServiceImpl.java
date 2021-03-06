package com.spring.boot.learning.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.boot.learning.dao.SysUserDao;
import com.spring.boot.learning.model.SysUserModel;
import com.spring.boot.learning.model.UserEntity;
import com.spring.boot.learning.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * author: yangyk
 * date:2020/5/31 11:23
 * description:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserModel> implements SysUserService {

	@Resource
	private SysUserDao sysUserDao;


	@Override
	public UserEntity loadUserByUsername(String username) {
		return sysUserDao.loadUserByUsername(username);
	}
}
