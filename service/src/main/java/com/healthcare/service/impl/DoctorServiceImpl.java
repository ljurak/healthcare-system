package com.healthcare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.model.entities.Doctor;
import com.healthcare.model.repo.DoctorRepo;
import com.healthcare.service.DoctorService;
import com.healthcare.service.dto.DoctorDTO;
import com.healthcare.service.dto.converter.DoctorConverter;
import com.healthcare.service.exception.DoctorNotFoundException;

@Service
@Transactional(readOnly = true)
public class DoctorServiceImpl implements DoctorService {

	private DoctorRepo doctorRepo;
	private DoctorConverter doctorConverter;
	
	@Autowired
	public DoctorServiceImpl(DoctorRepo doctorRepo, DoctorConverter doctorConverter) {
		this.doctorRepo = doctorRepo;
		this.doctorConverter = doctorConverter;
	}
	
	@Override
	public List<DoctorDTO> getDoctors() {
		List<Doctor> doctors = doctorRepo.findAllOrderByLastName();
		return doctorConverter.convertFromEntity(doctors);
	}
	
	@Override
	public DoctorDTO getDoctorById(Long id) {
		Doctor doctor = doctorRepo.findById(id)
				.orElseThrow(() -> new DoctorNotFoundException("Doctor with id: " + id + " does not exist"));
		return doctorConverter.convertFromEntity(doctor);
	}	

	@Override
	public List<DoctorDTO> getDoctorsByLastName(String lastName) {
		List<Doctor> doctors = doctorRepo.findByLastNameContainingIgnoreCaseOrderByLastName(lastName);
		return doctorConverter.convertFromEntity(doctors);
	}	
}
