package com.healthcare.rest.user.dto;

public class JwtTokenDTO {
	
	private String token;
	
	private String refreshToken;

	public JwtTokenDTO(String token, String refreshToken) {
		this.token = token;
		this.refreshToken = refreshToken;
	}

	public String getToken() {
		return token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}
}
