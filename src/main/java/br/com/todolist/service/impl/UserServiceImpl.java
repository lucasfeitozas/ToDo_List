package br.com.todolist.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.todolist.converters.UserConverter;
import br.com.todolist.dto.UserDTO;
import br.com.todolist.entity.UserTask;
import br.com.todolist.repository.UserRepository;
import br.com.todolist.service.UserService;
import br.com.todolist.service.exception.BusinessException;
import br.com.todolist.util.MessageConstants;

/**
 * @author Lucas Feitozas
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserDTO save(UserDTO usuario) throws BusinessException {
		
		if (usuario == null) {
			throw new BusinessException(MessageConstants.USER_NOT_FOUND);
		}
		
		UserTask user = UserConverter.toEntity(usuario);
		
		Integer countByEmailOrLogin = userRepository.countByEmailOrLogin(user.getEmail(), user.getLogin(), formatId(user.getId()));
		if (countByEmailOrLogin > 0) {
			throw new BusinessException(MessageConstants.USER_LOGIN_OR_EMAIL_ALREADY_EXISTS);
		}
		user.setPassword(encoder.encode(user.getPassword()));
		
		return UserConverter.toDTO(userRepository.save(user));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserDTO update(Integer id, UserDTO dto) throws BusinessException {
		
		Optional<UserTask> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new BusinessException(MessageConstants.USER_NOT_FOUND);
		}
		
		UserTask user = UserConverter.toEntity(userOptional.get(), dto);
		
		Integer countByEmailOrLogin = userRepository.countByEmailOrLogin(user.getEmail(), user.getLogin(), formatId(user.getId()));
		if (countByEmailOrLogin > 0) {
			throw new BusinessException(MessageConstants.USER_LOGIN_OR_EMAIL_ALREADY_EXISTS);
		}
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		return UserConverter.toDTO(userRepository.save(user));
	}
	
	private static Integer formatId(Integer id) {
		return id != null ? id : 0;
	}

	@Override
	public UserDTO findUserById(Integer userId) throws BusinessException {
		Optional<UserTask> userOptional = userRepository.findById(userId);
		
		if (userOptional.isPresent()) {
			return UserConverter.toDTO(userOptional.get());
		}
		throw new BusinessException(MessageConstants.USER_NOT_FOUND);
	}
	
}
