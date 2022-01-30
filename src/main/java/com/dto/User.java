package com.dto;

import org.springframework.stereotype.Component;

@Component
public class User {
	private String username;
	private String userfirstname;
	private String userlastname;
	private String password;
	private String email;
	public User(String username, String userfirstname, String userlastname, String password) {
		this.username=username;
		this.userfirstname=userfirstname;
		this.userlastname=userlastname;
		this.password=password;
				
	}
	
	public User(String username, String userfirstname, String userlastname, String password, String email) {
		this.username=username;
		this.userfirstname=userfirstname;
		this.userlastname=userlastname;
		this.password=password;
		this.email=email;
				
	}
	public User(String username, String password) {
		this.username=username;
		this.password=password;
	}
	
	public User() {
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserfirstname() {
		return userfirstname;
	}
	public void setUserfirstname(String userfirstname) {
		this.userfirstname = userfirstname;
	}
	public String getUserlastname() {
		return userlastname;
	}
	public void setUserlastname(String userlastname) {
		this.userlastname = userlastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", userfirstname=" + userfirstname + ", userlastname=" + userlastname
				+ ", password=" + password + "]";
	}
	
	

}
