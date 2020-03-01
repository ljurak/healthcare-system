package com.healthcare.rest.doctor;

import org.springframework.stereotype.Component;

import com.healthcare.rest.common.dto.DTOConverter;
import com.healthcare.rest.doctor.dto.SpecialtyDTO;

@Component
class SpecialtyConverterImpl implements DTOConverter<SpecialtyDTO, Specialty> {

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
