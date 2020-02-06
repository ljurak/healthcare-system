package com.healthcare.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.model.entities.RefreshToken;
import com.healthcare.model.entities.User;
import com.healthcare.model.repo.RefreshTokenRepo;
import com.healthcare.model.repo.UserRepo;
import com.healthcare.service.AuthenticationService;
import com.healthcare.service.dto.JwtTokenDTO;
import com.healthcare.service.exception.RefreshTokenException;
import com.healthcare.service.exception.UserNotFoundException;
import com.healthcare.service.utils.JwtUtils;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private UserRepo userRepo;
	
	private RefreshTokenRepo refreshTokenRepo;
	
	private JwtUtils jwtUtils;
	
	@Value("${jwt.refreshToken.expirationTime}")
	private int expirationTime;
	
	@Autowired
	public AuthenticationServiceImpl(UserRepo userRepo, RefreshTokenRepo refreshTokenRepo, JwtUtils jwtUtils) {
		this.userRepo = userRepo;
		this.refreshTokenRepo = refreshTokenRepo;
		this.jwtUtils = jwtUtils;
	}

	@Override
	public String createRefreshToken(String username) {
		User user = userRepo.findByUsername(username).orElseThrow(
				() -> new UserNotFoundException("User with username: " + username + " does not exist"));
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setCreateTime(LocalDateTime.now());
		refreshToken.setUser(user);
		refreshToken = refreshTokenRepo.save(refreshToken);
		return refreshToken.getToken();
	}

	@Override
	public JwtTokenDTO refreshAccessToken(String refreshToken) {
		RefreshToken token = refreshTokenRepo.findByToken(refreshToken).orElseThrow(
				() -> new RefreshTokenException("Refresh token is no longer valid"));
		LocalDateTime expirationDate = token.getCreateTime().plusSeconds(expirationTime);
		if (expirationDate.isBefore(LocalDateTime.now())) {
			refreshTokenRepo.delete(token);
			throw new RefreshTokenException("Refresh token is no longer valid");
		}
		
		User user = token.getUser(); 
		String accessToken = jwtUtils.generateToken(user);
		String newRefreshToken = createRefreshToken(user.getUsername());
		refreshTokenRepo.delete(token);
		return new JwtTokenDTO(accessToken, newRefreshToken);
	}
}
