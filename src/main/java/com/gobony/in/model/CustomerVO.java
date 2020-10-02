package com.gobony.in.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Customer",uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class CustomerVO {

@Id
@Column(name="id")
private String cust_uuid;

@Column
@NotEmpty
private String first_name;

@NotEmpty
@Column
private String last_name;

@Column
@Email
private String email;

@Column
private String gender;



public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getCust_uuid() {
	return cust_uuid;
}

public void setCust_uuid(String cust_uuid) {
	this.cust_uuid = cust_uuid;
}

public String getFirst_name() {
	return first_name;
}

public void setFirst_name(String first_name) {
	this.first_name = first_name;
}

public String getLast_name() {
	return last_name;
}

public void setLast_name(String last_name) {
	this.last_name = last_name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}


	
	
	
}
