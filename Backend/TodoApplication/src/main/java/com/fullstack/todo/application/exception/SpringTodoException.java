package com.fullstack.todo.application.exception;

public class SpringTodoException extends RuntimeException {

	
	private static final long serialVersionUID = 2758679135468388013L;
	
	public SpringTodoException(String message) {
		super(message);
	}

}
