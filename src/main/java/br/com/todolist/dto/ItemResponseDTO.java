package br.com.todolist.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Response DTO for Item
 * 
 * @author lucasfeitozas
 *
 */
public class ItemResponseDTO {

	List<ItemDetailResponseDTO> items = new ArrayList<>();

	public List<ItemDetailResponseDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemDetailResponseDTO> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ItemResponseDTO [items=" + items + "]";
	}

}
