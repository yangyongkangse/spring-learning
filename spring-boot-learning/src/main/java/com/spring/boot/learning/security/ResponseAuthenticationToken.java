package com.spring.boot.learning.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/17 11:02
 * @description:
 */
@Data
public class ResponseAuthenticationToken {
	private static final long serialVersionUID = 4900130631803037029L;
	private Object username;
	private Long userId;
	private String fullName;
	private String accessToken;
	private String tokenType = "Bearer ";
	private Collection<? extends GrantedAuthority> authorities = new HashSet<>();

	public ResponseAuthenticationToken(Object principal,Long userId, String fullName, String accessToken,
									   Collection<? extends GrantedAuthority> authorities) {
		this.username = principal;
		this.userId = userId;
		this.fullName = fullName;
		this.accessToken = accessToken;
		this.authorities=authorities;
	}

}
