package br.com.todolist.service.exception;

/**
 * @author Lucas Feitozas
 *
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1706475858328827646L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	
}
