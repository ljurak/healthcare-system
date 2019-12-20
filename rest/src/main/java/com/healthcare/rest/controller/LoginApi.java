package com.healthcare.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthcare.rest.security.JwtUtils;
import com.healthcare.rest.security.LoginDTO;

@RestController
@RequestMapping("/login")
public class LoginApi {
	
	private AuthenticationManager authenticationManager;
	private JwtUtils jwtUtils;
	
	@Autowired
	public LoginApi(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public JwtToken authenticate(@RequestBody @Valid LoginDTO loginDTO) {
		UsernamePasswordAuthenticationToken authenticationRequest = 
				new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenticationRequest);
		String token = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());
		return new JwtToken(token);
	}
	
	static class JwtToken {
		@JsonProperty("token")
		private final String token;
		
		JwtToken(String token) {
			this.token = token;
		}
		
		String getToken() {
			return token;
		}
	}
}
