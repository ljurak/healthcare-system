package com.healthcare.rest.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.rest.common.dto.DTOConverter;
import com.healthcare.rest.doctor.dto.DoctorDTO;
import com.healthcare.rest.doctor.exception.SpecialtyNotFoundException;

@Component
class DoctorConverterImpl implements DTOConverter<DoctorDTO, Doctor> {
	
	private SpecialtyRepo specialtyRepo;
	
	@Autowired	
	public DoctorConverterImpl(SpecialtyRepo specialtyRepo) {
		this.specialtyRepo = specialtyRepo;
	}

	@Override
	public Doctor convertFromDTO(DoctorDTO dto) {
		Specialty specialty = specialtyRepo.findByName(dto.getSpecialty()).orElseThrow(
				() -> new SpecialtyNotFoundException("Specialty [" + dto.getSpecialty() + "] does not exist"));
		Doctor entity = new Doctor();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setAddress(dto.getAddress());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setEmail(dto.getEmail());		
		entity.setSpecialty(specialty);
		return entity;
	}

	@Override
	public DoctorDTO convertFromEntity(Doctor entity) {
		DoctorDTO dto = new DoctorDTO();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setBirthDate(entity.getBirthDate());
		dto.setAddress(entity.getAddress());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setEmail(entity.getEmail());
		dto.setSpecialty(entity.getSpecialty().getName());
		return dto;
	}	
}
