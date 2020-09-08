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

import com.fullstack.todo.application.dto.CreateActivityDto;
import com.fullstack.todo.application.entity.ActivityEntity;
import com.fullstack.todo.application.model.request.CreateActivityRequestDetailsModel;
import com.fullstack.todo.application.model.response.ActivityRest;
import com.fullstack.todo.application.service.ActivityService;
import com.fullstack.todo.application.service.TodoService;
import com.fullstack.todo.application.shared.Utils;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	ActivityService activityService;
	
	@Autowired
	TodoService todoService;
	
	@Autowired
	Utils utils;
	
	@PostMapping(path = "/{todoId}/createNewActivity",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ActivityRest> CreateActivities(@PathVariable String todoId,@RequestBody CreateActivityRequestDetailsModel createActivityRequestDetailsModel) {
		
		CreateActivityDto activityDto = mapper.map(createActivityRequestDetailsModel,CreateActivityDto.class);
		
		
		ActivityEntity activity = activityService.createNewActivity(todoId,activityDto);
		
		
		ActivityRest response = mapper.map(activity,ActivityRest.class);
		
		
		response.setDuration(utils.setCreationDuration(activity));
		response.setTodoName(todoService.getTodoName(todoId));
		
		
		Link todoLink = WebMvcLinkBuilder.linkTo(TodoController.class).slash(activity.getTodoDetails().getTodoId()).withRel("Todo Details");
		Link userLink = WebMvcLinkBuilder.linkTo(UserController.class).slash(activity.getTodoDetails().getUserDetails().getUserId()).withRel("User Details");
		
		response.add(todoLink);
		response.add(userLink);
		
		
		return new ResponseEntity<ActivityRest>(response,HttpStatus.CREATED);
		
	}
	
	
	@GetMapping(path = "/{todoId}/getActivities",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ActivityRest>> getActivities(@PathVariable String todoId) {
		
		List<ActivityEntity> activities = activityService.getAllActivitiesForATodo(todoId);
		
		List<ActivityRest> response = new ArrayList<>();
		
		for(ActivityEntity activity: activities) {
			ActivityRest rest = mapper.map(activity,ActivityRest.class);
			
			Link todoLink = WebMvcLinkBuilder.linkTo(TodoController.class).slash(activity.getTodoDetails().getTodoId()).withRel("Todo Details");
			Link userLink = WebMvcLinkBuilder.linkTo(UserController.class).slash(activity.getTodoDetails().getUserDetails().getUserId()).withRel("User Details");
			
			rest.setTodoName(todoService.getTodoName(todoId));
			
			rest.add(todoLink);
			rest.add(userLink);
			
			response.add(rest);
			
			
		}
		
		
		return new ResponseEntity<List<ActivityRest>>(response,HttpStatus.OK);
		
	}
	
	
	
	@GetMapping(path = "/{activityId}/getDetails",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ActivityRest> getDetails(@PathVariable String activityId) {
		
			ActivityEntity activity = activityService.getActivityDetails(activityId);
			
			
			ActivityRest response = mapper.map(activity,ActivityRest.class);
			
			response.setTodoName(activity.getTodoDetails().getTodoName());
			
			Link todoLink = WebMvcLinkBuilder.linkTo(TodoController.class).slash(activity.getTodoDetails().getTodoId()).withRel("Todo Details");
			Link userLink = WebMvcLinkBuilder.linkTo(UserController.class).slash(activity.getTodoDetails().getUserDetails().getUserId()).withRel("User Details");
			
			response.add(todoLink);
			response.add(userLink);
			
			response.setDuration(utils.setCreationDuration(activity));
			
			
			return new ResponseEntity<ActivityRest>(response,HttpStatus.OK);
			
		
	}

}
