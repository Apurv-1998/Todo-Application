package com.fullstack.todo.application.model.request;

import java.util.Date;

import lombok.Data;

@Data
public class CreateActivityRequestDetailsModel {
	
	private String activityName;
	private String text;
	private Date startDate;
	private Date endDate;
	
}
