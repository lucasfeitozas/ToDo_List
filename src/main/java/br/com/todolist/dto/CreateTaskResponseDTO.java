package br.com.todolist.dto;

import java.io.Serializable;

public class CreateTaskResponseDTO implements Serializable{

	private static final long serialVersionUID = -4697791603278512869L;

	private TaskDetailResponseDTO list;

	public TaskDetailResponseDTO getList() {
		return list;
	}

	public void setList(TaskDetailResponseDTO list) {
		this.list = list;
	}
	
}
