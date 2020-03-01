package com.healthcare.rest.doctor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.rest.doctor.dto.DoctorDTO;
import com.healthcare.rest.visit.VisitFacade;
import com.healthcare.rest.visit.dto.VisitDTO;

@Service
@Transactional(readOnly = true)
public class DoctorFacade {
	
	private DoctorService doctorService;
	
	private VisitFacade visitFacade;

	public DoctorFacade(DoctorService doctorService, VisitFacade visitFacade) {
		this.doctorService = doctorService;
		this.visitFacade = visitFacade;
	}
	
	public List<DoctorDTO> getDoctors() {
		return doctorService.getDoctors();
	}
	
	public DoctorDTO getDoctorById(Long id) {
		return doctorService.getDoctorById(id);
	}
	
	public List<DoctorDTO> getDoctorsByLastName(String lastName) {
		return doctorService.getDoctorsByLastName(lastName);
	}
	
	public List<DoctorDTO> getDoctorsBySpecialty(String specialty) {
		return doctorService.getDoctorsBySpecialty(specialty);
	}
	
	public List<VisitDTO> getVisitsByDoctorIdBetweenDates(Long doctorId, LocalDate startDate, LocalDate endDate) {
		return visitFacade.getVisitsByDoctorIdBetweenDates(doctorId, startDate, endDate);
	}
	
	@Transactional(readOnly = false)
	public DoctorDTO addDoctor(DoctorDTO doctorDTO) {
		return doctorService.addDoctor(doctorDTO);
	}
	
	@Transactional(readOnly = false)
	public DoctorDTO updateDoctor(DoctorDTO doctorDTO, Long id) {
		return doctorService.updateDoctor(doctorDTO, id);
	}
}
