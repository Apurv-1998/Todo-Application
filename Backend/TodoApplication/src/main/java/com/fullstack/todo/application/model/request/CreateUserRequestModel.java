package com.fullstack.todo.application.model.request;

import java.util.Date;

import lombok.Data;

@Data
public class CreateUserRequestModel {
	
	private String firstName;
	private String lastName;
	private Date dob;
	private String email;
	private String password;

}
