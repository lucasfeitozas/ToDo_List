package br.com.todolist.controller.v1;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.todolist.dto.UserDTO;
import br.com.todolist.service.UserService;
import br.com.todolist.service.exception.BusinessException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user) throws BusinessException {
		UserDTO userDTO = userService.save(user);
		return ResponseEntity.ok(userDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO user) throws BusinessException  {
		UserDTO userDTO = userService.update(id, user);
		return ResponseEntity.ok(userDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findUser(@PathVariable Integer id) throws BusinessException {
		return ResponseEntity.ok(userService.findUserById(id));
	}

}
