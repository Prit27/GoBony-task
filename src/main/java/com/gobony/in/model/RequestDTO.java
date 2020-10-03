package com.gobony.in.model;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



public class RequestDTO {
	
			private String uuid;
		
			@NotNull
			private String first_name;
			
			@NotBlank
			private String last_name;
			
			@Email
			private String email;
			
			
			private String gender;


			private List<String> mobiles;
			
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
			
			public String getGender() {
				return gender;
			}
			public void setGender(String gender) {
				this.gender = gender;
			}
			
			public List<String> getMobiles() {
				return mobiles;
			}
			public void setMobiles(List<String> mobiles) {
				this.mobiles = mobiles;
			}
			
			public String getUuid() {
				return uuid;
			}
			public void setUuid(String uuid) {
				this.uuid = uuid;
			}
			
			
}
