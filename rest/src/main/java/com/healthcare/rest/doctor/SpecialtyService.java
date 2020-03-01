package com.healthcare.rest.doctor;

import java.util.List;

import com.healthcare.rest.doctor.dto.SpecialtyDTO;

interface SpecialtyService {
	
	/**
	 * Retrieves list of all specialties.
	 * 
	 * @return list of all specialties
	 */
	List<SpecialtyDTO> getSpecialties();
}
