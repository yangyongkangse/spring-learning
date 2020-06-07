package com.spring.boot.learning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/5/23 14:33
 * @description: SecurityConfig
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	private UserDetailsService userDetailsService;

	@Autowired
	private void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
	 * author: yangyk
	 * date:2020/6/3 11:26
	 * description:确定密码解析方式
	 **/
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * author: yangyk
	 * date:2020/6/3 12:42
	 * description:配置DaoAuthenticationProvider
	 **/
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider
				= new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	/**
	 * author: yangyk
	 * date:2020/6/3 11:59
	 * description:配置AuthenticationManagerBuilder用户校验逻辑
	 **/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//配置不需要登录验证
		http.formLogin().and().authorizeRequests()
				//允许跨域OPTIONS访问,防止401
				.antMatchers(HttpMethod.OPTIONS, "**").permitAll()
				//允许直接访问的资源
				.antMatchers("/api/login/check","/api/system/getUserMenuInfo").permitAll()
				.anyRequest().authenticated()
				.and()
				.cors()
				.and()
				.csrf().disable();
	}
}
