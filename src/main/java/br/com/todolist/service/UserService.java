package br.com.todolist.service;

import br.com.todolist.dto.UserDTO;
import br.com.todolist.service.exception.BusinessException;

public interface UserService {

	UserDTO save(UserDTO usuario) throws BusinessException;

	UserDTO findUserById(Integer userId) throws BusinessException;

	UserDTO update(Integer id, UserDTO usuario) throws BusinessException;
}
