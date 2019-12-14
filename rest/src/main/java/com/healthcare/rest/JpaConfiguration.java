package com.healthcare.rest;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.healthcare.model.entities.EntityPackage;
import com.healthcare.model.repo.JpaRepoPackage;

@Configuration
@EntityScan(basePackageClasses = EntityPackage.class)
@EnableJpaRepositories(basePackageClasses = JpaRepoPackage.class)
public class JpaConfiguration {

}
