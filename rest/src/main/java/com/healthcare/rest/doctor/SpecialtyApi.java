package com.healthcare.rest.doctor;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.rest.doctor.dto.SpecialtyDTO;

@RestController
@RequestMapping("/specialties")
@CrossOrigin
class SpecialtyApi {
	
	private DoctorFacade doctorFacade;

	public SpecialtyApi(DoctorFacade doctorFacade) {
		this.doctorFacade = doctorFacade;
	}
	
	@GetMapping
	public List<SpecialtyDTO> getSpecialties() {
		return doctorFacade.getSpecialties();
	}
}
