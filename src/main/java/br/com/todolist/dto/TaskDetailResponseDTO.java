package br.com.todolist.dto;

import java.io.Serializable;

public class TaskDetailResponseDTO implements Serializable {

	private static final long serialVersionUID = 3853246370345886178L;

	public TaskDetailResponseDTO() {
		super();
	}

	public TaskDetailResponseDTO(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	private Integer id;
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ListaDetailResponseDTO [title=" + title + "]";
	}

}
