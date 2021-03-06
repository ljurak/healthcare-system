package com.healthcare.rest.doctor;

import java.util.List;

import com.healthcare.rest.doctor.dto.DoctorDTO;

interface DoctorService {
	
	/**
	 * Retrieves list of all doctors.
	 * 
	 * @return list of all patients
	 */
	List<DoctorDTO> getDoctors();
	
	/**
	 * Adds new doctor.
	 * 
	 * @param doctor doctor
	 * @return added doctor
	 */
	DoctorDTO addDoctor(DoctorDTO doctorDTO);
	
	/**
	 * Retrieves doctor by given id.
	 * Throws <code>DoctorNotFoundException</code> if doctor
	 * with given id does not exist.
	 * 
	 * @param id id
	 * @return doctor 
	 * @throws DoctorNotFoundException
	 */
	DoctorDTO getDoctorById(Long id);
	
	/**
	 * Retrieves doctors with matching last name or its part.
	 * 
	 * @param lastName doctor last name or its part
	 * @return list of doctors
	 */
	List<DoctorDTO> getDoctorsByLastName(String lastName);
	
	/**
	 * Retrieves doctors with given specialty.
	 * 
	 * @param specialty doctor's specialty
	 * @return list of doctors
	 */
	List<DoctorDTO> getDoctorsBySpecialty(String specialty);
	
	/**
	 * Updates doctor data.
	 * Throws <code>DoctorNotFoundException</code> if doctor
	 * with given id does not exist.
	 * 
	 * @param doctor doctor to be updated
	 * @param id doctor id
	 * @return updated doctor
	 * @throws DoctorNotFoundException
	 */
	DoctorDTO updateDoctor(DoctorDTO doctorDTO, Long id);
}
