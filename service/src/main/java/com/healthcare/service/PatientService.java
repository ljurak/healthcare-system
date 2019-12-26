package com.healthcare.service;

import java.util.List;

import com.healthcare.service.dto.PatientDTO;
import com.healthcare.service.dto.VisitDTO;

public interface PatientService {
	
	/**
	 * Retrieves list of all patients.
	 * 
	 * @return list of all patients
	 */
	List<PatientDTO> getPatients();
	
	/**
	 * Registers patient.
	 * 
	 * @param patient patient
	 * @return registered patient
	 */
	PatientDTO registerPatient(PatientDTO patientDTO);
	
	/**
	 * Retrieves patient by given id.
	 * Throws <code>PatientNotFoundException</code> if patient
	 * with given id does not exist.
	 * 
	 * @param id id
	 * @return patient 
	 * @throws PatientNotFoundException
	 */
	PatientDTO getPatientById(Long id);
	
	/**
	 * Retrieves patients with matching last name or its part.
	 * 
	 * @param lastName patient last name or its part
	 * @return list of patients
	 */
	List<PatientDTO> getPatientsByLastName(String lastName);	
	
	/**
	 * Updates patient data.
	 * Throws <code>PatientNotFoundException</code> if patient
	 * with given id does not exist.
	 * 
	 * @param patient patient to be updated
	 * @param id patient id
	 * @return updated patient
	 * @throws PatientNotFoundException
	 */
	PatientDTO updatePatient(PatientDTO patientDTO, Long id);
	
	/**
	 * Retrieves list of specified patient's visits.
	 * 
	 * @param id patient id
	 * @return list of patient's visits 
	 */
	List<VisitDTO> getPatientVisits(Long id);
}
