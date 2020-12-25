package br.com.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.todolist.entity.UserTask;

/**
 * Repository interface for User
 * @author Lucas Feitozas
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserTask, Integer> {

	/**
	 * This method returns a count by email or login
	 * @param email
	 * @param Login
	 * @return
	 */
	@Query(value = "select count(*) from UserTask where "
			+ " (email = :email"
			+ " or login = :login)"
			+ " and id <> :id", nativeQuery = false)
	Integer countByEmailOrLogin(String email, String login, Integer id);

	UserTask findByLogin(String login);
	
}
