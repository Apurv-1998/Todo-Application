package com.fullstack.todo.application.model.response;

import lombok.Data;

@Data
public class UserRest {
	
	
	private String userId;
	private String userName;
	private String email;
	private String regPassword;

}
