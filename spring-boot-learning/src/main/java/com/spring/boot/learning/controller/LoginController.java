package com.spring.boot.learning.controller;

import com.spring.api.model.ResponseEntity;
import com.spring.api.tools.Constant;
import com.spring.boot.learning.model.SysUserModel;
import com.spring.boot.learning.security.JwtTokenProvider;
import com.spring.boot.learning.security.ResponseAuthenticationToken;
import com.spring.boot.learning.security.UserAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		if (!StringUtils.isEmpty(jwt)) {
			return ResponseEntity.build(200, "登录成功", new ResponseAuthenticationToken(authentication.getName(), jwt, authentication.getAuthorities()));
		}
		return ResponseEntity.build(Constant.ERROR_CODE, "登录失败,用户名或密码错误!", "");
	}
}
