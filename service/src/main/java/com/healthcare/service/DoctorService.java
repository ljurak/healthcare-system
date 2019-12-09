package com.healthcare.service;

import java.util.List;

import com.healthcare.service.dto.DoctorDTO;

public interface DoctorService {
	
	/**
	 * Retrieves list of all doctors.
	 * 
	 * @return list of all patients
	 */
	List<DoctorDTO> getDoctors();
	
	/**
	 * Retrieves doctor by given id.
	 * Throws <code>DoctorException</code> if doctor
	 * with given id does not exist.
	 * 
	 * @param id id
	 * @return doctor 
	 * @throws DoctorException
	 */
	DoctorDTO getDoctorById(Long id);
	
	/**
	 * Retrieves doctors with matching last name or its part.
	 * 
	 * @param lastName doctor last name or its part
	 * @return list of doctors
	 */
	List<DoctorDTO> getDoctorsByLastName(String lastName);
}
