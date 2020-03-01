package com.healthcare.rest.patient;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.rest.patient.dto.PatientDTO;
import com.healthcare.rest.visit.VisitFacade;
import com.healthcare.rest.visit.dto.VisitDTO;

@Service
@Transactional(readOnly = true)
public class PatientFacade {
	
	private PatientService patientService;
	
	private VisitFacade visitFacade;

	public PatientFacade(PatientService patientService, VisitFacade visitFacade) {
		this.patientService = patientService;
		this.visitFacade = visitFacade;
	}
	
	public List<PatientDTO> getPatients() {
		return patientService.getPatients();
	}
	
	public PatientDTO getPatientById(Long id) {
		return patientService.getPatientById(id);
	}
	
	public List<PatientDTO> getPatientsByLastName(String lastName) {
		return patientService.getPatientsByLastName(lastName);
	}
	
	public List<VisitDTO> getVisitsByPatientId(Long patientId) {
		return visitFacade.getVisitsByPatientId(patientId);
	}
	
	@Transactional(readOnly = false)
	public PatientDTO registerPatient(PatientDTO patientDTO) {
		return patientService.registerPatient(patientDTO);
	}
	
	@Transactional(readOnly = false)
	public PatientDTO updatePatient(PatientDTO patientDTO) {
		return patientService.updatePatient(patientDTO);
	}
	
	@Transactional(readOnly = false)
	public VisitDTO addVisit(VisitDTO visitDTO) {
		return visitFacade.addVisit(visitDTO);
	}
	
	@Transactional(readOnly = false)
	public VisitDTO updateVisit(VisitDTO visitDTO) {
		return visitFacade.updateVisit(visitDTO);
	}
}
