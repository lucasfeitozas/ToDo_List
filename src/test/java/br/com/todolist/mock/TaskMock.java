package br.com.todolist.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.todolist.entity.Task;

public class TaskMock {
	
	public static Task createEntity() {
		Task entity = new Task();
		entity.setId(1);
		entity.setTitle("Some Title");
		entity.setUser(UserMock.createEntity());
		
		return entity;
	}

	public static List<Task> createEntityList() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(createEntity());
		return tasks;
	}

}
