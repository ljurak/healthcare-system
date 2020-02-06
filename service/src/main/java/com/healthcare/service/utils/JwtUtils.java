package com.healthcare.service.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.healthcare.model.entities.Role;

@Component
public class JwtUtils {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expirationTime}")
	private int expirationTime;
	
	public UserDetails parseToken(String token) {
		DecodedJWT decodedToken = JWT.require(Algorithm.HMAC256(secret))
				.build()
				.verify(token);
		String username = decodedToken.getSubject();
		String[] authorities = decodedToken.getClaim("scopes").asArray(String.class);
		
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
		
		return null;
	}
	
	public String generateToken(UserDetails user) {		
		String[] authorities = user.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.toArray(String[]::new);
		
		String token = JWT.create()
				.withSubject(user.getUsername())
				.withArrayClaim("scopes", authorities)
				.withExpiresAt(new Date(System.currentTimeMillis() + expirationTime * 1000))
				.sign(Algorithm.HMAC256(secret));
		
		return token;
	}
	
	public String generateToken(com.healthcare.model.entities.User user) {
		String[] authorities = user.getRoles().stream()
				.map(Role::getName)
				.toArray(String[]::new);
		
		String token = JWT.create()
				.withSubject(user.getUsername())
				.withArrayClaim("scopes", authorities)
				.withExpiresAt(new Date(System.currentTimeMillis() + expirationTime * 1000))
				.sign(Algorithm.HMAC256(secret));
				
		return token;
	}
}
