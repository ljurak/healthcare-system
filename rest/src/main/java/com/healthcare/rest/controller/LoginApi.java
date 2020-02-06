package com.healthcare.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthcare.rest.security.LoginDTO;
import com.healthcare.service.AuthenticationService;
import com.healthcare.service.dto.JwtTokenDTO;
import com.healthcare.service.utils.JwtUtils;

@RestController
@CrossOrigin
public class LoginApi {
	
	private AuthenticationManager authenticationManager;
	
	private JwtUtils jwtUtils;
	
	private AuthenticationService authenticationService;
	
	@Autowired
	public LoginApi(
			AuthenticationManager authenticationManager, 
			JwtUtils jwtUtils, 
			AuthenticationService authenticationService) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.authenticationService = authenticationService;
	}
	
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public JwtTokenDTO authenticate(@RequestBody @Valid LoginDTO loginDTO) {
		UsernamePasswordAuthenticationToken authenticationRequest = 
				new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenticationRequest);
		UserDetails principal = (UserDetails) authentication.getPrincipal();
		String token = jwtUtils.generateToken(principal);
		String refreshToken = authenticationService.createRefreshToken(principal.getUsername());
		return new JwtTokenDTO(token, refreshToken);
	}
	
	@PostMapping(path = "/token", consumes = MediaType.APPLICATION_JSON_VALUE)
	public JwtTokenDTO refreshToken(@RequestBody @Valid RefreshToken token) {
		return authenticationService.refreshAccessToken(token.getRefreshToken());
	}
	
	static class RefreshToken {
		@JsonProperty("refreshToken")
		private String refreshToken;
		
		String getRefreshToken() {
			return refreshToken;
		}	
	}
}
