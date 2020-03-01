package com.healthcare.rest.doctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.rest.common.dto.DTOConverter;
import com.healthcare.rest.doctor.dto.SpecialtyDTO;

@Component
class SpecialtyServiceImpl implements SpecialtyService {
	
	private final SpecialtyRepo specialtyRepo;
	private final DTOConverter<SpecialtyDTO, Specialty> specialtyConverter;
	
	@Autowired
	public SpecialtyServiceImpl(SpecialtyRepo specialtyRepo, DTOConverter<SpecialtyDTO, Specialty> specialtyConverter) {
		this.specialtyRepo = specialtyRepo;
		this.specialtyConverter = specialtyConverter;
	}

	@Override
	public List<SpecialtyDTO> getSpecialties() {
		List<Specialty> specialties = specialtyRepo.findAll();
		return specialtyConverter.convertFromEntity(specialties);
	}	
}
