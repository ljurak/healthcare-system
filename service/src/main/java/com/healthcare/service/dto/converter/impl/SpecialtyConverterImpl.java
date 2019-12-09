package com.healthcare.service.dto.converter.impl;

import org.springframework.stereotype.Component;

import com.healthcare.model.entities.Specialty;
import com.healthcare.service.dto.SpecialtyDTO;
import com.healthcare.service.dto.converter.SpecialtyConverter;

@Component
public class SpecialtyConverterImpl implements SpecialtyConverter {

	@Override
	public Specialty convertFromDTO(SpecialtyDTO dto) {
		Specialty specialty = new Specialty();
		specialty.setName(dto.getName());
		return specialty;
	}

	@Override
	public SpecialtyDTO convertFromEntity(Specialty entity) {
		SpecialtyDTO dto = new SpecialtyDTO();
		dto.setName(entity.getName());
		return dto;
	}	
}
