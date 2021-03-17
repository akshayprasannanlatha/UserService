package com.userservice.userservice.dto;

public class LoginDTO {

	String Email;
	String password;
	
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginDTO() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "LoginDTO [Email=" + Email + ", password=" + password + "]";
	}

	
}
