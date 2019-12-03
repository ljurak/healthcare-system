package com.healthcare.rest.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
import com.healthcare.rest.exception.InvalidRequestException;
import com.healthcare.service.DoctorService;
import com.healthcare.service.PatientService;
import com.healthcare.service.dto.PatientDTO;
import com.healthcare.service.dto.VisitDTO;
import com.healthcare.service.dto.converter.DTOEntityConverter;

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
	public List<PatientDTO> getPatients() {
		return patientService
				.getPatients()
				.stream()
				.map(DTOEntityConverter::toPatientDTO)
				.collect(Collectors.toList());
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public PatientDTO processPatientRegistration(@RequestBody @Valid PatientDTO patientDTO, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		Patient registeredPatient = patientService.registerPatient(DTOEntityConverter.toPatientEntity(patientDTO));
		return DTOEntityConverter.toPatientDTO(registeredPatient);
	}
	
	@GetMapping("/{id}")
	public PatientDTO getPatient(@PathVariable long id) {
		Patient patient = patientService.getPatientById(id);
		return DTOEntityConverter.toPatientDTO(patient);
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public PatientDTO updatePatient(@RequestBody @Valid PatientDTO patientDTO, BindingResult result, @PathVariable long id) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		Patient patient = DTOEntityConverter.toPatientEntity(patientDTO);
		patient.setId(id);
		Patient persistedPatient = patientService.updatePatient(patient);
		return DTOEntityConverter.toPatientDTO(persistedPatient);		
	}
	
	@PostMapping(path = "/{id}/visits", consumes = MediaType.APPLICATION_JSON_VALUE)
	public VisitDTO addVisit(@RequestBody @Valid VisitDTO visitDTO, BindingResult result, @PathVariable long id) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		visitDTO.setPatientId(id);
		Patient patient = patientService.getPatientById(visitDTO.getPatientId());
		Doctor doctor = doctorService.getDoctorById(visitDTO.getDoctorId());
		Visit visit = DTOEntityConverter.toVisitEntity(visitDTO, patient, doctor);
		
		Visit registeredVisit = patientService.addVisit(visit);
		return DTOEntityConverter.toVisitDTO(registeredVisit, patient.getId(), doctor.getId());
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Map<String, String>> handleException(NoSuchElementException ex) {
		return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<Map<String, String>> handleInvalidRequestException(InvalidRequestException ex) {
		return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
