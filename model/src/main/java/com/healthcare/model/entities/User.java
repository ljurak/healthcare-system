package com.healthcare.model.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "USERS")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 40)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(name = "first_name", nullable = false, length = 40)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 40)
	private String lastName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "users_roles",
		uniqueConstraints = @UniqueConstraint(name = "users_roles_idx1", columnNames = { "user_id", "role_id" }),
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name ="role_id", referencedColumnName = "id")
	)
	private Set<Role> roles = new HashSet<>();
	
	@Column(name = "create_time", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createTime;
	
	@Column(name = "update_time", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updateTime;
	
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
}
