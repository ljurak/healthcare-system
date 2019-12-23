package com.healthcare.service.dto.converter.impl;

import org.springframework.stereotype.Component;

import com.healthcare.model.entities.Specialty;
import com.healthcare.service.dto.SpecialtyDTO;
import com.healthcare.service.dto.converter.DTOConverter;

@Component
public class SpecialtyConverterImpl implements DTOConverter<SpecialtyDTO, Specialty> {

	@Override
	public Specialty convertFromDTO(SpecialtyDTO dto) {
		Specialty entity = new Specialty();
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public SpecialtyDTO convertFromEntity(Specialty entity) {
		SpecialtyDTO dto = new SpecialtyDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}	
}
