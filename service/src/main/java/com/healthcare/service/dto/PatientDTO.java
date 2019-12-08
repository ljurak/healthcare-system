package com.healthcare.service.dto;

public class PatientDTO extends PersonDTO {

	@Override
	public String toString() {
		return "PatientDTO [firstName=" + getFirstName() 
				+ ", lastName=" + getLastName() 
				+ ", birthDate=" + getBirthDate() 
				+ ", address=" + getAddress() 
				+ ", phoneNumber=" + getPhoneNumber()
				+ ", email=" + getEmail() + "]";
	}	
}
