package com.healthcare.model.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entities.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
}
