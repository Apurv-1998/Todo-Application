package com.fullstack.todo.application.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreateTodoDto implements Serializable {

	private static final long serialVersionUID = 7094396859241529849L;
	
	private String todoId;
	private String todoName;

}
