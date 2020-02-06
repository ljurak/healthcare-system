package com.healthcare.rest.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.rest.exception.ApiError;
import com.healthcare.service.utils.JwtUtils;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {	
	
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String TOKEN_PREFIX = "Bearer ";
	
	private ObjectMapper objectMapper;
	
	private AuthenticationManager authenticationManager;
	
	private JwtUtils jwtUtils;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, 
			JwtUtils jwtUtils, 
			ObjectMapper objectMapper) {
		super(authenticationManager);
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.objectMapper = objectMapper;
	}
	
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = extractToken(request);
		if (token == null) {
			chain.doFilter(request, response);
			return;
		}
		
		UserDetails principal;
		try {
			principal = jwtUtils.parseToken(token);
		} catch (JWTVerificationException ex) {
			createInvalidTokenResponse(request, response, chain);
			return;
		}
		
		if (principal == null) {
			createInvalidTokenResponse(request, response, chain);
			return;
		}
		
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);		
	}
	
	public String extractToken(HttpServletRequest request) {
		String authHeader = request.getHeader(AUTHORIZATION_HEADER);
		if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
			return authHeader.replace(TOKEN_PREFIX, "");
		}
		return null;
	}
	
	public void createInvalidTokenResponse(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Your token is invalid. Please log in again.");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(apiError));
		chain.doFilter(request, response);
	}
}
