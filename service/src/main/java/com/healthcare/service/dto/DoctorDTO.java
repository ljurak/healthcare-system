package com.healthcare.service.dto;

public class DoctorDTO extends PersonDTO {
	
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
