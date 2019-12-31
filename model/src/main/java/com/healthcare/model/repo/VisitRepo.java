package com.healthcare.model.repo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entities.Visit;

@Repository
public interface VisitRepo extends CrudRepository<Visit, Long> {
	
	@Query("select v from Visit v where v.doctor.id = :doctorId and v.visitDate = :date and v.visitTime = :time")
	Visit findVisitByDoctorAndDateTime(@Param("doctorId") Long doctorId, @Param("date") LocalDate date, @Param("time") LocalTime time);
	
	@Query("select v from Visit v where v.doctor.id = :doctorId and v.visitDate = :date and v.visitTime = :time and v.status != 'CANCELLED'")
	Visit findVisitByDoctorAndDateTimeNotCancelled(@Param("doctorId") Long doctorId, @Param("date") LocalDate date, @Param("time") LocalTime time);
	
	@Query("select v from Visit v where v.patient.id = :patientId order by v.visitDate, v.visitTime")
	List<Visit> findVisitsByPatient(@Param("patientId") Long patientId);
	
	@Query("select v from Visit v where v.doctor.id = :doctorId order by v.visitDate, v.visitTime")
	List<Visit> findVisitsByDoctor(@Param("doctorId") Long doctorId);
}
