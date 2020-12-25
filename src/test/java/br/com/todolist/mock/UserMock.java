package br.com.todolist.mock;

import br.com.todolist.converters.UserConverter;
import br.com.todolist.dto.UserDTO;
import br.com.todolist.entity.UserTask;

public class UserMock {

	public static UserDTO createUserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail("frodo@example.com");
		userDTO.setLogin("frodo");
		userDTO.setName("Frodo Baggins");
		userDTO.setPassword("password");
		return userDTO;
	}

	public static UserTask createEntity() {
		
		UserTask entity = UserConverter.toEntity(createUserDTO());
		entity.setId(1);
		
		return entity;
	}

}
