package com.healthcare.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.model.entities.Visit;
import com.healthcare.model.entities.VisitStatus;
import com.healthcare.model.repo.VisitRepo;
import com.healthcare.service.VisitService;
import com.healthcare.service.dto.VisitDTO;
import com.healthcare.service.dto.converter.DTOConverter;
import com.healthcare.service.exception.VisitException;
import com.healthcare.service.exception.VisitNotFoundException;

@Service
@Transactional(readOnly = true)
public class VisitServiceImpl implements VisitService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VisitServiceImpl.class);
	
	private VisitRepo visitRepo;
	
	private DTOConverter<VisitDTO, Visit> visitConverter;
	
	@Autowired
	public VisitServiceImpl(VisitRepo visitRepo, DTOConverter<VisitDTO, Visit> visitConverter) {
		this.visitRepo = visitRepo;
		this.visitConverter = visitConverter;
	}
	
	@Override
	@Transactional(readOnly = false)
	public VisitDTO addVisit(VisitDTO visitDTO) {
		LOGGER.info("An attempt to add visit: {}", visitDTO);
		validateNewVisit(visitDTO);
		
		Visit visit = visitConverter.convertFromDTO(visitDTO);
		visit.setStatus(VisitStatus.ACTIVE);
		Visit registeredVisit = visitRepo.save(visit);
		LOGGER.info("Successfully registered visit: {}", registeredVisit);
		return visitConverter.convertFromEntity(registeredVisit);
	}
	
	private void validateNewVisit(VisitDTO visitDTO) {
		LocalDateTime visitDateTime = LocalDateTime.of(visitDTO.getVisitDate(), visitDTO.getVisitTime());
		if (visitDateTime.isBefore(LocalDateTime.now())) {
			LOGGER.info("Unable to add visit: {}", visitDTO);
			throw new VisitException("Cannot add visit with past date");
		}
		Visit existingVisit = visitRepo.findVisitByDoctorAndDateTimeNotCancelled(
				visitDTO.getDoctorId(), visitDTO.getVisitDate(), visitDTO.getVisitTime());
		if (existingVisit != null) {
			LOGGER.info("Unable to add visit: {}", visitDTO);
			throw new VisitException("Visit at given time is not available");
		}
	}

	@Override
	public VisitDTO getVisitById(Long id) {
		Visit visit = visitRepo.findById(id)
				.orElseThrow(() -> new VisitNotFoundException("Visit with id: " + id + " does not exist"));
		return visitConverter.convertFromEntity(visit);
	}

	@Override
	public List<VisitDTO> getVisitsByPatientId(Long patientId) {
		List<Visit> visits = visitRepo.findVisitsByPatient(patientId);
		return visitConverter.convertFromEntity(visits);
	}
	
	@Override
	public List<VisitDTO> getVisitsByDoctorId(Long doctorId) {
		List<Visit> visits = visitRepo.findVisitsByDoctor(doctorId);
		return visitConverter.convertFromEntity(visits);
	}

	@Override
	@Transactional(readOnly = false)
	public VisitDTO updateVisit(VisitDTO visitDTO) {
		Visit persistedVisit = visitRepo.findById(visitDTO.getId())
				.orElseThrow(() -> new VisitNotFoundException("Visit with id: " + visitDTO.getId() + " does not exist"));
		validateExistingVisit(persistedVisit, visitDTO);
		persistedVisit.setStatus(visitDTO.getStatus());
		persistedVisit.setDescription(visitDTO.getDescription());
		persistedVisit = visitRepo.save(persistedVisit);
		LOGGER.info("Successfully updated visit: {}", persistedVisit);
		return visitConverter.convertFromEntity(persistedVisit);
	}
	
	private void validateExistingVisit(Visit visit, VisitDTO visitDTO) {
		if (visit.getStatus() == VisitStatus.CANCELLED && visitDTO.getStatus() != VisitStatus.CANCELLED) {
			Visit existingVisit = visitRepo.findVisitByDoctorAndDateTimeNotCancelled(
					visitDTO.getDoctorId(), visitDTO.getVisitDate(), visitDTO.getVisitTime());
			if (existingVisit != null) {
				LOGGER.info("Unable to update visit: {}", visitDTO);
				throw new VisitException("Visit at given time is not available");
			}
		}
	}
}
