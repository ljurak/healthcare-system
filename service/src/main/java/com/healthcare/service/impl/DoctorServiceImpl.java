package com.healthcare.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.model.entities.Doctor;
import com.healthcare.model.repo.DoctorRepo;
import com.healthcare.service.DoctorService;
import com.healthcare.service.dto.DoctorDTO;
import com.healthcare.service.dto.converter.DTOConverter;
import com.healthcare.service.exception.DoctorNotFoundException;

@Service
@Transactional(readOnly = true)
public class DoctorServiceImpl implements DoctorService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorServiceImpl.class);

	private final DoctorRepo doctorRepo;
	
	private final DTOConverter<DoctorDTO, Doctor> doctorConverter;
	
	@Autowired
	public DoctorServiceImpl(DoctorRepo doctorRepo, DTOConverter<DoctorDTO, Doctor> doctorConverter) {
		this.doctorRepo = doctorRepo;
		this.doctorConverter = doctorConverter;
	}
	
	@Override
	public List<DoctorDTO> getDoctors() {
		List<Doctor> doctors = doctorRepo.findAllOrderByLastName();
		return doctorConverter.convertFromEntity(doctors);
	}
	
	@Override
	@Transactional(readOnly = false)
	public DoctorDTO addDoctor(DoctorDTO doctorDTO) {
		LOGGER.info("An attempt to add doctor: {}", doctorDTO);
		Doctor doctor = doctorRepo.save(doctorConverter.convertFromDTO(doctorDTO));
		LOGGER.info("Successfully added doctor: {}", doctor);
		return doctorConverter.convertFromEntity(doctor);
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
	
	@Override
	public List<DoctorDTO> getDoctorsBySpecialty(String specialty) {
		List<Doctor> doctors = doctorRepo.findBySpecialty(specialty);
		return doctorConverter.convertFromEntity(doctors);
	}
	
	@Override
	@Transactional(readOnly = false)
	public DoctorDTO updateDoctor(DoctorDTO doctorDTO, Long id) {
		Doctor persistedDoctor = doctorRepo.findById(id)
				.orElseThrow(() -> new DoctorNotFoundException("Doctor with id: " + id + " does not exist"));
		persistedDoctor.setAddress(doctorDTO.getAddress());
		persistedDoctor.setPhoneNumber(doctorDTO.getPhoneNumber());
		persistedDoctor.setEmail(doctorDTO.getEmail());
		persistedDoctor = doctorRepo.save(persistedDoctor);
		LOGGER.info("Successfully updated doctor: {}", persistedDoctor);
		return doctorConverter.convertFromEntity(persistedDoctor);
	}	
}
