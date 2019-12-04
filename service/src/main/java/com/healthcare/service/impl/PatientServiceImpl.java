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
import com.healthcare.service.exception.PatientException;
import com.healthcare.service.exception.VisitException;

@Service
@Transactional(readOnly = true)
public class PatientServiceImpl implements PatientService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);	
	
	private PatientRepo patientRepo;	
	
	private VisitRepo visitRepo;
	
	@Autowired
	public PatientServiceImpl(PatientRepo patientRepo, VisitRepo visitRepo) {
		this.patientRepo = patientRepo;
		this.visitRepo = visitRepo;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Patient registerPatient(Patient patient) {
		LOGGER.info("An attempt to register patient: {}", patient);
		Patient registeredPatient = patientRepo.save(patient);
		LOGGER.info("Successfully registered patient: {}", registeredPatient);
		return registeredPatient;
	}
	
	@Override
	public List<Patient> getPatients() {
		return patientRepo.findAllOrderByLastName();
	}

	@Override
	public Patient getPatientById(Long id) {
		return patientRepo.findById(id)
			.orElseThrow(() -> new PatientException("Patient with id: " + id + " does not exist"));
	}
	
	@Override
	@Transactional(readOnly = false)
	public Patient updatePatient(Patient patient) {
		Patient persistedPatient = patientRepo.findById(patient.getId())
				.orElseThrow(() -> new PatientException("Patient with id: " + patient.getId() + " does not exist"));
		persistedPatient.setAddress(patient.getAddress());
		persistedPatient.setPhoneNumber(patient.getPhoneNumber());
		persistedPatient.setEmail(patient.getEmail());
		persistedPatient = patientRepo.save(persistedPatient);
		LOGGER.info("Successfully updated patient: {}", persistedPatient);
		return persistedPatient;
	}	
	
	@Override
	public List<Visit> getPatientVisits(Long id) {
		return visitRepo.findVisitsByPatient(id);
	}

	@Override
	@Transactional(readOnly = false)
	public Visit addVisit(Visit visit) {
		LOGGER.info("An attempt to add visit: {}", visit);
		boolean isVisitAvailable = checkVisitAvailability(visit);
		if (isVisitAvailable) {
			visit.setStatus(VisitStatus.ACTIVE);
			Visit registeredVisit = visitRepo.save(visit);
			LOGGER.info("Successfully registered visit: {}", visit);
			return registeredVisit;
		} else {
			LOGGER.info("Unable to add visit: {}", visit);
			throw new VisitException("Visit at given time is not available");
		}
	}
	
	private boolean checkVisitAvailability(Visit visit) {
		Visit existingVisit = visitRepo.findVisitByDoctorAndDateTime(
				visit.getDoctor(), visit.getVisitDate(), visit.getVisitTime());
		return existingVisit == null;
	}
}
