package br.com.todolist.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.todolist.dto.CreateTaskRequestDTO;
import br.com.todolist.dto.CreateTaskResponseDTO;
import br.com.todolist.dto.TaskResponseDTO;
import br.com.todolist.entity.Task;
import br.com.todolist.mock.AuthenticationMock;
import br.com.todolist.mock.TaskMock;
import br.com.todolist.repository.TaskRepository;
import br.com.todolist.service.exception.BusinessException;

/**
 * @author lucasfeitozas
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

	@InjectMocks
	private TaskServiceImpl service;
	
	@Mock
	private TaskRepository taskRepository;
	
	@Test
	public final void shouldSaveList() {
		SecurityContextHolder.getContext().setAuthentication(new AuthenticationMock());
		
		when(taskRepository.save(any(Task.class))).thenReturn(TaskMock.createEntity());
		
		CreateTaskRequestDTO dto = new CreateTaskRequestDTO();
		dto.setTitle("Some Title");
		CreateTaskResponseDTO taskResponseDTO = service.save(dto);
		
		assertNotNull(taskResponseDTO);
		assertNotNull(taskResponseDTO.getList());
		assertNotNull(taskResponseDTO.getList().getId());
		assertNotNull(taskResponseDTO.getList().getTitle());
	}
	
	@Test(expected = BusinessException.class)
	public final void shouldThrowAnExceptionWhenListAlreadyExists() {
		CreateTaskRequestDTO dto = new CreateTaskRequestDTO();
		dto.setTitle("Some Title");

		when(taskRepository.countByTitle(dto.getTitle())).thenReturn(1);
		service.save(dto);
	}

	@Test
	public final void testDeleteTask() {
		Integer id = 1;
		service.deleteTask(id);
		assertNotNull(id);
	}

	@Test
	public final void testFindAll() {
		when(taskRepository.findAllByOrderByTitle()).thenReturn(TaskMock.createEntityList());
		
		List<TaskResponseDTO> list = service.findAll();
		
		assertNotNull(list);
		assertNotNull(list.get(0).getList().getId());
	}

	@Test
	public final void shouldFindTaskById() {
		int taskId = 1;
		when(taskRepository.findById(taskId)).thenReturn(Optional.of(TaskMock.createEntity()));
		TaskResponseDTO dto = service.findById(1);
		
		assertNotNull(dto);
	}
	
	@Test(expected = BusinessException.class)
	public final void shouldThrowAnExceptionWhenTaskNotFound() {
		service.findById(1);
	}

	@Test
	public final void testFindAllByUserId() {
		when(taskRepository.findByUserId(1)).thenReturn(TaskMock.createEntityList());
		
		List<TaskResponseDTO> list = service.findAllByUserId(1);
		
		assertNotNull(list);
		assertNotNull(list.get(0).getList().getId());

	}

}
