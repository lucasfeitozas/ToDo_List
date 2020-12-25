package br.com.todolist.service;

import java.util.List;

import br.com.todolist.dto.CreateTaskRequestDTO;
import br.com.todolist.dto.CreateTaskResponseDTO;
import br.com.todolist.dto.TaskResponseDTO;
import br.com.todolist.service.exception.BusinessException;

/**
 * @author lucasfeitozas
 *
 */
public interface TaskService {

	CreateTaskResponseDTO save(CreateTaskRequestDTO dto) throws BusinessException;

	void deleteTask(Integer id);
	
	List<TaskResponseDTO> findAll();

	TaskResponseDTO findById(Integer id) throws BusinessException;
	
	List<TaskResponseDTO> findAllByUserId(Integer userId);
	

}
