package com.healthcare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.entities.Specialty;
import com.healthcare.model.repo.SpecialtyRepo;
import com.healthcare.service.SpecialtyService;
import com.healthcare.service.dto.SpecialtyDTO;
import com.healthcare.service.dto.converter.SpecialtyConverter;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
	
	private SpecialtyRepo specialtyRepo;
	private SpecialtyConverter specialtyConverter;
	
	@Autowired
	public SpecialtyServiceImpl(SpecialtyRepo specialtyRepo, SpecialtyConverter specialtyConverter) {
		this.specialtyRepo = specialtyRepo;
		this.specialtyConverter = specialtyConverter;
	}

	@Override
	public List<SpecialtyDTO> getSpecialties() {
		List<Specialty> specialties = specialtyRepo.findAll();
		return specialtyConverter.convertFromEntity(specialties);
	}	
}
