package br.com.todolist.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lucas Feitozas
 *
 */
public class TaskResponseDTO implements Serializable {

	private static final long serialVersionUID = -170117221016619923L;

	private TaskDetailResponseDTO list;
	
	@JsonProperty("user_id")
	private Integer userId;

	public TaskDetailResponseDTO getList() {
		return list;
	}

	public void setList(TaskDetailResponseDTO list) {
		this.list = list;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ListaResponseDTO [list=" + list + ", userId=" + userId + "]";
	}
	
	
	
}
