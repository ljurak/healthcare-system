package com.healthcare.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.model.entities.Patient;
import com.healthcare.model.entities.Visit;
import com.healthcare.model.entities.VisitStatus;
import com.healthcare.model.repo.PatientRepo;
import com.healthcare.model.repo.VisitRepo;
import com.healthcare.service.PatientService;
import com.healthcare.service.dto.PatientDTO;
import com.healthcare.service.dto.VisitDTO;
import com.healthcare.service.dto.converter.PatientConverter;
import com.healthcare.service.dto.converter.VisitConverter;
import com.healthcare.service.exception.PatientException;
import com.healthcare.service.exception.VisitException;

@Service
@Transactional(readOnly = true)
public class PatientServiceImpl implements PatientService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);	
	
	private PatientRepo patientRepo;	
	
	private VisitRepo visitRepo;
	
	private PatientConverter patientConverter;
	
	private VisitConverter visitConverter;
	
	@Autowired
	public PatientServiceImpl(
			PatientRepo patientRepo, 
			VisitRepo visitRepo, 
			PatientConverter patientConverter,
			VisitConverter visitConverter) {
		this.patientRepo = patientRepo;
		this.visitRepo = visitRepo;
		this.patientConverter = patientConverter;
		this.visitConverter = visitConverter;
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
	public List<PatientDTO> getPatients() {
		List<Patient> patients = patientRepo.findAllOrderByLastName();
		return patientConverter.convertFromEntity(patients);
	}

	@Override
	public PatientDTO getPatientById(Long id) {
		Patient patient = patientRepo.findById(id)
			.orElseThrow(() -> new PatientException("Patient with id: " + id + " does not exist"));
		return patientConverter.convertFromEntity(patient);
	}
	
	@Override
	@Transactional(readOnly = false)
	public PatientDTO updatePatient(PatientDTO patientDTO, Long id) {
		Patient persistedPatient = patientRepo.findById(id)
				.orElseThrow(() -> new PatientException("Patient with id: " + id + " does not exist"));
		persistedPatient.setAddress(patientDTO.getAddress());
		persistedPatient.setPhoneNumber(patientDTO.getPhoneNumber());
		persistedPatient.setEmail(patientDTO.getEmail());
		persistedPatient = patientRepo.save(persistedPatient);
		LOGGER.info("Successfully updated patient: {}", persistedPatient);
		return patientConverter.convertFromEntity(persistedPatient);
	}	
	
	@Override
	public List<VisitDTO> getPatientVisits(Long id) {
		List<Visit> visits = visitRepo.findVisitsByPatient(id);
		return visitConverter.convertFromEntity(visits);
	}

	@Override
	@Transactional(readOnly = false)
	public VisitDTO addVisit(VisitDTO visitDTO) {
		LOGGER.info("An attempt to add visit: {}", visitDTO);
		boolean isVisitAvailable = checkVisitAvailability(visitDTO);
		if (isVisitAvailable) {
			Visit visit = visitConverter.convertFromDTO(visitDTO);
			visit.setStatus(VisitStatus.ACTIVE);
			Visit registeredVisit = visitRepo.save(visit);
			LOGGER.info("Successfully registered visit: {}", visit);
			return visitConverter.convertFromEntity(registeredVisit);
		} else {
			LOGGER.info("Unable to add visit: {}", visitDTO);
			throw new VisitException("Visit at given time is not available");
		}
	}
	
	private boolean checkVisitAvailability(VisitDTO visitDTO) {
		Visit existingVisit = visitRepo.findVisitByDoctorAndDateTime(
				visitDTO.getDoctorId(), visitDTO.getVisitDate(), visitDTO.getVisitTime());
		return existingVisit == null;
	}
}
