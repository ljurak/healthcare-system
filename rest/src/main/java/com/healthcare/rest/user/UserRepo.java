package com.healthcare.rest.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepo extends CrudRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
}
