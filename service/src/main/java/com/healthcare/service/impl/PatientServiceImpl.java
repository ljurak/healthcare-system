package com.healthcare.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.model.entities.Patient;
import com.healthcare.model.repo.PatientRepo;
import com.healthcare.service.PatientService;
import com.healthcare.service.dto.PatientDTO;
import com.healthcare.service.dto.converter.DTOConverter;
import com.healthcare.service.exception.PatientNotFoundException;

@Service
@Transactional(readOnly = true)
public class PatientServiceImpl implements PatientService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);	
	
	private PatientRepo patientRepo;	
	
	private DTOConverter<PatientDTO, Patient> patientConverter;
	
	@Autowired
	public PatientServiceImpl(
			PatientRepo patientRepo,
			DTOConverter<PatientDTO, Patient> patientConverter) {
		this.patientRepo = patientRepo;
		this.patientConverter = patientConverter;
	}
	
	@Override
	public List<PatientDTO> getPatients() {
		List<Patient> patients = patientRepo.findAllOrderByLastName();
		return patientConverter.convertFromEntity(patients);
	}
	
	@Override
	@Transactional(readOnly = false)
	public PatientDTO registerPatient(PatientDTO patientDTO) {
		LOGGER.info("An attempt to register patient: {}", patientDTO);
		Patient registeredPatient = patientRepo.save(patientConverter.convertFromDTO(patientDTO));
		LOGGER.info("Successfully registered patient: {}", registeredPatient);
		return patientConverter.convertFromEntity(registeredPatient);
	}

	@Override
	public PatientDTO getPatientById(Long id) {
		Patient patient = patientRepo.findById(id)
			.orElseThrow(() -> new PatientNotFoundException("Patient with id: " + id + " does not exist"));
		return patientConverter.convertFromEntity(patient);
	}
	
	@Override
	public List<PatientDTO> getPatientsByLastName(String lastName) {
		List<Patient> patients = patientRepo.findByLastNameContainingIgnoreCaseOrderByLastName(lastName);
		return patientConverter.convertFromEntity(patients);
	}
	
	@Override
	@Transactional(readOnly = false)
	public PatientDTO updatePatient(PatientDTO patientDTO) {
		Patient persistedPatient = patientRepo.findById(patientDTO.getId())
				.orElseThrow(() -> new PatientNotFoundException("Patient with id: " + patientDTO.getId() + " does not exist"));
		persistedPatient.setAddress(patientDTO.getAddress());
		persistedPatient.setPhoneNumber(patientDTO.getPhoneNumber());
		persistedPatient.setEmail(patientDTO.getEmail());
		persistedPatient = patientRepo.save(persistedPatient);
		LOGGER.info("Successfully updated patient: {}", persistedPatient);
		return patientConverter.convertFromEntity(persistedPatient);
	}	
}
