package com.healthcare.rest.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.model.entities.Doctor;
import com.healthcare.model.entities.Patient;
import com.healthcare.model.entities.Visit;
import com.healthcare.rest.dto.VisitDTO;
import com.healthcare.rest.exception.InvalidRequestException;
import com.healthcare.service.DoctorService;
import com.healthcare.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientApi {
	
	private PatientService patientService;
	
	private DoctorService doctorService;
	
	@Autowired
	public PatientApi(PatientService patientService, DoctorService doctorService) {
		this.patientService = patientService;
		this.doctorService = doctorService;
	}
	
	@GetMapping
	public List<Patient> getPatients() {
		return patientService.getPatients();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Patient processPatientRegistration(@RequestBody @Valid Patient patient, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		Patient registeredPatient = patientService.registerPatient(patient);
		return registeredPatient;
	}
	
	@GetMapping("/{id}")
	public Patient getPatient(@PathVariable long id) {
		return patientService.getPatientById(id);
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Patient updatePatient(@RequestBody @Valid Patient patient, BindingResult result, @PathVariable long id) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		patient.setId(id);
		Patient persistedPatient = patientService.updatePatient(patient);
		return persistedPatient;		
	}
	
	@PostMapping(path = "/{id}/visits", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Visit addVisit(@RequestBody @Valid VisitDTO visitDTO, BindingResult result, @PathVariable long id) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		visitDTO.setPatientId(id);
		Patient patient = patientService.getPatientById(visitDTO.getPatientId());
		Doctor doctor = doctorService.getDoctorById(visitDTO.getDoctorId());
		Visit visit = toVisit(visitDTO, patient, doctor);
		
		Visit registeredVisit = patientService.addVisit(visit);
		return registeredVisit;
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Map<String, String>> handleException(NoSuchElementException ex) {
		return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<Map<String, String>> handleInvalidRequestException(InvalidRequestException ex) {
		return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	private Visit toVisit(VisitDTO visitDTO, Patient patient, Doctor doctor) {
		Visit visit = new Visit();
		visit.setDoctor(doctor);
		visit.setPatient(patient);
		visit.setVisitDate(visitDTO.getVisitDate());
		visit.setVisitTime(visitDTO.getVisitTime());
		return visit;
	}
}
