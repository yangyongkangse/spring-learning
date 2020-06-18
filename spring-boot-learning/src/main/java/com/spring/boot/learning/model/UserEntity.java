package com.spring.boot.learning.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/3 15:50
 * @description: UserEntity
 */
@Data
@NoArgsConstructor
public class UserEntity implements UserDetails {
	private static final long serialVersionUID = -5098806571800730024L;
	private Long id;
	private String username;
	private String fullName;
	private String password;
	private List<SysMenuModel> menus = new ArrayList<>();
	private List<SysRoleModel> roles = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 填充权限
		Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
		for (SysRoleModel role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
