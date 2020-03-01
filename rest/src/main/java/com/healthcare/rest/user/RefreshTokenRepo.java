package com.healthcare.rest.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface RefreshTokenRepo extends CrudRepository<RefreshToken, Long> {
	
	Optional<RefreshToken> findByToken(String token);
}
