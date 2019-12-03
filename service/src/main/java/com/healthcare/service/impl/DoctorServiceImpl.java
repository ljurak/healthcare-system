package com.healthcare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.model.entities.Doctor;
import com.healthcare.model.repo.DoctorRepo;
import com.healthcare.service.DoctorService;
import com.healthcare.service.exception.DoctorException;

@Service
@Transactional(readOnly = true)
public class DoctorServiceImpl implements DoctorService {

	private DoctorRepo doctorRepo;
	
	@Autowired
	public DoctorServiceImpl(DoctorRepo doctorRepo) {
		this.doctorRepo = doctorRepo;
	}
	
	@Override
	public Doctor getDoctorById(Long id) {
		return doctorRepo.findById(id)
				.orElseThrow(() -> new DoctorException("Doctor with id: " + id + " does not exist"));
	}	
}
