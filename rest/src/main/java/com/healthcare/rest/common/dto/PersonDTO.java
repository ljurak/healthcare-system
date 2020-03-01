package com.healthcare.rest.common.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public abstract class PersonDTO {
	
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 40)
	private String firstName;	
	
	@NotBlank
	@Size(min = 3, max = 40)
	private String lastName;	
	
	@Past
	@NotNull
	private LocalDate birthDate;
	
	@NotBlank
	@Size(min = 10, max = 100)
	private String address;	
	
	@NotBlank
	@Pattern(regexp = "^\\+?[0-9]{9,15}$")
	@Size(min = 9, max = 20)
	private String phoneNumber;	
	
	@Email
	@Size(min = 5, max = 100)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
