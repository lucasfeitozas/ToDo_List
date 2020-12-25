package br.com.todolist.controller.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.todolist.dto.CreateTaskRequestDTO;
import br.com.todolist.dto.CreateTaskResponseDTO;
import br.com.todolist.dto.ItemDetailResponseDTO;
import br.com.todolist.dto.ItemRequestDTO;
import br.com.todolist.dto.ItemResponseDTO;
import br.com.todolist.dto.TaskResponseDTO;
import br.com.todolist.service.ItemService;
import br.com.todolist.service.TaskService;
import br.com.todolist.service.exception.BusinessException;

/**
 * 
 * Class Controller to Lista
 * 
 * @author Lucas Feitozas
 *
 */
@RestController
@RequestMapping("/api/v1/lists")
public class ListsController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private ItemService itemService;

	// ----------------------------------------
	// Tasks (list)
	// -----------------------------------------
	@PostMapping(value = "")
	public ResponseEntity<CreateTaskResponseDTO> saveLista(@Valid @RequestBody CreateTaskRequestDTO dto) throws BusinessException {
		CreateTaskResponseDTO listaResponse = taskService.save(dto);
		return ResponseEntity.ok(listaResponse);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		taskService.deleteTask(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<TaskResponseDTO> findListaById(@PathVariable Integer id) throws BusinessException {
		TaskResponseDTO taskResponseDTO = taskService.findById(id);

		return ResponseEntity.ok(taskResponseDTO);
	}

	@GetMapping(value = "")
	public ResponseEntity<List<TaskResponseDTO>> findAll() throws BusinessException {
		List<TaskResponseDTO> listaResponseDTO = taskService.findAll();

		return ResponseEntity.ok(listaResponseDTO);
	}

	@GetMapping(value = "/user/{id}")
	public ResponseEntity<List<TaskResponseDTO>> findAllByUserId(@PathVariable Integer id) throws BusinessException {
		List<TaskResponseDTO> listaResponseDTO = taskService.findAllByUserId(id);

		return ResponseEntity.ok(listaResponseDTO);
	}

	// ----------------------------------------
	// ITEM
	// -----------------------------------------

	@GetMapping(value = "/{listaId}/items")
	public ResponseEntity<ItemResponseDTO> findListaItens(@PathVariable Integer listaId) throws BusinessException {

		ItemResponseDTO listItem = itemService.findAllByListaId(listaId);

		return ResponseEntity.ok(listItem);
	}

	@PostMapping(value = "/{listaId}/items")
	public ResponseEntity<Map<String, ItemDetailResponseDTO>> saveItem(@PathVariable Integer listaId,
			@Valid @RequestBody ItemRequestDTO dto) {

		ItemDetailResponseDTO item = itemService.saveItem(listaId, dto);

		Map<String, ItemDetailResponseDTO> map = formatItemResponse(item);

		return ResponseEntity.ok(map);
	}

	@PutMapping(value = "/{listaId}/items/{itemId}")
	public ResponseEntity<Map<String, ItemDetailResponseDTO>> updateItem(@PathVariable Integer listaId, @PathVariable Integer itemId,
			@Valid @RequestBody ItemRequestDTO itemRequestDTO) {
		
		ItemDetailResponseDTO item = itemService.updateItem(listaId, itemId, itemRequestDTO);
		
		Map<String, ItemDetailResponseDTO> map = formatItemResponse(item);

		return ResponseEntity.ok(map);
	}

	private Map<String, ItemDetailResponseDTO> formatItemResponse(ItemDetailResponseDTO item) {
		Map<String, ItemDetailResponseDTO> map = new HashMap<>();
		map.put("item", item);
		return map;
	}

	@DeleteMapping(value = "/{listaId}/items/{itemId}")
	public ResponseEntity<Void> deleteItem(@PathVariable Integer listaId, @PathVariable Integer itemId)
			throws BusinessException {

		itemService.deleteItem(listaId, itemId);
		
		return ResponseEntity.noContent().build();
	}

}
