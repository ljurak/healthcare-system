package com.healthcare.rest.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.healthcare.model.entities.Role;
import com.healthcare.model.entities.User;
import com.healthcare.model.repo.UserRepo;

@Service
public class UserServiceImpl implements UserDetailsService {
	
	private UserRepo userRepo;
	
	@Autowired	
	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("There is no such user: " + username));
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : user.getRoles()) {
			GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
			authorities.add(authority);
		}
		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
		return userDetails;
	}	
}
