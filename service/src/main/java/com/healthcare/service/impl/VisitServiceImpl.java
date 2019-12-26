package com.healthcare.service.impl;

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
		boolean isVisitAvailable = checkVisitAvailability(visitDTO);
		if (isVisitAvailable) {
			Visit visit = visitConverter.convertFromDTO(visitDTO);
			visit.setStatus(VisitStatus.ACTIVE);
			Visit registeredVisit = visitRepo.save(visit);
			LOGGER.info("Successfully registered visit: {}", registeredVisit);
			return visitConverter.convertFromEntity(registeredVisit);
		} else {
			LOGGER.info("Unable to add visit: {}", visitDTO);
			throw new VisitException("Visit at given time is not available");
		}
	}
	
	private boolean checkVisitAvailability(VisitDTO visitDTO) {
		Visit existingVisit = visitRepo.findVisitByDoctorAndDateTime(
				visitDTO.getDoctorId(), visitDTO.getVisitDate(), visitDTO.getVisitTime());
		return existingVisit == null;
	}

	@Override
	public VisitDTO getVisitById(Long id) {
		Visit visit = visitRepo.findById(id)
				.orElseThrow(() -> new VisitNotFoundException("Visit with id: " + id + " does not exist"));
		return visitConverter.convertFromEntity(visit);
	}

	@Override
	public List<VisitDTO> getVisitByPatientId(Long patientId) {
		List<Visit> visits = visitRepo.findVisitsByPatient(patientId);
		return visitConverter.convertFromEntity(visits);
	}

	@Override
	@Transactional(readOnly = false)
	public VisitDTO updateVisit(VisitDTO visitDTO, Long id) {
		Visit persistedVisit = visitRepo.findById(id)
				.orElseThrow(() -> new VisitNotFoundException("Visit with id: " + id + " does not exist"));
		persistedVisit.setStatus(visitDTO.getStatus());
		persistedVisit.setDescription(visitDTO.getDescription());
		persistedVisit = visitRepo.save(persistedVisit);
		LOGGER.info("Successfully updated visit: {}", persistedVisit);
		return visitConverter.convertFromEntity(persistedVisit);
	}	
}
