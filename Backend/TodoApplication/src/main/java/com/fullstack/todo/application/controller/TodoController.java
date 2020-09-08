package com.fullstack.todo.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.todo.application.dto.CreateTodoDto;
import com.fullstack.todo.application.entity.TodoEntity;
import com.fullstack.todo.application.model.request.CreateTodoRequestModel;
import com.fullstack.todo.application.model.response.TodoRest;
import com.fullstack.todo.application.service.TodoService;
import com.fullstack.todo.application.shared.Utils;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	TodoService todoService;
	
	@Autowired
	Utils utils;
	
	
	@PostMapping(path = "/{userId}/createTodo",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public TodoRest createTodo(@PathVariable String userId,@RequestBody CreateTodoRequestModel createTodoRequestModel) {
		
		CreateTodoDto todoDto = mapper.map(createTodoRequestModel,CreateTodoDto.class);
		
		TodoEntity todo = todoService.createNewTodo(userId,todoDto);
		
		TodoRest response = mapper.map(todo,TodoRest.class);
		
		response.setDuration(utils.setCreationDuration(todo));
		
		Link link = WebMvcLinkBuilder.linkTo(UserController.class).slash(userId).withRel("User Details");
		
		response.add(link);
		
		return response;
		
	}
	
	
	@GetMapping(path = "/{userId}/showTodos",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<TodoRest>> getAllTodos(@PathVariable String userId) {
		
		List<TodoEntity> todos = todoService.getAllTodos(userId);
		
		List<TodoRest> response = new ArrayList<>();
		
		for(TodoEntity todo: todos) {
			TodoRest rest = mapper.map(todo,TodoRest.class);
			
			Link link = WebMvcLinkBuilder.linkTo(UserController.class).slash(userId).withRel("User Details");
			
			rest.add(link);
			
			response.add(rest);
			
		}
		
		
		return new ResponseEntity<List<TodoRest>>(response,HttpStatus.OK);
		
	}

}
