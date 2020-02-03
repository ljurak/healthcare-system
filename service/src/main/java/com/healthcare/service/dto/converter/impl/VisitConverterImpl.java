package com.healthcare.service.dto.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.model.entities.Doctor;
import com.healthcare.model.entities.Patient;
import com.healthcare.model.entities.Visit;
import com.healthcare.model.repo.DoctorRepo;
import com.healthcare.model.repo.PatientRepo;
import com.healthcare.service.dto.VisitDTO;
import com.healthcare.service.dto.converter.DTOConverter;
import com.healthcare.service.exception.DoctorNotFoundException;
import com.healthcare.service.exception.PatientNotFoundException;

@Component
public class VisitConverterImpl implements DTOConverter<VisitDTO, Visit> {
	
	private final PatientRepo patientRepo;
	
	private final DoctorRepo doctorRepo;
	
	@Autowired
	public VisitConverterImpl(PatientRepo patientRepo, DoctorRepo doctorRepo) {
		this.patientRepo = patientRepo;
		this.doctorRepo = doctorRepo;
	}

	@Override
	public Visit convertFromDTO(VisitDTO dto) {
		Patient patient = patientRepo.findById(dto.getPatientId()).orElseThrow(
				() -> new PatientNotFoundException("Patient with id: " + dto.getPatientId() + " does not exist"));
		Doctor doctor = doctorRepo.findById(dto.getDoctorId()).orElseThrow(
				() -> new DoctorNotFoundException("Doctor with id: " + dto.getDoctorId() + " does not exist"));
		Visit entity = new Visit();
		entity.setPatient(patient);
		entity.setDoctor(doctor);
		entity.setDescription(dto.getDescription());
		entity.setVisitDate(dto.getVisitDate());
		entity.setVisitTime(dto.getVisitTime());
		entity.setStatus(dto.getStatus());
		return entity;
	}

	@Override
	public VisitDTO convertFromEntity(Visit entity) {
		VisitDTO dto = new VisitDTO();
		dto.setId(entity.getId());
		dto.setPatientId(entity.getPatient().getId());
		dto.setPatientName(entity.getPatient().getFirstName() + " " + entity.getPatient().getLastName());
		dto.setDoctorId(entity.getDoctor().getId());
		dto.setDoctorName(entity.getDoctor().getFirstName() + " " + entity.getDoctor().getLastName());
		dto.setDescription(entity.getDescription());
		dto.setVisitDate(entity.getVisitDate());
		dto.setVisitTime(entity.getVisitTime());
		dto.setStatus(entity.getStatus());
		return dto;
	}
}
