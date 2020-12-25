package br.com.todolist.mock;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import br.com.todolist.entity.UserTask;

public class AuthenticationMock implements Authentication {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "mock-user";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return "auth-details";
	}

	@Override
	public Object getPrincipal() {
		UserTask entity = UserMock.createEntity();
		entity.setId(1);
		return entity;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
	}

}
