package com.spring.boot.learning.security;

import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/17 9:41
 * @description: JWTAuthenticationFilter 从请求中获取JWT token, 验证, 加载token相关用户信息, 并传递给 Spring Security.
 */
@Log4j2
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	private JwtTokenProvider tokenProvider;

	private UserDetailsService userDetailsService;

	@Autowired
	private void setJwtTokenProvider(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Autowired
	private void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		String bearerToken = request.getHeader("Authorization");
		String jwt = null;
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenHead)) {
			jwt = bearerToken.substring(7);
		}
		if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
			Claims claims = tokenProvider.getClaimByToken(jwt);
			// 可以将用户名及角色信息都编码到 JWT claims中
			// 然后通过解析JWT 的 claims 对象来 创建UserDetails信息
			// 避免重复查询数据库
			UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}
}
