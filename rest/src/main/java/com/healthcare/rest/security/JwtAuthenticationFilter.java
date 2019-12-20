package com.healthcare.rest.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {	
	
	private AuthenticationManager authenticationManager;
	private JwtUtils jwtUtils;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		super(authenticationManager);
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}
	
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		UserDetails principal = jwtUtils.parseToken(request);
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
