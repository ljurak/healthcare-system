package com.healthcare.service.dto.converter;

import com.healthcare.model.entities.Doctor;
import com.healthcare.model.entities.Patient;
import com.healthcare.model.entities.Person;
import com.healthcare.model.entities.Visit;
import com.healthcare.service.dto.DoctorDTO;
import com.healthcare.service.dto.PatientDTO;
import com.healthcare.service.dto.PersonDTO;
import com.healthcare.service.dto.VisitDTO;

public class DTOEntityConverter {
	
	public static PatientDTO toPatientDTO(Patient patientEntity) {
		PatientDTO patientDTO = new PatientDTO();
		setPersonDTOProperties(patientDTO, patientEntity);
		return patientDTO;
	}
	
	public static Patient toPatientEntity(PatientDTO patientDTO) {
		Patient patient = new Patient();
		setPersonEntityProperties(patient, patientDTO);
		return patient;
	}
	
	public static DoctorDTO toDoctorDTO(Doctor doctorEntity) {
		DoctorDTO doctorDTO = new DoctorDTO();
		setPersonDTOProperties(doctorDTO, doctorEntity);
		return doctorDTO;
	}
	
	public static Doctor toDoctorEntity(DoctorDTO doctorDTO) {
		Doctor doctor = new Doctor();
		setPersonEntityProperties(doctor, doctorDTO);
		return doctor;
	}
	
	public static VisitDTO toVisitDTO(Visit visitEntity, Long patientId, Long doctorId) {
		VisitDTO visitDTO = new VisitDTO();
		visitDTO.setPatientId(patientId);
		visitDTO.setDoctorId(doctorId);
		visitDTO.setVisitDate(visitEntity.getVisitDate());
		visitDTO.setVisitTime(visitEntity.getVisitTime());
		return visitDTO;
	}
	
	public static Visit toVisitEntity(VisitDTO visitDTO, Patient patient, Doctor doctor) {
		Visit visit = new Visit();
		visit.setPatient(patient);
		visit.setDoctor(doctor);
		visit.setVisitDate(visitDTO.getVisitDate());
		visit.setVisitTime(visitDTO.getVisitTime());
		return visit;
	}
	
	private static void setPersonDTOProperties(PersonDTO personDTO, Person fromPersonEntity) {
		personDTO.setFirstName(fromPersonEntity.getFirstName());
		personDTO.setLastName(fromPersonEntity.getLastName());
		personDTO.setBirthDate(fromPersonEntity.getBirthDate());
		personDTO.setAddress(fromPersonEntity.getAddress());
		personDTO.setPhoneNumber(fromPersonEntity.getPhoneNumber());
		personDTO.setEmail(fromPersonEntity.getEmail());
	}
	
	private static void setPersonEntityProperties(Person personEntity, PersonDTO fromPersonDTO) {
		personEntity.setFirstName(fromPersonDTO.getFirstName());
		personEntity.setLastName(fromPersonDTO.getLastName());
		personEntity.setBirthDate(fromPersonDTO.getBirthDate());
		personEntity.setAddress(fromPersonDTO.getAddress());
		personEntity.setPhoneNumber(fromPersonDTO.getPhoneNumber());
		personEntity.setEmail(fromPersonDTO.getEmail());
	}
}
