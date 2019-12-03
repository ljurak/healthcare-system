package com.healthcare.model.repo;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entities.Doctor;
import com.healthcare.model.entities.Visit;

@Repository
public interface VisitRepo extends CrudRepository<Visit, Long> {
	
	@Query("select v from Visit v where v.doctor = :doctor and v.visitDate = :date and v.visitTime = :time")
	Visit findVisitByDoctorAndDate(@Param("doctor") Doctor doctor, @Param("date") LocalDate date, @Param("time") LocalTime time);
}
