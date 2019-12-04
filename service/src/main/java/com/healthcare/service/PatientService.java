package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entities.Patient;
import com.healthcare.service.dto.PatientDTO;
import com.healthcare.service.dto.VisitDTO;

public interface PatientService {
	
	/**
	 * Registers patient.
	 * 
	 * @param patient patient
	 * @return registered patient
	 */
	PatientDTO registerPatient(PatientDTO patientDTO);
	
	/**
	 * Retrieves patient by given id.
	 * Throws <code>PatientException</code> if patient
	 * with given id does not exist.
	 * 
	 * @param id id
	 * @return patient 
	 * @throws Patient Exception
	 */
	PatientDTO getPatientById(Long id);
	
	/**
	 * Retrieves list of all patients.
	 * 
	 * @return list of all patients
	 */
	List<PatientDTO> getPatients();
	
	/**
	 * Updates patient data.
	 * Throws <code>PatientException</code> if patient
	 * with given id does not exist.
	 * 
	 * @param patient patient to be updated
	 * @param id patient id
	 * @return updated patient
	 * @throws Patient Exception
	 */
	PatientDTO updatePatient(PatientDTO patientDTO, Long id);
	
	/**
	 * Retrieves list of specified patient's visits.
	 * 
	 * @param id patient id
	 * @return list of patient's visits 
	 */
	List<VisitDTO> getPatientVisits(Long id);
	
	/**
	 * Adds visit for a patient to the specified doctor
	 * at a specified time. If appointment at given time
	 * is not possible throws <code>Visit Exception</code>
	 * 
	 * @param visit visit to be added
	 * @return added visit
	 * @throws VisitException
	 */
	VisitDTO addVisit(VisitDTO visit);
}
