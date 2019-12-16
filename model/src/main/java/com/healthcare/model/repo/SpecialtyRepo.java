package com.healthcare.model.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entities.Specialty;

@Repository
public interface SpecialtyRepo extends CrudRepository<Specialty, Long> {
	
	@Query("select s from Specialty s")
	List<Specialty> findAll();
	
	Optional<Specialty> findByName(String name);
}
