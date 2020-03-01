package com.healthcare.rest.patient;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.healthcare.rest.common.entity.Person;

@Entity
@Table(name = "patients")
public class Patient extends Person {

	@Override
	public String toString() {
		return "Patient [firstName=" + getFirstName() 
				+ ", lastName=" + getLastName() 
				+ ", birthDate=" + getBirthDate()
				+ ", address=" + getAddress() 
				+ ", phoneNumber=" + getPhoneNumber()
				+ ", email=" + getEmail() 
				+ ", id=" + getId() + "]";
	}	
}
