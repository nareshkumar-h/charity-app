package com.charityapp.model;

public class Admin {
	
	private int id;
	private String name;
	private String email;
	private String role;
	private String date;
	private String password;
	private Boolean isAdminLoggedIn;
	public Boolean getIsAdminLoggedIn() {
		return isAdminLoggedIn;
	}
	public void setIsAdminLoggedIn(Boolean isAdminLoggedIn) {
		this.isAdminLoggedIn = isAdminLoggedIn;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", email=" + email + ", role=" + role + ", date=" + date
				+ ", password=" + password + ", isAdminLoggedIn=" + isAdminLoggedIn + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
