package com.healthcare.rest.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtils {
	
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String TOKEN_PREFIX = "Bearer ";
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expirationTime}")
	private int expirationTime;
	
	public UserDetails parseToken(HttpServletRequest request) {
		String authHeader = request.getHeader(AUTHORIZATION_HEADER);
		if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
			DecodedJWT decodedToken = JWT.require(Algorithm.HMAC256(secret))
					.build()
					.verify(authHeader.replace(TOKEN_PREFIX, ""));
			String username = decodedToken.getSubject();
			String[] authorities = decodedToken.getClaim("auth").asArray(String.class);
			
			List<GrantedAuthority> authoritiesList = new ArrayList<>();
			if (authorities != null) {
				for (String authority : authorities) {
					authoritiesList.add(new SimpleGrantedAuthority(authority));
				}
			}
			
			if (username != null) {
				UserDetails user = new User(username, "", authoritiesList);
				return user;
			}
		}		
		return null;
	}
	
	public String generateToken(UserDetails user) {		
		String[] authorities = user.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.toArray(String[]::new);
		
		String token = JWT.create()
				.withSubject(user.getUsername())
				.withArrayClaim("auth", authorities)
				.withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
				.sign(Algorithm.HMAC256(secret));
		
		return token;
	}
}
