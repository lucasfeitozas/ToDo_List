package br.com.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.todolist.entity.Task;

/**
 * 
 * Repository Interface for Lista
 * @author Lucas Feitozas
 *
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	Integer countByTitle(String title);

	List<Task> findAllByOrderByTitle();

	List<Task> findByUserId(Integer userId);

}
