package com.healthcare.model.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entities.Doctor;

@Repository
public interface DoctorRepo extends CrudRepository<Doctor, Long> {

}
