package com.fullstack.todo.application.dto;

import java.io.Serializable;

import lombok.Data;
import java.util.Date;

@Data
public class CreateActivityDto implements Serializable {

	private static final long serialVersionUID = -6394963985360038963L;
	
	private String activityId;
	private String activityName;
	private String text;
	private Date startDate;
	private Date endDate;

}
