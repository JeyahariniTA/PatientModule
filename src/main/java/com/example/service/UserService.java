package com.example.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.example.model.User;
import com.example.model.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

public interface UserService {

	@Cacheable(value = "UsersCache")
	public List<UserDto> listUsers();

	public List<UserDto> listUsersWithPagination(int pageNo, int usersCount);

	@Cacheable(value = "UsersCache", key = "#id")
	public UserDto getUserById(int id);

	public UserDto addUser(User user);

	@CacheEvict(value = "UsersCache", key = "#id")
	public String deleteUser(int id);

	@CachePut(value = "UsersCache")
	public UserDto updateById(User user);

	public List<UserDto> getUsersByRoleId(int roleId);

//	public RoleDto getRoleByUserId(int id);

	public UserDto partialUpdate(int id, String gender);

	public void partialUserUpdate(int id, JsonPatch patch) throws JsonProcessingException, JsonPatchException;

	List<UserDto> findUsersByProjectId(int projectId);

	public List<Object[]> findRoleUsersByProjectId(int projectId);
}
