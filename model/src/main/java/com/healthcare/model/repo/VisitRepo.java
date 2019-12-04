package com.healthcare.model.repo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entities.Doctor;
import com.healthcare.model.entities.Visit;

@Repository
public interface VisitRepo extends CrudRepository<Visit, Long> {
	
	@Query("select v from Visit v where v.doctor = :doctor and v.visitDate = :date and v.visitTime = :time")
	Visit findVisitByDoctorAndDateTime(@Param("doctor") Doctor doctor, @Param("date") LocalDate date, @Param("time") LocalTime time);
	
	@Query("select v from Visit v where v.patient.id = :patientId order by v.visitDate, v.visitTime")
	List<Visit> findVisitsByPatient(@Param("patientId") Long patientId);
	
	@Query("select v from Visit v where v.doctor.id = :doctorId order by v.visitDate, v.visitTime")
	List<Visit> findVisitsByDoctor(@Param("doctorId") Long doctorId);
}
