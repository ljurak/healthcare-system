package com.healthcare.service.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.healthcare.model.entities.VisitStatus;

public class VisitDTO {
	
	@NotNull
	@Positive
	private Long patientId;
	
	@NotNull
	@Positive
	private Long doctorId;
	
	@NotNull
	@Future
	private LocalDate visitDate;
	
	@NotNull
	private LocalTime visitTime;
	
	private VisitStatus status;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public LocalDate getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(LocalDate visitDate) {
		this.visitDate = visitDate;
	}

	public LocalTime getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(LocalTime visitTime) {
		this.visitTime = visitTime;
	}

	public VisitStatus getVisitStatus() {
		return status;
	}

	public void setVisitStatus(VisitStatus visitStatus) {
		this.status = visitStatus;
	}
}