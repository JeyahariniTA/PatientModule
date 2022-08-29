package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.converter.RoleConverter;
import com.example.model.Role;
import com.example.model.RoleDto;
import com.example.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	RoleConverter roleConverter;

	@Override
	public List<RoleDto> listRoles() {
		return roleConverter.convertRolesToDtos(roleRepository.findAll());
	}

	@Override
	public RoleDto addRole(Role role) {
		return roleConverter.convertRoleToDto(roleRepository.save(role));
	}

	@Override
	public List<RoleDto> getRoleByName(String roleName) {
		return roleConverter.convertRolesToDtos(roleRepository.findByRole(roleName));
	}

	@Override
	public RoleDto updateRole(Role role) {
		if (roleRepository.existsById(role.getId())) {
//			roleRepository.
			return roleConverter.convertRoleToDto(roleRepository.save(role));
		} else {
			return new RoleDto();
		}
	}

	@Override
	public String deleteRole(int id) {
		if (roleRepository.existsById(id)) {
			roleRepository.deleteById(id);
			return "deleted";
		} else {
			return "id not found";
		}
	}

	@Override
	public RoleDto findRoleByUserId(int userId) {
		return roleConverter.convertRoleToDto(roleRepository.findByUsers_Id(userId)); // Role
//		role = roleRepository.getReferenceById(userId); //
//		System.out.println(roleMapper.convertRoleToDto(role)); // return new
//		RoleDto();
//		return new RoleDto();
	}

}
