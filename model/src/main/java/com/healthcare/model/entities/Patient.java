package com.healthcare.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient extends Person {

}
