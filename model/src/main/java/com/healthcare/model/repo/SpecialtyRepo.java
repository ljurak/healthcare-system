package com.healthcare.model.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entities.Specialty;

@Repository
public interface SpecialtyRepo extends CrudRepository<Specialty, Long> {
	
	Specialty findByName(String name);
}
