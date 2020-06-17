package com.spring.boot.learning.security;

import com.spring.api.tools.Constant;
import com.spring.boot.learning.exception.ResourceNotFoundException;
import com.spring.boot.learning.model.SysRoleModel;
import com.spring.boot.learning.model.UserEntity;
import com.spring.boot.learning.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/17 10:48
 * @description:
 */
@Component
public class UserAuthenticationManager implements AuthenticationManager {

	private UserDetailsService userDetailsService;

	@Autowired
	private void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	private SysRoleService sysRoleService;

	@Autowired
	private void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) {
		//这里简单校验用户名和密码
		UserEntity userEntity = (UserEntity) userDetailsService.loadUserByUsername(authentication.getName());
		if (userEntity == null) {
			throw new ResourceNotFoundException(Constant.ERROR_CODE, authentication.getName() + " not found");
		}
		boolean flag = new BCryptPasswordEncoder().matches(authentication.getCredentials().toString(), userEntity.getPassword());
		if (!flag) {
			throw new ResourceNotFoundException(Constant.ERROR_CODE, "用户名或密码错误!");
		}
		//获取用户角色
		List<SysRoleModel> roleModels = sysRoleService.getUserRoleInfo(userEntity.getId());
		// 填充权限
		Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
		for (SysRoleModel role : roleModels) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword(), authorities);
	}
}
