package br.com.todolist.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 9194684574487580326L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer id;
	
	@NotNull(message = "Favor informar o nome do usuario")
	private String name;
	
	@NotNull
	@Email(message = "Formato de e-mail inválido")
	private String email;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@NotNull(message = "Favor informar login.")
	private String login;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@NotNull(message = "Favor informar password")
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", login=" + login + "]";
	}

}
