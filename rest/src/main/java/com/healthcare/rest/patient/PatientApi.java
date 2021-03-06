package com.healthcare.rest.patient;

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

import com.healthcare.rest.common.exception.InvalidRequestException;
import com.healthcare.rest.patient.dto.PatientDTO;
import com.healthcare.rest.visit.dto.VisitDTO;

@RestController
@RequestMapping("/patients")
@CrossOrigin
class PatientApi {
	
	private PatientFacade patientFacade;
	
	@Autowired
	public PatientApi(PatientFacade patientFacade) {
		this.patientFacade = patientFacade;
	}
	
	@GetMapping
	public List<PatientDTO> getPatients() {
		return patientFacade.getPatients();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public PatientDTO registerPatient(@RequestBody @Valid PatientDTO patientDTO, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		PatientDTO registeredPatient = patientFacade.registerPatient(patientDTO);
		return registeredPatient;
	}
	
	@GetMapping("/{id}")
	public PatientDTO getPatient(@PathVariable long id) {
		return patientFacade.getPatientById(id);
	}
	
	@GetMapping(params = "lastname")
	public List<PatientDTO> getPatientsByLastname(@RequestParam String lastname) {
		return patientFacade.getPatientsByLastName(lastname);
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public PatientDTO updatePatient(@RequestBody @Valid PatientDTO patientDTO, BindingResult result, @PathVariable long id) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		patientDTO.setId(id);
		PatientDTO updatedPatient = patientFacade.updatePatient(patientDTO);
		return updatedPatient;
	}
	
	@GetMapping("/{id}/visits")
	public List<VisitDTO> getPatientVisits(@PathVariable long id) {
		return patientFacade.getVisitsByPatientId(id);
	}
	
	@PostMapping(path = "/{id}/visits", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public VisitDTO addVisit(@RequestBody @Valid VisitDTO visitDTO, BindingResult result, @PathVariable long id) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		visitDTO.setPatientId(id);		
		VisitDTO registeredVisit = patientFacade.addVisit(visitDTO);
		return registeredVisit;
	}
	
	@PutMapping(path = "/{id}/visits", consumes = MediaType.APPLICATION_JSON_VALUE)
	public VisitDTO updateVisit(@RequestBody @Valid VisitDTO visitDTO, BindingResult result, @PathVariable long id) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		visitDTO.setPatientId(id);		
		VisitDTO updatedVisit = patientFacade.updateVisit(visitDTO);
		return updatedVisit;
	}
}
