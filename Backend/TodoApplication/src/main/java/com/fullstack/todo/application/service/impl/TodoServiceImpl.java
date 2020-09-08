package com.fullstack.todo.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.todo.application.dto.CreateTodoDto;
import com.fullstack.todo.application.entity.ActivityEntity;
import com.fullstack.todo.application.entity.TodoEntity;
import com.fullstack.todo.application.entity.UserEntity;
import com.fullstack.todo.application.exception.SpringTodoException;
import com.fullstack.todo.application.repository.TodoRepository;
import com.fullstack.todo.application.service.TodoService;
import com.fullstack.todo.application.service.UserService;
import com.fullstack.todo.application.shared.Utils;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	TodoRepository todoRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	Utils utils;

	@Override
	public TodoEntity createNewTodo(String userId, CreateTodoDto todoDto) {
		
		UserEntity user = userService.finduser(userId);
		
		if(user == null)
			throw new SpringTodoException("Iv=nvalid UserId");
		
		TodoEntity todo = mapper.map(todoDto,TodoEntity.class);
		
		todo.setTodoId(utils.generateUserId(7));
		todo.setUserDetails(user);
		
		TodoEntity savedTodo = todoRepository.save(todo);
		
		userService.saveTodo(user,savedTodo);
		
		return savedTodo;
		
	}

	@Override
	public TodoEntity getTodo(String todoId) {
		
		
		return todoRepository.findTodoByTodoId(todoId);
		
	}

	@Override
	public TodoEntity savedActivity(TodoEntity todo, ActivityEntity savedActivity) {
		
		
		if(todo.getActivityDetails() == null) {
			List<ActivityEntity> list = new ArrayList<ActivityEntity>();
			list.add(savedActivity);
			todo.setActivityDetails(list);
		}
		else {
			List<ActivityEntity> list = todo.getActivityDetails();
			list.add(savedActivity);
			todo.setActivityDetails(list);
		}
		
		return todoRepository.save(todo);
		
	}

	@Override
	public List<TodoEntity> getAllTodos(String userId) {
		
		UserEntity user = userService.finduser(userId);
		
		if(user == null)
			throw new SpringTodoException("Given User Doesnot Exists");
		
		if(user.getTodoDetails() == null)
			return new ArrayList<>();
		
		return user.getTodoDetails();
		
	}

	@Override
	public String getTodoName(String todoId) {

		return todoRepository.findTodoByTodoId(todoId).getTodoName();
		
	}

}
