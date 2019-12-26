package com.healthcare.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "visits")
public class Visit {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor doctor;
	
	@Column(name = "description", length = 4000)
	private String description;
	
	@Column(name = "visit_date", nullable = false)
	private LocalDate visitDate;
	
	@Column(name = "visit_time", nullable = false)
	private LocalTime visitTime;
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private VisitStatus status;
	
	@Column(name = "create_time", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createTime;
	
	@Column(name = "update_time", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updateTime;
	
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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

	public Long getId() {
		return id;
	}

	public VisitStatus getStatus() {
		return status;
	}

	public void setStatus(VisitStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Visit [id=" + id 
				+ ", patientId=" + patient.getId() 
				+ ", doctorId=" + doctor.getId() 
				+ ", visitDate=" + visitDate
				+ ", visitTime=" + visitTime 
				+ ", status=" + status + "]";
	}
}
