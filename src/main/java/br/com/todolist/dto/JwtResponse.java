package br.com.todolist.dto;

public class JwtResponse {
	private final String jwttoken;
	private UserDTO user;

	public JwtResponse(String jwttoken, UserDTO user) {
		this.jwttoken = jwttoken;
		this.user = user;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public UserDTO getUser() {
		return user;
	}
}
