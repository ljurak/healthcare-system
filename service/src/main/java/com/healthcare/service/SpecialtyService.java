package com.healthcare.service;

import java.util.List;

import com.healthcare.service.dto.SpecialtyDTO;

public interface SpecialtyService {
	
	/**
	 * Retrieves list of all specialties.
	 * 
	 * @return list of all specialties
	 */
	List<SpecialtyDTO> getSpecialties();
}
