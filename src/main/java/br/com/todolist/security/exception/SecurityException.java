package br.com.todolist.security.exception;

/**
 * Exception for Security package
 * @author lucasfeitozas
 *
 */
public class SecurityException extends Exception{
	private static final long serialVersionUID = 6505459021406935904L;

	public SecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	
}
