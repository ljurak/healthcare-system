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
import com.healthcare.rest.visit.VisitFacade;
import com.healthcare.rest.visit.dto.VisitDTO;

@RestController
@RequestMapping("/patients")
@CrossOrigin
class PatientApi {
	
	private PatientService patientService;
	
	private VisitFacade visitFacade;
	
	@Autowired
	public PatientApi(PatientService patientService, VisitFacade visitFacade) {
		this.patientService = patientService;
		this.visitFacade = visitFacade;
	}
	
	@GetMapping
	public List<PatientDTO> getPatients() {
		return patientService.getPatients();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public PatientDTO registerPatient(@RequestBody @Valid PatientDTO patientDTO, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		PatientDTO registeredPatient = patientService.registerPatient(patientDTO);
		return registeredPatient;
	}
	
	@GetMapping("/{id}")
	public PatientDTO getPatient(@PathVariable long id) {
		return patientService.getPatientById(id);
	}
	
	@GetMapping(params = "lastname")
	public List<PatientDTO> getPatientsByLastname(@RequestParam String lastname) {
		return patientService.getPatientsByLastName(lastname);
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public PatientDTO updatePatient(@RequestBody @Valid PatientDTO patientDTO, BindingResult result, @PathVariable long id) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		patientDTO.setId(id);
		PatientDTO updatedPatient = patientService.updatePatient(patientDTO);
		return updatedPatient;
	}
	
	@GetMapping("/{id}/visits")
	public List<VisitDTO> getPatientVisits(@PathVariable long id) {
		return visitFacade.getVisitsByPatientId(id);
	}
	
	@PostMapping(path = "/{id}/visits", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public VisitDTO addVisit(@RequestBody @Valid VisitDTO visitDTO, BindingResult result, @PathVariable long id) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		visitDTO.setPatientId(id);		
		VisitDTO registeredVisit = visitFacade.addVisit(visitDTO);
		return registeredVisit;
	}
	
	@PutMapping(path = "/{id}/visits", consumes = MediaType.APPLICATION_JSON_VALUE)
	public VisitDTO updateVisit(@RequestBody @Valid VisitDTO visitDTO, BindingResult result, @PathVariable long id) {
		if (result.hasErrors()) {
			throw new InvalidRequestException("Invalid data format", result);
		}
		
		visitDTO.setPatientId(id);		
		VisitDTO updatedVisit = visitFacade.updateVisit(visitDTO);
		return updatedVisit;
	}
}
