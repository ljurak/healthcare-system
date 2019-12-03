package com.healthcare.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	@NotBlank
	@Size(min = 3, max = 40)
	private String firstName;
	
	@Column(name = "last_name")
	@NotBlank
	@Size(min = 3, max = 40)
	private String lastName;
	
	@Column(name = "birth_date")
	@Past
	@NotNull
	private LocalDate birthDate;	

	@Column(name = "address")
	@NotBlank
	@Size(min = 10, max = 100)
	private String address;
	
	@Column(name = "phone_number")
	@NotBlank
	@Size(min = 3, max = 40)
	private String phoneNumber;
	
	@Column(name = "email")
	@Email
	@Size(min = 10, max = 100)
	private String email;	

	@Column(name = "create_time", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createTime;
	
	@Column(name = "update_time", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updateTime;

	public LocalDateTime getCreateTime() {
		return createTime;
	}
	
	public LocalDateTime getUpdateTime() {
		return updateTime;
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

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
