package br.com.todolist.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemRequestDTO implements Serializable{

	private static final long serialVersionUID = -8433392677046224063L;

	@NotNull
	private String title;
	
	@NotNull
	private String description;
	
	@NotNull
	@JsonProperty("user_id")
	private Integer userId;

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ItemRequestDTO [title=" + title + ", description=" + description + ", userId=" + userId + "]";
	}
	
}
