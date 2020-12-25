package br.com.todolist.dto;

import java.io.Serializable;

public class ItemDetailResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;

	private String description;

	private UserDTO user;

	private Integer taskId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer listId) {
		this.taskId = listId;
	}

	@Override
	public String toString() {
		return "ItemDetailResponseDTO [title=" + title + ", description=" + description + ", user=" + user + ", listId="
				+ taskId + "]";
	}

}
