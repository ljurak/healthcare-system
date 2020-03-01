package com.healthcare.rest.doctor;

import javax.persistence.Table;

import com.healthcare.rest.common.entity.Person;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "doctors")
public class Doctor extends Person {
	
	@ManyToOne
	@JoinColumn(name = "specialty_id", nullable = false)
	private Specialty specialty;

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	@Override
	public String toString() {
		return "Doctor [firstName=" + getFirstName() 
				+ ", lastName="	+ getLastName() 
				+ ", birthDate=" + getBirthDate() 
				+ ", address=" + getAddress()
				+ ", phoneNumber=" + getPhoneNumber() 
				+ ", email=" + getEmail() 
				+ ", id=" + getId()
				+ ", specialty=" + specialty + "]";
	}
}
