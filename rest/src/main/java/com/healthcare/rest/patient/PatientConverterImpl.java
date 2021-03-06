package com.healthcare.rest.patient;

import org.springframework.stereotype.Component;

import com.healthcare.rest.common.dto.DTOConverter;
import com.healthcare.rest.patient.dto.PatientDTO;

@Component
class PatientConverterImpl implements DTOConverter<PatientDTO, Patient> {

	@Override
	public Patient convertFromDTO(PatientDTO dto) {
		Patient entity = new Patient();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setAddress(dto.getAddress());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setEmail(dto.getEmail());
		return entity;
	}

	@Override
	public PatientDTO convertFromEntity(Patient entity) {
		PatientDTO dto = new PatientDTO();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setBirthDate(entity.getBirthDate());
		dto.setAddress(entity.getAddress());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setEmail(entity.getEmail());
		return dto;
	}	
}
