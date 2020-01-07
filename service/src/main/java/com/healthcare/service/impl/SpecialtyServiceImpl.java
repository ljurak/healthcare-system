package com.healthcare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.model.entities.Specialty;
import com.healthcare.model.repo.SpecialtyRepo;
import com.healthcare.service.SpecialtyService;
import com.healthcare.service.dto.SpecialtyDTO;
import com.healthcare.service.dto.converter.DTOConverter;

@Service
@Transactional(readOnly = true)
public class SpecialtyServiceImpl implements SpecialtyService {
	
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
