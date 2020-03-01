package com.healthcare.rest.visit;

import java.time.LocalDate;
import java.util.List;

import com.healthcare.rest.visit.dto.VisitDTO;

interface VisitService {
	
	/**
	 * Adds visit for a patient to the specified doctor
	 * at a specified time. If appointment at given time
	 * is not possible throws <code>VisitException</code>
	 * 
	 * @param visit visit to be added
	 * @return added visit
	 * @throws VisitException
	 */
	VisitDTO addVisit(VisitDTO visitDTO);
	
	/**
	 * Retrieves visit by given id.
	 * Throws <code>VisitNotFoundException</code> if visit
	 * with given id does not exist.
	 * 
	 * @param id id
	 * @return visit 
	 * @throws VisitNotFoundException
	 */
	VisitDTO getVisitById(Long id);
	
	/**
	 * Retrieves visits for a patient with given id.
	 * 
	 * @param patientId id of a patient
	 * @return list of visits
	 */
	List<VisitDTO> getVisitsByPatientId(Long patientId);
	
	/**
	 * Retrieves visits for a doctor with given id.
	 * 
	 * @param doctorId id of a doctor
	 * @return list of visits
	 */
	List<VisitDTO> getVisitsByDoctorId(Long doctorId);
	
	/**
	 * Retrieves visits for a doctor with given id
	 * between specified dates.
	 * 
	 * @param doctorId id of a doctor
	 * @param startDate 
	 * @param endDate
	 * @return list of visits
	 */
	List<VisitDTO> getVisitsByDoctorIdBetweenDates(Long doctorId, LocalDate startDate, LocalDate endDate);
	
	/**
	 * Updates visit data.
	 * Throws <code>VisitNotFoundException</code> if visit
	 * with given id does not exist.
	 * 
	 * @param visitDTO visit to be updated
	 * @return updated visit
	 * @throws VisitNotFoundException
	 */
	VisitDTO updateVisit(VisitDTO visitDTO);	
}
