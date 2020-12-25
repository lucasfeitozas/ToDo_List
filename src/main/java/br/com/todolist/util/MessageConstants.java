package br.com.todolist.util;

public class MessageConstants {
	
	// GENERAL
	public static final String SYSTEM_ERROR=  "An error occurred during this operation. Contact the System Admin";
	public static final String UNAUTHORIZED = "Unauthorized";
	
	// USER
	public static final String USER_NOT_FOUND = "User not found";
	public static final String USER_LOGIN_OR_EMAIL_ALREADY_EXISTS = "An User with this email/login already exists";

	// TASK (LIST)
	public static final String LIST_ALREADY_EXISTS = "List already exists";
	public static final String LIST_NOT_FOUND = "List already exists";
	
	// ITEM
	public static final String ITEM_NOT_FOUND = "Item not found";
	public static final String ITEM_USER_CANNOT_BE_EQUALS_LIST_USER = "The item owner cannot be the same as the list owner";

}
