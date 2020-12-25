package br.com.todolist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.todolist.entity.Item;

/**
 * Repository interface for Item
 * @author Lucas Feitozas
 *
 */
public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findAllByTaskIdOrderByTitle(Integer taskId);

	Optional<Item> findByIdAndTaskId(Integer itemId, Integer taskId);

	Optional<Item> findByItemChildId(Integer itemId);

}
