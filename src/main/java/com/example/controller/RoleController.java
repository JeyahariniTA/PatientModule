package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.converter.RoleConverter;
import com.example.exception.CustomException;
import com.example.model.Role;
import com.example.model.RoleDto;
import com.example.service.RoleService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(path = "/role")
@SecurityRequirement(name = "bearerAuth")
public class RoleController {

	@Autowired
	RoleService dao;

	@Autowired
	RoleConverter roleMapper;

	@GetMapping(path = "/get")
	public MappingJacksonValue listRoles() {
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("roleFilter", simpleBeanPropertyFilter);
		List<RoleDto> roleDtos = dao.listRoles();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(roleDtos);
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
	}

	@GetMapping(path = "/get/{roleName}")
	public List<RoleDto> getRolesByName(@PathVariable("roleName") String roleName) {
		return dao.getRoleByName(roleName);
	}

	@PostMapping(path = "/add")
	public RoleDto addRole(@RequestBody Role role) {
		return dao.addRole(role);
	}

	@PutMapping(path = "/update")
	public RoleDto updateRole(@RequestBody RoleDto roleDto) {
		return dao.updateRole(roleMapper.convertDtoToRole(roleDto));
	}

	@DeleteMapping(path = "/delete/{roleId}")
	public ResponseEntity<Object> deleteRole(@PathVariable("roleId") int id) {
		String message = dao.deleteRole(id);
		if (message.equalsIgnoreCase("deleted")) {
			return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		} else {
			throw new CustomException("Role Id Not Found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/getByUserId/{userId}")
	public RoleDto getRolesByUserId(@PathVariable("userId") int userId) {
//		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("users");
//		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("roleFilter", simpleBeanPropertyFilter);
//		RoleDto roleDto = dao.findRoleByUserId(userId);
//		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(roleDto);
//		mappingJacksonValue.setFilters(filterProvider);
//		return mappingJacksonValue;
		return dao.findRoleByUserId(userId);
	}

}
