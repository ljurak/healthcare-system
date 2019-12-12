package com.healthcare.service.dto.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.model.entities.Doctor;
import com.healthcare.model.entities.Patient;
import com.healthcare.model.entities.Visit;
import com.healthcare.model.repo.DoctorRepo;
import com.healthcare.model.repo.PatientRepo;
import com.healthcare.service.dto.VisitDTO;
import com.healthcare.service.dto.converter.VisitConverter;
import com.healthcare.service.exception.DoctorNotFoundException;
import com.healthcare.service.exception.PatientNotFoundException;

@Component
public class VisitConverterImpl implements VisitConverter {
	
	private PatientRepo patientRepo;
	
	private DoctorRepo doctorRepo;
	
	@Autowired
	public VisitConverterImpl(PatientRepo patientRepo, DoctorRepo doctorRepo) {
		this.patientRepo = patientRepo;
		this.doctorRepo = doctorRepo;
	}

	@Override
	public Visit convertFromDTO(VisitDTO dto) {
		Visit entity = new Visit();
		Patient patient = patientRepo.findById(dto.getPatientId())
				.orElseThrow(() -> new PatientNotFoundException("Patient with id: " + dto.getPatientId() + " does not exist"));
		Doctor doctor = doctorRepo.findById(dto.getDoctorId())
				.orElseThrow(() -> new DoctorNotFoundException("Doctor with id: " + dto.getDoctorId() + " does not exist"));
		entity.setPatient(patient);
		entity.setDoctor(doctor);
		entity.setVisitDate(dto.getVisitDate());
		entity.setVisitTime(dto.getVisitTime());
		entity.setStatus(dto.getVisitStatus());
		return entity;
	}

	@Override
	public VisitDTO convertFromEntity(Visit entity) {
		VisitDTO dto = new VisitDTO();
		dto.setPatientId(entity.getPatient().getId());
		dto.setDoctorId(entity.getDoctor().getId());
		dto.setVisitDate(entity.getVisitDate());
		dto.setVisitTime(entity.getVisitTime());
		dto.setVisitStatus(entity.getStatus());
		return dto;
	}	
}
