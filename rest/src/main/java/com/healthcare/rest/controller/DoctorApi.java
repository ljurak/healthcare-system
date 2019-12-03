package com.healthcare.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.model.entities.Specialty;
import com.healthcare.model.repo.SpecialtyRepo;

@RestController
@RequestMapping("/doctors")
public class DoctorApi {
	
	private SpecialtyRepo specialtyRepo;

	@Autowired
	public DoctorApi(SpecialtyRepo specialtyRepo) {
		this.specialtyRepo = specialtyRepo;
	}
	
	@GetMapping("/specialties")
	public Iterable<Specialty> getSpecialties() {
		return specialtyRepo.findAll();
	}
}
