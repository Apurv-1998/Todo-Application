package com.fullstack.todo.application.service;

import java.util.List;

import com.fullstack.todo.application.dto.CreateActivityDto;
import com.fullstack.todo.application.entity.ActivityEntity;

public interface ActivityService {

	ActivityEntity createNewActivity(String todoId, CreateActivityDto activityDto);

	List<ActivityEntity> getAllActivitiesForATodo(String todoId);

	ActivityEntity getActivityDetails(String activityId);

}
