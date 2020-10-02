package com.gobony.in.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Mobile")
public class MobileVO {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
	
@Column(name="mobile_no")
@ElementCollection(targetClass=String.class)
private List<String> mobile_no;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public CustomerVO getCustomerVO() {
	return customerVO;
}

public void setCustomerVO(CustomerVO customerVO) {
	this.customerVO = customerVO;
}

@JoinColumn
@ManyToOne
private CustomerVO customerVO;

public List<String> getMobile_no() {
	return mobile_no;
}

public void setMobile_no(List<String> mobile_no) {
	this.mobile_no = mobile_no;
}






}
