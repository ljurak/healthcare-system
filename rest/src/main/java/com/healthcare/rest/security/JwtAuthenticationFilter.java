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

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {	
	
	private ObjectMapper objectMapper;
	private AuthenticationManager authenticationManager;
	private JwtUtils jwtUtils;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils, ObjectMapper objectMapper) {
		super(authenticationManager);
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.objectMapper = objectMapper;
	}
	
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		UserDetails principal;
		try {
			principal = jwtUtils.parseToken(request);
		} catch (JWTVerificationException ex) {
			ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Your token is invalid. Please log in again.");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(apiError));
			chain.doFilter(request, response);
			return;
		}
		
		if (principal == null) {
			chain.doFilter(request, response);
			return;
		}
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(principal.getUsername(), null, principal.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);		
	}	
}
