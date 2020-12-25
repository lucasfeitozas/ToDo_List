package br.com.todolist.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.todolist.dto.ItemDetailResponseDTO;
import br.com.todolist.dto.ItemRequestDTO;
import br.com.todolist.dto.ItemResponseDTO;
import br.com.todolist.entity.Item;
import br.com.todolist.entity.UserTask;
import br.com.todolist.mock.ItemMock;
import br.com.todolist.mock.TaskMock;
import br.com.todolist.mock.UserMock;
import br.com.todolist.repository.ItemRepository;
import br.com.todolist.repository.TaskRepository;
import br.com.todolist.repository.UserRepository;
import br.com.todolist.service.exception.BusinessException;

/**
 * @author lucasfeitozas
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {

	@InjectMocks
	private ItemServiceImpl service;

	@Mock
	private UserRepository userRepository;

	@Mock
	private TaskRepository taskRepository;

	@Mock
	private ItemRepository itemRepository;

	@Test
	public final void testFindAllByListaId() {
		int listaId = 1;
		when(itemRepository.findAllByTaskIdOrderByTitle(listaId)).thenReturn(ItemMock.createEntityList());

		ItemResponseDTO findAllByListaId = service.findAllByListaId(listaId);

		assertNotNull(findAllByListaId);
	}

	@Test
	public final void shouldSaveItemWhenItemRequestDTOIsOK() {
		int taskId = 1;
		ItemRequestDTO requestDTO = ItemMock.createItemDetailResponseDTO();
		UserTask userTask = UserMock.createEntity();
		userTask.setId(requestDTO.getUserId());

		when(taskRepository.findById(taskId)).thenReturn(Optional.of(TaskMock.createEntity()));
		when(userRepository.findById(requestDTO.getUserId())).thenReturn(Optional.of(userTask));
		when(itemRepository.save(any(Item.class))).thenReturn(ItemMock.createEntity());

		ItemDetailResponseDTO response = service.saveItem(taskId, requestDTO);

		assertNotNull(response);
	}

	@Test(expected = BusinessException.class)
	public final void shouldThrowAnExceptionWhenTaskNotFound() {
		int taskId = 1;

		service.saveItem(taskId, ItemMock.createItemDetailResponseDTO());
	}

	@Test(expected = BusinessException.class)
	public final void shouldThrowAnExceptionWhenListUserIsEqualsItemUser() {
		int taskId = 1;
		ItemRequestDTO requestDTO = ItemMock.createItemDetailResponseDTO();
		requestDTO.setUserId(1); // userId same than task.user

		when(taskRepository.findById(taskId)).thenReturn(Optional.of(TaskMock.createEntity()));
		
		service.saveItem(taskId, requestDTO);
	}

	@Test(expected = BusinessException.class)
	public final void shouldThrowAnExceptionWhenUserNotFound() {
		int taskId = 1;
		ItemRequestDTO requestDTO = ItemMock.createItemDetailResponseDTO();
		requestDTO.setUserId(1); // userId same than task.user

		when(taskRepository.findById(taskId)).thenReturn(Optional.of(TaskMock.createEntity()));

		service.saveItem(taskId, requestDTO);
	}

	@Test
	public final void testUpdateItem() {
		int taskId = 1;
		ItemRequestDTO requestDTO = ItemMock.createItemDetailResponseDTO();
		UserTask userTask = UserMock.createEntity();
		userTask.setId(requestDTO.getUserId());

		when(taskRepository.findById(taskId)).thenReturn(Optional.of(TaskMock.createEntity()));
		when(userRepository.findById(requestDTO.getUserId())).thenReturn(Optional.of(userTask));
		when(itemRepository.findById(1)).thenReturn(Optional.of(ItemMock.createEntity()));
		when(itemRepository.save(any(Item.class))).thenReturn(ItemMock.createEntity());

		ItemDetailResponseDTO response = service.updateItem(taskId, 1, requestDTO);

		assertNotNull(response);
	}

	@Test(expected = BusinessException.class)
	public final void testUpdateShouldThrowAnExceptionWhenUserNotFound() {

		int taskId = 1;
		ItemRequestDTO requestDTO = ItemMock.createItemDetailResponseDTO();
		UserTask userTask = UserMock.createEntity();
		userTask.setId(requestDTO.getUserId());

		when(taskRepository.findById(taskId)).thenReturn(Optional.of(TaskMock.createEntity()));
		when(userRepository.findById(requestDTO.getUserId())).thenReturn(Optional.empty());

		service.updateItem(taskId, 1, requestDTO);

	}

	@Test(expected = BusinessException.class)
	public final void testUpdateShouldThrowAnExceptionWhenCurrentItemNotFound() {

		int taskId = 1;
		ItemRequestDTO requestDTO = ItemMock.createItemDetailResponseDTO();
		UserTask userTask = UserMock.createEntity();
		userTask.setId(requestDTO.getUserId());

		when(taskRepository.findById(taskId)).thenReturn(Optional.of(TaskMock.createEntity()));
		when(userRepository.findById(requestDTO.getUserId())).thenReturn(Optional.of(userTask));
		when(itemRepository.findById(1)).thenReturn(Optional.empty());

		service.updateItem(taskId, 1, requestDTO);

	}

	@Test
	public final void testDeleteItemWithoutItemChild() {

		when(itemRepository.findByIdAndTaskId(1, 1)).thenReturn(Optional.of(ItemMock.createEntity()));

		service.deleteItem(1, 1);

		assertTrue(1 > 0);
	}
	
	@Test
	public final void testDeleteItemWithItemChild() {

		when(itemRepository.findByIdAndTaskId(1, 1)).thenReturn(Optional.of(ItemMock.createEntity()));
		when(itemRepository.findByItemChildId(1)).thenReturn(Optional.of(ItemMock.createEntity()));
		
		service.deleteItem(1, 1);

		assertTrue(1 > 0);
	}

	@Test(expected = BusinessException.class)
	public final void testDeleteShouldThrowAnExceptionWhenItemNotFound() {
		service.deleteItem(1, 1);
	}
}
