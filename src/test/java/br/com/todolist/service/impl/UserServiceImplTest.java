package br.com.todolist.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.todolist.dto.UserDTO;
import br.com.todolist.entity.UserTask;
import br.com.todolist.mock.UserMock;
import br.com.todolist.repository.UserRepository;
import br.com.todolist.service.exception.BusinessException;

/**
 * @author lucasfeitozas
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	
	@Mock
	private PasswordEncoder encoder;
	
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	public final void testShouldSaveANewUser() {
		UserDTO createUserDTO = UserMock.createUserDTO();
		
		when(encoder.encode(createUserDTO.getPassword())).thenReturn("encoded-password");
		when(userRepository.save(any(UserTask.class))).thenReturn(UserMock.createEntity());
		
		UserDTO userDTO = userService.save(createUserDTO);
		
		assertNotNull(userDTO);
		assertNotNull(userDTO.getId());
	}
	
	@Test(expected=BusinessException.class)
	public final void testShouldThrowAnExceptionWhenUserIsNull() {
		userService.save(null);
	}
	
	@Test(expected=BusinessException.class)
	public final void testShouldThrowAnExceptionWhenUserAlreadyExists() {
		UserDTO createUserDTO = UserMock.createUserDTO();
		
		when(userRepository.countByEmailOrLogin(createUserDTO.getEmail(), createUserDTO.getLogin(), 0)).thenReturn(1);
		
		userService.save(createUserDTO);
	}

	@Test
	public final void shouldUpdateUser() {
		int userId = 1;
		UserDTO createUserDTO = UserMock.createUserDTO();
		createUserDTO.setId(userId);

		when(userRepository.findById(userId)).thenReturn(Optional.of(UserMock.createEntity()));

		when(encoder.encode(createUserDTO.getPassword())).thenReturn("encoded-password");
		when(userRepository.save(any(UserTask.class))).thenReturn(UserMock.createEntity());
		
		UserDTO userDTO = userService.update(1, createUserDTO);
		
		assertNotNull(userDTO);
		assertNotNull(userDTO.getId());
	}
	
	@Test(expected = BusinessException.class)
	public final void shouldThrowAnExceptionWhenCurrentUserNotInDB() {
		int userId = 1;
		UserDTO createUserDTO = UserMock.createUserDTO();
		createUserDTO.setId(userId);

		userService.update(1, createUserDTO);
	}

	@Test(expected = BusinessException.class)
	public final void shouldThrowAnExceptionOnUpdateWhenLoginOrEmailExistsToOtherUser() { 
		int userId = 1;
		UserDTO createUserDTO = UserMock.createUserDTO();
		createUserDTO.setId(userId);

		when(userRepository.findById(userId)).thenReturn(Optional.of(UserMock.createEntity()));
		when(userRepository.countByEmailOrLogin(createUserDTO.getEmail(), createUserDTO.getLogin(), userId)).thenReturn(1);
		
		userService.update(1, createUserDTO);
		
	}
	@Test
	public final void shouldFindUserById() {

		int userId = 1;
		when(userRepository.findById(userId)).thenReturn(Optional.of(UserMock.createEntity()));
		
		UserDTO userDTO = userService.findUserById(userId);
		
		assertNotNull(userDTO);
		assertNotNull(userDTO.getId());
	}
	
	@Test(expected = BusinessException.class)
	public final void shouldThrowAnExceptionWhenUserNotFound() {
		int userId = 1;
		userService.findUserById(userId);
	}

}
