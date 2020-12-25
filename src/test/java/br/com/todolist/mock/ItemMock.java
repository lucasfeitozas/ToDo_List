package br.com.todolist.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.todolist.dto.ItemRequestDTO;
import br.com.todolist.entity.Item;

public class ItemMock {

	public static List<Item> createEntityList() {
		List<Item> list = new ArrayList<>();
		list.add(createEntity());
		
		return list;
	}

	public static Item createEntity() {
		Item item = new Item();
		item.setId(1);
		item.setTitle("title item");
		item.setDescription("description");
		item.setTask(TaskMock.createEntity());
		item.setUser(UserMock.createEntity());
		
		return item;
	}

	public static ItemRequestDTO createItemDetailResponseDTO() {
		ItemRequestDTO dto = new ItemRequestDTO();
		dto.setTitle("title content");
		dto.setDescription("text content description");
		dto.setUserId(2);
		return dto;
	}

}
