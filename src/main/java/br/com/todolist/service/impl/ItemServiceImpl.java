package br.com.todolist.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.todolist.converters.ItemConverter;
import br.com.todolist.dto.ItemDetailResponseDTO;
import br.com.todolist.dto.ItemRequestDTO;
import br.com.todolist.dto.ItemResponseDTO;
import br.com.todolist.entity.Item;
import br.com.todolist.entity.Task;
import br.com.todolist.entity.UserTask;
import br.com.todolist.repository.ItemRepository;
import br.com.todolist.repository.TaskRepository;
import br.com.todolist.repository.UserRepository;
import br.com.todolist.service.ItemService;
import br.com.todolist.service.exception.BusinessException;
import br.com.todolist.util.MessageConstants;

/**
 * 
 * Service for Item
 * 
 * 
 * @author Lucas Feitozas
 *
 */
@Service
public class ItemServiceImpl implements ItemService {


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public ItemResponseDTO findAllByListaId(Integer listaId) {

		List<Item> items = itemRepository.findAllByTaskIdOrderByTitle(listaId);

		return ItemConverter.convertToDTO(items);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ItemDetailResponseDTO saveItem(Integer taskId, ItemRequestDTO dto) throws BusinessException {

		Task task = findTask(taskId);

		validateItemOwner(dto, task);

		UserTask userItem = findUserItem(dto);

		Item item = new Item();
		item.setTitle(dto.getTitle());
		item.setDescription(dto.getDescription());
		item.setTask(task);
		item.setUser(userItem);

		return ItemConverter.convertToItemDetailResponseDTO(itemRepository.save(item));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ItemDetailResponseDTO updateItem(Integer listaId, Integer itemId, ItemRequestDTO itemRequestDTO)
			throws BusinessException {

		Task lista = findTask(listaId);
		UserTask userOptional = findUserItem(itemRequestDTO);

		Item item = findCurrentItem(itemId);
		item.setTitle(itemRequestDTO.getTitle());
		item.setDescription(itemRequestDTO.getDescription());
		item.setTask(lista);
		item.setUser(userOptional);

		validateItemOwner(itemRequestDTO, lista);

		return ItemConverter.convertToItemDetailResponseDTO(itemRepository.save(item));
	}

	private void validateItemOwner(ItemRequestDTO itemRequestDTO, Task lista) {
		if (lista.getUser().getId().equals(itemRequestDTO.getUserId())) {
			throw new BusinessException(MessageConstants.ITEM_USER_CANNOT_BE_EQUALS_LIST_USER);
		}
	}

	private Item findCurrentItem(Integer itemId) {
		Optional<Item> itemOptional = itemRepository.findById(itemId);
		if (!itemOptional.isPresent()) {
			throw new BusinessException(MessageConstants.ITEM_NOT_FOUND);
		}
		return itemOptional.get();
	}

	private UserTask findUserItem(ItemRequestDTO dto) {
		Optional<UserTask> userOptional = userRepository.findById(dto.getUserId());
		if (!userOptional.isPresent()) {
			throw new BusinessException(MessageConstants.USER_NOT_FOUND);
		}
		return userOptional.get();
	}

	private Task findTask(Integer listaId) {
		Optional<Task> optionalLista = taskRepository.findById(listaId);
		if (!optionalLista.isPresent()) {
			throw new BusinessException(MessageConstants.LIST_NOT_FOUND);
		}
		return optionalLista.get();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteItem(Integer listaId, Integer itemId) {
		Optional<Item> optionalItem = itemRepository.findByIdAndTaskId(itemId, listaId);

		if (!optionalItem.isPresent()) {
			throw new BusinessException(MessageConstants.ITEM_NOT_FOUND);
		}
		Optional<Item> childItem = itemRepository.findByItemChildId(itemId);
		if (childItem.isPresent()) {
			itemRepository.delete(childItem.get());
		}
		itemRepository.delete(optionalItem.get());
	}

}
