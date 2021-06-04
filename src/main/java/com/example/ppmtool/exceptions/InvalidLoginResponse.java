package com.example.ppmtool.exceptions;


import lombok.Data;

@Data
public class InvalidLoginResponse {
	
	private String username;
	private String password;
	public InvalidLoginResponse() {
		super();
		this.username = "Invalid username";
		this.password = "Invalid password";
	}
	
	

}
