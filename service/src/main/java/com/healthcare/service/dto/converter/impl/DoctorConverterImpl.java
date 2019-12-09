package com.healthcare.service.dto.converter.impl;

import org.springframework.stereotype.Component;

import com.healthcare.model.entities.Doctor;
import com.healthcare.service.dto.DoctorDTO;
import com.healthcare.service.dto.converter.DoctorConverter;

@Component
public class DoctorConverterImpl implements DoctorConverter {
	
	@Override
	public Doctor convertFromDTO(DoctorDTO dto) {
		Doctor entity = new Doctor();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setAddress(dto.getAddress());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setEmail(dto.getEmail());
		return entity;
	}

	@Override
	public DoctorDTO convertFromEntity(Doctor entity) {
		DoctorDTO dto = new DoctorDTO();
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setBirthDate(entity.getBirthDate());
		dto.setAddress(entity.getAddress());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setEmail(entity.getEmail());
		dto.setSpecialty(entity.getSpecialty().getName());
		return dto;
	}	
}
