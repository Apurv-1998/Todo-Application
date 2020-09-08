package com.fullstack.todo.application.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class CreateUserDto implements Serializable {

	
	private static final long serialVersionUID = -2477516996682311118L;
	
	private String userId;
	private String firstName;
	private String lastName;
	private Date dob;
	private String email;
	private String password;
	private String regPassword;
}
