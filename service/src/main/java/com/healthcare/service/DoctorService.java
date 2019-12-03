package com.healthcare.service;

import com.healthcare.model.entities.Doctor;

public interface DoctorService {
	
	/**
	 * Retrieves doctor by given id.
	 * 
	 * @param id id
	 * @return doctor 
	 */
	Doctor getDoctorById(Long id);
}
