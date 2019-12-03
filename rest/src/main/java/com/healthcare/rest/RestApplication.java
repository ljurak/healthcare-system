package com.healthcare.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.healthcare.model.entities.EntityPackage;
import com.healthcare.model.repo.JpaRepoPackage;

@SpringBootApplication(scanBasePackages = "com.healthcare")
@EntityScan(basePackageClasses = EntityPackage.class)
@EnableJpaRepositories(basePackageClasses = JpaRepoPackage.class)
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}
}
