package com.fullstack.todo.application.model.response;

import org.springframework.hateoas.RepresentationModel;

public class TodoRest extends RepresentationModel<TodoRest> {

	private String todoId;
	private String todoName;
	private String duration;

	public String getTodoId() {
		return todoId;
	}

	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}

	public String getTodoName() {
		return todoName;
	}

	public void setTodoName(String todoName) {
		this.todoName = todoName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
