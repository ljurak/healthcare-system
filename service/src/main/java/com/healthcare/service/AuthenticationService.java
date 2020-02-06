package com.healthcare.service;

import com.healthcare.service.dto.JwtTokenDTO;

public interface AuthenticationService {
	
	/**
	 * Creates refresh token for a specified user.
	 * 
	 * @param username username
	 * @return refresh token
	 */
	String createRefreshToken(String username);
	
	/**
	 * Creates a pair of new access token and refresh token.
	 * 
	 * @param refreshToken refreshToken
	 * @return a pair of access token and refresh token
	 */
	JwtTokenDTO refreshAccessToken(String refreshToken);
}
