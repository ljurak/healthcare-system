package com.healthcare.rest.visit;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.rest.visit.dto.VisitDTO;

@Service
@Transactional(readOnly = true)
public class VisitFacade {
	
	private VisitService visitService;

	public VisitFacade(VisitService visitService) {
		this.visitService = visitService;
	}
	
	public List<VisitDTO> getVisitsByPatientId(Long patientId) {
		return visitService.getVisitsByPatientId(patientId);
	}
	
	public List<VisitDTO> getVisitsByDoctorIdBetweenDates(Long doctorId, LocalDate startDate, LocalDate endDate) {
		return visitService.getVisitsByDoctorIdBetweenDates(doctorId, startDate, endDate);
	}
	
	@Transactional(readOnly = false)
	public VisitDTO addVisit(VisitDTO visitDTO) {
		return visitService.addVisit(visitDTO);
	}
	
	@Transactional(readOnly = false)
	public VisitDTO updateVisit(VisitDTO visitDTO) {
		return visitService.updateVisit(visitDTO);
	}
}
