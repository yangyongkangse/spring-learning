package com.spring.boot.learning.security;


import com.spring.api.tools.Constant;
import com.spring.boot.learning.exception.ResourceNotFoundException;
import com.spring.boot.learning.model.UserEntity;
import com.spring.boot.learning.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/5/31 10:58
 * @description: CustomUserDetailsService
 */
@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	private SysUserService sysUserService;

	@Autowired
	private void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}


	@Override
	public UserEntity loadUserByUsername(String username) {
		UserEntity userModel = sysUserService.loadUserByUsername(username);
		if (userModel == null) {
			throw new ResourceNotFoundException(Constant.ERROR_CODE, username + " not found");
		}
		return userModel;
	}
}
