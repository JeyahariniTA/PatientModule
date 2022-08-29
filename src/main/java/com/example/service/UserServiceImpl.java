package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.converter.UserConverter;
import com.example.model.User;
import com.example.model.UserDto;
import com.example.repository.ProjectRepository;
import com.example.repository.UserJPARepository;
import com.example.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserJPARepository jpaRepository;

	@Autowired
	UserConverter userConverter;

	@Autowired
	ProjectRepository projectRepository;

	public static final String HASH_KEY = "Users";
	@Autowired
	private RedisTemplate<String, ?> template;

	@Override
	public List<UserDto> listUsers() {
		template.opsForHash().values(HASH_KEY);
		return userConverter.convertUsersToDtos(jpaRepository.findAll());
	}

	@Override
	public UserDto getUserById(int id) {
		try {
			if (userRepository.existsById(id)) {
//				template.opsForHash().get(HASH_KEY, id);
				return userConverter.convertUserToDto(userRepository.findById(id).get());
			} else {
				return new UserDto();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public UserDto addUser(User user) {
		return userConverter.convertUserToDto(userRepository.save(user));
	}

	@Override
	public String deleteUser(int id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			template.opsForHash().delete(HASH_KEY, id);
			return "Deleted Successfully!";
		} else {
			return "Please provide valid user id to delete.";
		}
	}

	@Override
	@CachePut(value = "UsersUpdate")
	public UserDto updateById(User user) {
		if (jpaRepository.existsById(user.getId())) {
			template.opsForHash().put(HASH_KEY, user.getId(), user);
//			System.out.println("user: " + user.toString());
			return userConverter.convertUserToDto(jpaRepository.save(user));
		} else {
			return new UserDto();
		}
	}

	public List<UserDto> getUsersByRoleId(int roleId) {
		return userConverter.convertUsersToDtos(jpaRepository.findByRoleId(roleId));

	}

	/*
	 * @Override public RoleDto getRoleByUserId(int id) {
	 * System.out.println("findrolebyid" + jpaRepository.findRoleById(id)); return
	 * roleMapper.convertRoleToDto(jpaRepository.findRoleById(id)); }
	 */

	@Override
	public List<UserDto> listUsersWithPagination(int pageNo, int usersCount) {
//		Sort sortOrder = Sort.by("id").descending();
		List<UserDto> list = new ArrayList<>();
		PageRequest pageReq = PageRequest.of(pageNo, usersCount);
//		jpaRepository.findAll(pageReq).forEach(users -> list.add(userConverter.convertUserToDto(users)));
		List<User> userList = jpaRepository.findAll(pageReq).getContent();
		for (User user : userList) {
			System.out.println("id: " + user.getId());
		}
		list = userConverter.convertUsersToDtos(jpaRepository.findAll(pageReq).getContent());

		return list;
	}

	@Override
	public UserDto partialUpdate(int id, String gender) {
		UserDto userDto = new UserDto();

		if (jpaRepository.existsById(id)) {
			User user = jpaRepository.findById(id).get();
			user.setGender(gender);
			System.out.println("user: " + user.toString());
			userDto = userConverter.convertUserToDto(jpaRepository.save(user));
		}
		return userDto;
	}

	@Override
	public List<UserDto> findUsersByProjectId(int projectId) {
		if (projectRepository.existsById(projectId)) {
			List<Object[]> resultSet = jpaRepository.findUsersByProjectId(projectId);
			List<UserDto> listDto = new ArrayList<>();
			UserDto dto;
			for (Object[] rs : resultSet) {
				dto = new UserDto();
				dto.setUserId((int) rs[0]);
				dto.setUserName((String) rs[1]);
				listDto.add(dto);
			}
			return listDto;
		} else {
			List<UserDto> list = new ArrayList<>();
			return list;
		}
	}

	@Override
	public List<Object[]> findRoleUsersByProjectId(int projectId) {
		List<Object[]> resultSet = jpaRepository.findRoleUsersByProjectId(projectId);
		return resultSet;
	}

	@Override
	public void partialUserUpdate(int id, JsonPatch patch) throws JsonProcessingException, JsonPatchException {
		if (jpaRepository.existsById(id)) {
			User user = jpaRepository.findById(id).get();
			User userPatched = applyPatchToUser(patch, user);
			System.out.println("userPatched: " + userPatched.getDob());
			updateById(userPatched);
		}

	}

//
//	private User applyPatchToUser(JsonPatch patch, User targetUser) throws JsonPatchException, JsonProcessingException {
//		JsonNode patched = patch.apply(userConverter.convertUserToValue(targetUser));
//		return userConverter.convertValueToUser(patched);
//	}

	private User applyPatchToUser(JsonPatch patch, User targetUser) throws JsonPatchException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonMapper jsonObjectMapper = new JsonMapper();
//		configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
		return jsonObjectMapper.treeToValue(patched, User.class);
	}

}
