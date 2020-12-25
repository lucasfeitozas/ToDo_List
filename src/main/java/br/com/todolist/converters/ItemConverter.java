package br.com.todolist.converters;

import java.util.List;
import java.util.stream.Collectors;

import br.com.todolist.dto.ItemDetailResponseDTO;
import br.com.todolist.dto.ItemResponseDTO;
import br.com.todolist.dto.UserDTO;
import br.com.todolist.entity.Item;

public class ItemConverter {

	public static ItemResponseDTO convertToDTO(List<Item> items) {

		ItemResponseDTO dto = new ItemResponseDTO();
		dto.setItems(items.stream().map(item -> convertToItemDetailResponseDTO(item)).collect(Collectors.toList()));

		return dto;
	}

	public static ItemDetailResponseDTO convertToItemDetailResponseDTO(Item entity) {
		ItemDetailResponseDTO dto = new ItemDetailResponseDTO();
		dto.setTitle(entity.getTitle());
		dto.setDescription(entity.getDescription());
		dto.setTaskId(entity.getTask().getId());
		UserDTO user = new UserDTO();
		user.setName(entity.getUser().getName());
		user.setEmail(entity.getUser().getEmail());
		dto.setUser(user);

		return dto;
	}
}
