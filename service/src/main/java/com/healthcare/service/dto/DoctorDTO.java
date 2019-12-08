package com.healthcare.service.dto;

public class DoctorDTO extends PersonDTO {

	@Override
	public String toString() {
		return "DoctorDTO [firstName=" + getFirstName() 
				+ ", lastName=" + getLastName() 
				+ ", birthDate=" + getBirthDate() 
				+ ", address=" + getAddress() 
				+ ", phoneNumber=" + getPhoneNumber()
				+ ", email=" + getEmail() + "]";
	}	
}
