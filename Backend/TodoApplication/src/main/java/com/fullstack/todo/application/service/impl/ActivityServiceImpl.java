package com.fullstack.todo.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.todo.application.dto.CreateActivityDto;
import com.fullstack.todo.application.entity.ActivityEntity;
import com.fullstack.todo.application.entity.TodoEntity;
import com.fullstack.todo.application.entity.UserEntity;
import com.fullstack.todo.application.exception.SpringTodoException;
import com.fullstack.todo.application.repository.ActivityRepository;
import com.fullstack.todo.application.service.ActivityService;
import com.fullstack.todo.application.service.TodoService;
import com.fullstack.todo.application.service.UserService;
import com.fullstack.todo.application.shared.Utils;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	TodoService todoService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	Utils utils;
	
	

	@Override
	public ActivityEntity createNewActivity(String todoId, CreateActivityDto activityDto) {
		
		TodoEntity todo = todoService.getTodo(todoId);
		
		if(todo == null)
			throw new SpringTodoException("Invalid Todo id");
		
		
		//Getting user from todo
		
		UserEntity user = todo.getUserDetails();
		
		
		//Setting up the child
		
		ActivityEntity activity = mapper.map(activityDto,ActivityEntity.class);
		
		activity.setActivityId(utils.generateUserId(5));
		activity.setTodoDetails(todo);
		
		ActivityEntity savedActivity = activityRepository.save(activity);
		
		
		//Saving the activity to the todo
		
		TodoEntity savedTodo = todoService.savedActivity(todo,savedActivity);
		
		
		//Saving the todo to the user
		
		userService.updateUserTodo(user,savedTodo);
		
		//Returning the response
		
		return savedActivity;
		
		
		
	}



	@Override
	public List<ActivityEntity> getAllActivitiesForATodo(String todoId) {
		
		TodoEntity todo = todoService.getTodo(todoId);
		
		if(todo == null)
			throw new SpringTodoException("Invalid Todo Id");
		
		if(todo.getActivityDetails() == null)
			return new ArrayList<>();
		
		return todo.getActivityDetails();
		
	}



	@Override
	public ActivityEntity getActivityDetails(String activityId) {
		
		ActivityEntity activity = activityRepository.findActivityByActivityId(activityId);
		
		
		if(activity == null)
			throw new SpringTodoException("Invalid Activity Id");
		
		
		return activity;
		
		
	}

}
