package com.fullstack.todo.application.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginDto implements Serializable {
	
	
	private static final long serialVersionUID = 772115391914823994L;
	
	private String userName;
	private String password;

}
