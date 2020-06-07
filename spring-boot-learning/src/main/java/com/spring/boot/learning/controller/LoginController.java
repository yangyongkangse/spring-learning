package com.spring.boot.learning.controller;

import com.spring.api.model.ResponseEntity;
import com.spring.boot.learning.model.SysUserModel;
import com.spring.boot.learning.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
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

	private RedisTemplate<String, Serializable> redisCacheTemplate;

	@Autowired
	public void setRedisTemplate(RedisTemplate<String, Serializable> redisCacheTemplate) {
		this.redisCacheTemplate = redisCacheTemplate;
	}

	private UserDetailsService userDetailsService;

	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
	 * author: yangyongkang
	 * date:2018/8/22
	 * time:13:58
	 * description:登录验证
	 **/
	@PostMapping(value = "/check")
	public ResponseEntity loginCheck(@RequestBody SysUserModel user) {
		UserEntity result = (UserEntity) userDetailsService.loadUserByUsername(user.getUsername());
		if (null != result) {
			redisCacheTemplate.opsForValue().set("login:user:info" + result.getUsername(), result, 2, TimeUnit.HOURS);
			return ResponseEntity.build(200, "登录成功", result);
		}
		return ResponseEntity.build(500, "登录失败,用户名或密码错误!", "");
	}
}
