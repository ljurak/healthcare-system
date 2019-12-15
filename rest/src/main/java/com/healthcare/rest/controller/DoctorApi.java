package com.healthcare.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.rest.exception.InvalidRequestException;
import com.healthcare.service.DoctorService;
import com.healthcare.service.dto.DoctorDTO;

@RestController
@RequestMapping("/doctors")
@CrossOrigin
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
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public DoctorDTO processAddingNewDoctor(@RequestBody @Valid DoctorDTO doctorDTO, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		DoctorDTO addedDoctor = doctorService.addDoctor(doctorDTO);
		return addedDoctor;
	}
	
	@GetMapping("/{id}")
	public DoctorDTO getDoctor(@PathVariable Long id) {
		return doctorService.getDoctorById(id);
	}
	
	@GetMapping(params = "lastname")
	public List<DoctorDTO> getDoctorsByLastName(@RequestParam String lastname) {
		return doctorService.getDoctorsByLastName(lastname);
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public DoctorDTO updateDoctor(@RequestBody @Valid DoctorDTO doctorDTO, BindingResult result, @PathVariable long id) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		DoctorDTO updatedDoctor = doctorService.updateDoctor(doctorDTO, id);
		return updatedDoctor;
	}
}
