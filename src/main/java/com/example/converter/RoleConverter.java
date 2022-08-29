package com.example.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.model.Role;
import com.example.model.RoleDto;

@Mapper(componentModel = "spring")
public interface RoleConverter {

	@Mapping(source = "id", target = "roleId")
	@Mapping(source = "role", target = "roleName")
	RoleDto convertRoleToDto(Role role);

	@Mapping(source = "roleId", target = "id")
	@Mapping(source = "roleName", target = "role")
	Role convertDtoToRole(RoleDto dto);

	@Mapping(source = "id", target = "roleId")
	@Mapping(source = "role", target = "roleName")
	List<RoleDto> convertRolesToDtos(List<Role> roles);

	@Mapping(source = "roleId", target = "id")
	@Mapping(source = "roleName", target = "role")
	List<Role> convertDtosToRoles(List<RoleDto> roleDtos);

}
