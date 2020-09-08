package com.fullstack.todo.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fullstack.todo.application.entity.ActivityEntity;

@Repository
public interface ActivityRepository extends CrudRepository<ActivityEntity, Long> {

	ActivityEntity findActivityByActivityId(String activityId);

}
