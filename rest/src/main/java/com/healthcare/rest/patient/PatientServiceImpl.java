package com.healthcare.rest.patient;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.rest.common.dto.DTOConverter;
import com.healthcare.rest.patient.dto.PatientDTO;
import com.healthcare.rest.patient.exception.PatientNotFoundException;

@Component
class PatientServiceImpl implements PatientService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);	
	
	private final PatientRepo patientRepo;	
	
	private final DTOConverter<PatientDTO, Patient> patientConverter;
	
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
	public PatientDTO registerPatient(PatientDTO patientDTO) {
		LOGGER.info("An attempt to register patient: {}", patientDTO);
		Patient registeredPatient = patientRepo.save(patientConverter.convertFromDTO(patientDTO));
		LOGGER.info("Successfully registered patient: {}", registeredPatient);
		return patientConverter.convertFromEntity(registeredPatient);
	}

	@Override
	public PatientDTO getPatientById(Long id) {
		Patient patient = patientRepo.findById(id).orElseThrow(
				() -> new PatientNotFoundException("Patient with id: " + id + " does not exist"));
		return patientConverter.convertFromEntity(patient);
	}
	
	@Override
	public List<PatientDTO> getPatientsByLastName(String lastName) {
		List<Patient> patients = patientRepo.findByLastNameContainingIgnoreCaseOrderByLastName(lastName);
		return patientConverter.convertFromEntity(patients);
	}
	
	@Override
	public PatientDTO updatePatient(PatientDTO patientDTO) {
		LOGGER.info("An attempt to update patient: {}", patientDTO);
		Patient persistedPatient = patientRepo.findById(patientDTO.getId()).orElseThrow(
				() -> new PatientNotFoundException("Patient with id: " + patientDTO.getId() + " does not exist"));
		persistedPatient.setAddress(patientDTO.getAddress());
		persistedPatient.setPhoneNumber(patientDTO.getPhoneNumber());
		persistedPatient.setEmail(patientDTO.getEmail());
		persistedPatient = patientRepo.save(persistedPatient);
		LOGGER.info("Successfully updated patient: {}", persistedPatient);
		return patientConverter.convertFromEntity(persistedPatient);
	}	
}
