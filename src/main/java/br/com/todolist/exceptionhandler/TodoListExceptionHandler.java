package br.com.todolist.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.todolist.service.exception.BusinessException;

@ControllerAdvice
public class TodoListExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(SecurityException.class)
	public ResponseEntity<Object> handleSecurityException(SecurityException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, createErrorResponse(bodyOfResponse), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, createErrorResponse(bodyOfResponse), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleBusinessException(Exception ex, WebRequest request) {
		String bodyOfResponse = "An error occurred during call server";
		logger.error(ex.getMessage());
		return handleExceptionInternal(ex, createErrorResponse(bodyOfResponse), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Internal Error Occurred";
		return handleExceptionInternal(ex, createErrorResponse(bodyOfResponse), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	private Map<String, Object> createErrorResponse(Object error) {
		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put("error", error);
		return errorMap;
	}
	
	
}
