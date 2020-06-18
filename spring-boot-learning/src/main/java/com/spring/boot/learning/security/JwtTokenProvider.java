package com.spring.boot.learning.security;

import com.spring.api.tools.Constant;
import com.spring.boot.learning.exception.ResourceNotFoundException;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/17 9:42
 * @description: 生成及校验token
 */
@Component
@Log4j2
public class JwtTokenProvider {
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private int expiration;

	/**
	 * author: yangyk
	 * date:2020/6/17 9:56
	 * description:生成token
	 **/
	public String generateToken(Authentication authentication) {
		Date now = new Date();
		//过期时间
		Date expiryDate = new Date(now.getTime() + expiration);
		return Jwts.builder()
				.claim(Constant.ROLE_LOGIN, authentication.getAuthorities())
				.setSubject(authentication.getPrincipal().toString())
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	/**
	 * author: yangyk
	 * date:2020/6/17 9:56
	 * description:获取签名信息
	 **/
	public Claims getClaimByToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	/**
	 * author: yangyk
	 * date:2020/6/17 9:57
	 * description:验证token
	 **/
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			log.error("Invalid JWT signature");
			throw new ResourceNotFoundException(Constant.ERROR_CODE, "Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
			throw new ResourceNotFoundException(Constant.ERROR_CODE, "Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
			throw new ResourceNotFoundException(Constant.ERROR_CODE, "Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
			throw new ResourceNotFoundException(Constant.ERROR_CODE, "Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
			throw new ResourceNotFoundException(Constant.ERROR_CODE, "JWT claims string is empty");
		}
	}

	/**
	 * author: yangyk
	 * date:2020/6/18 9:50
	 * description:获取token的过期时间
	 **/
	public Date getExpirationDateFromToken(String token) {
		return getClaimByToken(token).getExpiration();
	}

	/**
	 * author: yangyk
	 * date:2020/6/18 9:50
	 * description:根据token获取username
	 **/
	public String getUsernameFromToken(String token) {
		return getClaimByToken(token).getSubject();
	}
}
