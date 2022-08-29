package com.example.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4999671781205117900L;
	private int userId;
	private String userName;
	private List<Address> addresses;
	private RoleDto role;
	private List<Project> projects;
	private String dob;
	private String gender;

	public String getDob() {
		return dob;
	}

	public void setRole(RoleDto role) {
		this.role = role;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public RoleDto getRole() {
		return role;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
