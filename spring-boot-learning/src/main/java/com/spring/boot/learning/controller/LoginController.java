package com.spring.boot.learning.controller;

import com.spring.api.model.ResponseEntity;
import com.spring.boot.learning.model.SysUserModel;
import com.spring.boot.learning.model.UserEntity;
import com.spring.boot.learning.security.JwtTokenProvider;
import com.spring.boot.learning.security.ResponseAuthenticationToken;
import com.spring.boot.learning.security.UserAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 登录验证
 * Created with IntelliJ IDEA.
 *
 * @Author: yangyongkang
 * Date: 2018/8/22
 * Time: 14:49
 */

@RestController
@RequestMapping("/api/login")
public class LoginController {

	private UserAuthenticationManager authenticationManager;

	@Autowired
	public void setAuthenticationManager(UserAuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	private JwtTokenProvider tokenProvider;

	@Autowired
	public void setJwtTokenProvider(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	private UserDetailsService userDetailsService;

	@Autowired
	private void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	private RedisTemplate<String, Serializable> redisCacheTemplate;

	@Autowired
	public void setRedisTemplate(RedisTemplate<String, Serializable> redisCacheTemplate) {
		this.redisCacheTemplate = redisCacheTemplate;
	}

	/**
	 * author: yangyongkang
	 * date:2018/8/22
	 * time:13:58
	 * description:登录验证
	 **/
	@PostMapping(value = "/check")
	public ResponseEntity loginCheck(@RequestBody SysUserModel user) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						user.getUsername(),
						user.getPassword()
				)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		UserEntity userEntity = (UserEntity) userDetailsService.loadUserByUsername(authentication.getName());
		if (!StringUtils.isEmpty(jwt) && userEntity != null) {
			redisCacheTemplate.opsForValue().set("login:user:info" + userEntity.getUsername(), userEntity, 2, TimeUnit.HOURS);
			return ResponseEntity.build(200, "登录成功", new ResponseAuthenticationToken(authentication.getName(), userEntity.getId(), userEntity.getFullName(), jwt, authentication.getAuthorities()));
		}
		return ResponseEntity.build(500, "登录失败,用户名或密码错误!", "");
	}
}
