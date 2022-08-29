package com.example.service;

import java.util.List;

import com.example.model.Role;
import com.example.model.RoleDto;

public interface RoleService {

	public List<RoleDto> listRoles();

	public RoleDto addRole(Role role);

	public List<RoleDto> getRoleByName(String roleName);

	public RoleDto updateRole(Role role);

	public String deleteRole(int id);

	RoleDto findRoleByUserId(int userId);

}
