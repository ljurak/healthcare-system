package com.healthcare.rest.doctor;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepo extends CrudRepository<Doctor, Long> {
	
	@Query("select d from Doctor d order by d.lastName")
	List<Doctor> findAllOrderByLastName();
	
	List<Doctor> findByLastNameContainingIgnoreCaseOrderByLastName(String lastName);
	
	@Query("select d from Doctor d where d.specialty.name = :specialty")
	List<Doctor> findBySpecialty(@Param("specialty") String specialty);
}
