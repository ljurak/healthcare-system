package com.healthcare.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entities.Patient;

@Repository
public interface PatientRepo extends CrudRepository<Patient, Long> {
	
	@Query("select p from Patient p order by p.lastName")
	List<Patient> findAllOrderByLastName();
	
	List<Patient> findByLastNameContainingIgnoreCaseOrderByLastName(String lastname);
}
