package com.healthcare.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DoctorDTO extends PersonDTO {
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String specialty;

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	@Override
	public String toString() {
		return "DoctorDTO [id=" + getId() 
				+ ", firstName=" + getFirstName() 
				+ ", lastName=" + getLastName() 
				+ ", birthDate=" + getBirthDate() 
				+ ", address=" + getAddress() 
				+ ", phoneNumber=" + getPhoneNumber()
				+ ", email=" + getEmail() 
				+ ", specialty=" + specialty + "]";
	}	
}
