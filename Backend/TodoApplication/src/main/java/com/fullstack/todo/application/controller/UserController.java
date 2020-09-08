package com.fullstack.todo.application.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.todo.application.dto.CreateUserDto;
import com.fullstack.todo.application.dto.LoginDto;
import com.fullstack.todo.application.entity.UserEntity;
import com.fullstack.todo.application.model.request.CreateUserRequestModel;
import com.fullstack.todo.application.model.request.UserLoginmodel;
import com.fullstack.todo.application.model.response.UserRest;
import com.fullstack.todo.application.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	UserService userService;
	
	
	/*--------------------- POST METHODS --------------------------- */
	
	@PostMapping(path = "/createUser",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public UserRest createUser(@RequestBody CreateUserRequestModel createUserRequestModel) {
		
		CreateUserDto userDto = mapper.map(createUserRequestModel,CreateUserDto.class);
		
		UserEntity user = userService.createNewUser(userDto);
		
		
		UserRest response = mapper.map(user,UserRest.class);
		
		response.setUserName(user.getFirstName()+" "+user.getLastName());
		
		return response;
	}
	
	
	@PostMapping(path = "/login",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserRest> login(@RequestBody UserLoginmodel userLoginModel) {
		
		LoginDto loginDto = mapper.map(userLoginModel,LoginDto.class);
		
		UserRest response = userService.loginUser(loginDto);
		
		return new ResponseEntity<UserRest>(response,HttpStatus.OK);
		
	}
	
	
	/*-------------------- GET METHODS --------------------------------*/
	
	@GetMapping(path = "/accountVerification/{tokenId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> verifyUser(@PathVariable String tokenId) {
		
		boolean isVerified = userService.verifyAccount(tokenId);
		
		String message = "";
		
		if(isVerified)
			message = "Account Verification Successful";
		else
			message = "Cannot Verify Account";
		
		return new ResponseEntity<String>(message,HttpStatus.OK);
		
		
	}
	
	

}
