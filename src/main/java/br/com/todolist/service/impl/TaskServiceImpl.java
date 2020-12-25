package br.com.todolist.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.todolist.dto.CreateTaskRequestDTO;
import br.com.todolist.dto.CreateTaskResponseDTO;
import br.com.todolist.dto.TaskDetailResponseDTO;
import br.com.todolist.dto.TaskResponseDTO;
import br.com.todolist.entity.Task;
import br.com.todolist.repository.TaskRepository;
import br.com.todolist.security.utils.UsuarioUtils;
import br.com.todolist.service.TaskService;
import br.com.todolist.service.exception.BusinessException;
import br.com.todolist.util.MessageConstants;

/**
 * @author Lucas Feitozas
 *
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public CreateTaskResponseDTO save(CreateTaskRequestDTO dto) throws BusinessException {
		
		Integer countByTitle = taskRepository.countByTitle(dto.getTitle());
		if (countByTitle > 0) {
			throw new BusinessException(MessageConstants.LIST_ALREADY_EXISTS);
		}
		
		Task lista = new Task();
		lista.setTitle(dto.getTitle());
		lista.setUser(UsuarioUtils.getAuthenticatedUser());
		
		Task entity = taskRepository.save(lista);
		
		return convertToDTO(entity);
	}

	private CreateTaskResponseDTO convertToDTO(Task entity) {
		CreateTaskResponseDTO response = new CreateTaskResponseDTO();
		response.setList(new TaskDetailResponseDTO(entity.getId(), entity.getTitle()));
		return response;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteTask(Integer id) {
		taskRepository.deleteById(id);
	}

	@Override
	public List<TaskResponseDTO> findAll() {
		List<Task> listResult = taskRepository.findAllByOrderByTitle();
		
		return listResult.stream().map(item -> convertToTaskResponseDTO(item)).collect(Collectors.toList());
	}

	@Override
	public TaskResponseDTO findById(Integer id) throws BusinessException {
		
		Optional<Task> optional = taskRepository.findById(id);
		if (!optional.isPresent()) {
			throw new BusinessException(MessageConstants.LIST_NOT_FOUND);
		}
		return convertToTaskResponseDTO(optional.get());
	}

	@Override
	public List<TaskResponseDTO> findAllByUserId(Integer userId) {
		List<Task> listaResult = taskRepository.findByUserId(userId);
		return listaResult.stream().map(item -> convertToTaskResponseDTO(item)).collect(Collectors.toList());
	}

	private TaskResponseDTO convertToTaskResponseDTO(Task lista) {
		TaskResponseDTO response = new TaskResponseDTO();
		response.setList(new TaskDetailResponseDTO(lista.getId(), lista.getTitle()));
		response.setUserId(lista.getUser().getId());
		return response;
	}
}
