package com.healthcare.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.service.SpecialtyService;
import com.healthcare.service.dto.SpecialtyDTO;

@RestController
@RequestMapping("/specialties")
@CrossOrigin
public class SpecialtyApi {
	
	private SpecialtyService specialtyService;

	public SpecialtyApi(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}
	
	@GetMapping
	public List<SpecialtyDTO> getSpecialties() {
		return specialtyService.getSpecialties();
	}
}
