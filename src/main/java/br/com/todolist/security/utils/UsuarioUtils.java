package br.com.todolist.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.todolist.entity.UserTask;

public class UsuarioUtils {
	
	private UsuarioUtils() {
		super();
	}

	public static UserTask getAuthenticatedUser() {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
		 return (UserTask) auth.getPrincipal();
	}
}
