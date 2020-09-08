package com.fullstack.todo.application.service;

import java.util.List;

import com.fullstack.todo.application.dto.CreateTodoDto;
import com.fullstack.todo.application.entity.ActivityEntity;
import com.fullstack.todo.application.entity.TodoEntity;

public interface TodoService {

	TodoEntity createNewTodo(String userId, CreateTodoDto todoDto);

	TodoEntity getTodo(String todoId);

	TodoEntity savedActivity(TodoEntity todo, ActivityEntity savedActivity);

	List<TodoEntity> getAllTodos(String userId);

	String getTodoName(String todoId);

}
