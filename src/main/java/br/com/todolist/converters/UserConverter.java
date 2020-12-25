package br.com.todolist.converters;

import br.com.todolist.dto.UserDTO;
import br.com.todolist.entity.UserTask;

public class UserConverter {
	private UserConverter() {
		super();
	}

	public static UserTask toEntity(UserDTO dto) {
		UserTask user = new UserTask();
		user.setId(dto.getId());
		user.setLogin(dto.getLogin());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());

		return user;
	}
	
	public static UserTask toEntity(UserTask user, UserDTO dto) {
		user.setLogin(dto.getLogin());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		return user;
	}

	public static UserDTO toDTO(UserTask user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setLogin(user.getLogin());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());

		return dto;
	}
}
