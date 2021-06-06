package com.example.ppmtool.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email(message = "please enter your email")
	@NotBlank(message = "email is required")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message = "password is required")
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@NotBlank(message = "enter your full name")
	private String fullName;
	
	private Date created_At;
	
	private Date updated_At;
	
	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();
	}
	
	protected void onUpdate() {
		this.updated_At = new Date();
	}

	
	

}
