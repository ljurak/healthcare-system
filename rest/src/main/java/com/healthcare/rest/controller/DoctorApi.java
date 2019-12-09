package com.healthcare.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.service.DoctorService;
import com.healthcare.service.dto.DoctorDTO;

@RestController
@RequestMapping("/doctors")
public class DoctorApi {
	
	private DoctorService doctorService;

	@Autowired
	public DoctorApi(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	
	@GetMapping
	public List<DoctorDTO> getDoctors() {
		return doctorService.getDoctors();
	}
	
	@GetMapping("/{id}")
	public DoctorDTO getDoctor(@PathVariable Long id) {
		return doctorService.getDoctorById(id);
	}
	
	@GetMapping(params = "lastname")
	public List<DoctorDTO> getDoctorsByLastName(@RequestParam String lastname) {
		return doctorService.getDoctorsByLastName(lastname);
	}
}
