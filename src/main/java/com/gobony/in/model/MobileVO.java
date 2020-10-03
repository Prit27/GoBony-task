package com.gobony.in.model;

import javax.persistence.Column;
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
		
		private String mobile_no;
		
		
		@ManyToOne
		@JoinColumn(name = "customer_id")
		private CustomerVO customer;
		
		
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public CustomerVO getCustomer() {
			return customer;
		}
		
		public void setCustomer(CustomerVO customer) {
			this.customer = customer;
		}
		
		public String getMobile_no() {
			return mobile_no;
		}
		
		public void setMobile_no(String mobile_no) {
			this.mobile_no = mobile_no;
		}
		
		public MobileVO(String mobile) {
			this.mobile_no=mobile;
		}
		
		public MobileVO(int id, String mobile_no, CustomerVO customer) {
			super();
			this.id = id;
			this.mobile_no = mobile_no;
			this.customer = customer;
		}
		
		public MobileVO() {
			
		}
		
		
		

}
