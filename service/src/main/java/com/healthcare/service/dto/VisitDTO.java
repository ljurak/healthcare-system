package com.healthcare.service.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.healthcare.model.entities.VisitStatus;

public class VisitDTO {
	
	private Long id;
	
	@NotNull
	@Positive
	private Long patientId;
	
	@NotNull
	@Positive
	private Long doctorId;
	
	@Size(max = 4000)
	private String description;
	
	@NotNull
	@Future
	private LocalDate visitDate;
	
	@NotNull
	private LocalTime visitTime;
	
	private VisitStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public VisitStatus getStatus() {
		return status;
	}

	public void setStatus(VisitStatus visitStatus) {
		this.status = visitStatus;
	}

	@Override
	public String toString() {
		return "VisitDTO [patientId=" + patientId 
				+ ", doctorId=" + doctorId 
				+ ", visitDate=" + visitDate
				+ ", visitTime=" + visitTime 
				+ ", status=" + status + "]";
	}
}