package com.example.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import com.example.model.User;
import com.example.model.UserDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserConverter {

	@Mapping(source = "id", target = "userId")
	@Mapping(source = "name", target = "userName")
	UserDto convertUserToDto(User user);

	@Mapping(source = "userId", target = "id")
	@Mapping(source = "userName", target = "name")
	User convertDtoToUser(UserDto dto);

	@Mapping(source = "id", target = "userId")
	@Mapping(source = "name", target = "userName")
	List<UserDto> convertUsersToDtos(List<User> listUsers);

	@Mapping(source = "userId", target = "id")
	@Mapping(source = "userName", target = "name")
	List<User> convertDtosToUsers(List<UserDto> dtos);

//	JsonNode convertUserToValue(User user);
//
//	User convertValueToUser(JsonNode node);
//	
//	ObjectMapper mapper = new ObjectMapper(); 
//	JsonNode node = mapper.convertValue(User user, JsonNode.class);

}
