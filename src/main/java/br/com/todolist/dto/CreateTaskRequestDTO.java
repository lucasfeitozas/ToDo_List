package br.com.todolist.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class CreateTaskRequestDTO implements Serializable{

	private static final long serialVersionUID = -1908746020749941001L;
	
	@NotNull
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
