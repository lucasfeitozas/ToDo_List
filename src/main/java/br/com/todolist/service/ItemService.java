package br.com.todolist.service;

import br.com.todolist.dto.ItemDetailResponseDTO;
import br.com.todolist.dto.ItemRequestDTO;
import br.com.todolist.dto.ItemResponseDTO;
import br.com.todolist.service.exception.BusinessException;

/**
 * Service interface for Item
 * @author Lucas Feitozas
 *
 */
public interface ItemService {

	ItemResponseDTO findAllByListaId(Integer listaId);

	ItemDetailResponseDTO saveItem(Integer listaId, ItemRequestDTO dto) throws BusinessException;

	ItemDetailResponseDTO updateItem(Integer listaId, Integer itemId, ItemRequestDTO itemRequestDTO) throws BusinessException;

	void deleteItem(Integer listaId, Integer itemId);

}
