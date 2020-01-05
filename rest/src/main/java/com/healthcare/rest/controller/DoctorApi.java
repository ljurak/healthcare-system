package com.healthcare.rest.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
import com.healthcare.service.VisitService;
import com.healthcare.service.dto.DoctorDTO;
import com.healthcare.service.dto.VisitDTO;

@RestController
@RequestMapping("/doctors")
@CrossOrigin
public class DoctorApi {
	
	private DoctorService doctorService;
	
	private VisitService visitService;

	@Autowired
	public DoctorApi(DoctorService doctorService, VisitService visitService) {
		this.doctorService = doctorService;
		this.visitService = visitService;
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
	public DoctorDTO getDoctor(@PathVariable long id) {
		return doctorService.getDoctorById(id);
	}
	
	@GetMapping(params = "lastname")
	public List<DoctorDTO> getDoctorsByLastName(@RequestParam String lastname) {
		return doctorService.getDoctorsByLastName(lastname);
	}
	
	@GetMapping(params = "specialty")
	public List<DoctorDTO> getDoctorsBySpecialty(@RequestParam String specialty) {
		return doctorService.getDoctorsBySpecialty(specialty);
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public DoctorDTO updateDoctor(@RequestBody @Valid DoctorDTO doctorDTO, BindingResult result, @PathVariable long id) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		DoctorDTO updatedDoctor = doctorService.updateDoctor(doctorDTO, id);
		return updatedDoctor;
	}
	
	@GetMapping(path = "/{id}/visits", params = { "startDate", "endDate" })
	public List<VisitDTO> getDoctorVisitsBetweenDates(
			@PathVariable long id, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate startDate, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
		return visitService.getVisitsByDoctorIdBetweenDates(id, startDate, endDate);
	}
}
