package com.spring.boot.learning.security;


import com.spring.api.tools.Constant;
import com.spring.boot.learning.exception.ResourceNotFoundException;
import com.spring.boot.learning.model.SysRoleModel;
import com.spring.boot.learning.model.UserEntity;
import com.spring.boot.learning.service.SysRoleService;
import com.spring.boot.learning.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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

	private SysRoleService sysRoleService;

	@Autowired
	private void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	@Override
	public UserEntity loadUserByUsername(String username) {
		UserEntity userModel = sysUserService.loadUserByUsername(username);
		if (userModel == null) {
			//无需进行一场处理,由DaoAuthenticationProvider进行异常处理
			throw new ResourceNotFoundException(Constant.ERROR_CODE, username + " not found");
		}
		//获取用户角色
		List<SysRoleModel> roleModels = sysRoleService.getUserRoleInfo(userModel.getId());
		// 填充权限
		Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
		for (SysRoleModel role : roleModels) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return new UserEntity(userModel.getId(), userModel.getUsername(), userModel.getPassword(), userModel.getFullName(), authorities, null);
	}
}
