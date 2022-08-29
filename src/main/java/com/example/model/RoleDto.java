package com.example.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;

//@JsonFilter("roleFilter")
public class RoleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4500171365420053190L;
	private int roleId;
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	

}
