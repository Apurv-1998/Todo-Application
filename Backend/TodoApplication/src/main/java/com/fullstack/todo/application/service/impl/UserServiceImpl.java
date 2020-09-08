package com.fullstack.todo.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.todo.application.dto.CreateUserDto;
import com.fullstack.todo.application.dto.LoginDto;
import com.fullstack.todo.application.entity.TodoEntity;
import com.fullstack.todo.application.entity.UserEntity;
import com.fullstack.todo.application.entity.VerificationTokenEntity;
import com.fullstack.todo.application.exception.SpringTodoException;
import com.fullstack.todo.application.model.response.UserRest;
import com.fullstack.todo.application.repository.UserRepository;
import com.fullstack.todo.application.repository.VerificationTokenRepository;
import com.fullstack.todo.application.service.MailService;
import com.fullstack.todo.application.service.UserService;
import com.fullstack.todo.application.service.VerificationTokenService;
import com.fullstack.todo.application.shared.EmailBody;
import com.fullstack.todo.application.shared.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	VerificationTokenService verificationTokenService;
	
	@Autowired
	VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	MailService mailService;
	
	
	@Override
	public UserEntity createNewUser(CreateUserDto userDto) {
		
		UserEntity user = mapper.map(userDto,UserEntity.class);
		
		user.setUserId(utils.generateUserId(10));
		user.setRegPassword(utils.generateRegPassword(10));
		
		
		UserEntity savedUser = userRepository.save(user);
		
		
		//Generate Token
		
		VerificationTokenEntity token = verificationTokenService.generateToken(savedUser);
		
		
		//Send the mail
		mailService.sendMail(new EmailBody("Please Activate Your Account",savedUser.getEmail(),
				"Thank you for signing to spring todo-app please click on the url to activate your account : \"\r\n"
				+" http://localhost:8080/todo-app/api/users/accountVerification/"+token.getTokenId()));
		
		return savedUser;
	}


	@Override
	public boolean verifyAccount(String tokenId) {
		
		VerificationTokenEntity token = verificationTokenService.getTokenFromTokenId(tokenId);
		
		if(token == null)
			throw new SpringTodoException("Invalid Token");
		
		UserEntity user = token.getUserDetails();
		
		user.setVerified(true);
		
		UserEntity savedUser = userRepository.save(user);
		
		verificationTokenRepository.delete(token);
		
		return savedUser != null;
		
	}


	@Override
	public UserEntity finduser(String userId) {
		
		return userRepository.findUserByUserId(userId);
		
	}


	@Override
	public void saveTodo(UserEntity user,TodoEntity savedTodo) {
		
		if(user.getTodoDetails() == null) {
			List<TodoEntity> todos = new ArrayList<>();
			todos.add(savedTodo);
			user.setTodoDetails(todos);
		}
		else {
			List<TodoEntity> todos = user.getTodoDetails();
			todos.add(savedTodo);
			user.setTodoDetails(todos);
		}
		
		
		userRepository.save(user);
		
		
	}


	@Override
	public void updateUserTodo(UserEntity user, TodoEntity savedTodo) {
		
		List<TodoEntity> todos = user.getTodoDetails();
		
		for(int i=0;i<todos.size();i++) {
			TodoEntity todo = todos.get(i);
			
			if(todo.getTodoId().equals(savedTodo.getTodoId())) {
				todos.set(i, savedTodo);
				break;
			}
		}
		user.setTodoDetails(todos);
		
		userRepository.save(user);
	}


	@Override
	public UserRest loginUser(LoginDto loginDto) {
		UserRest response = new UserRest();
		
		String[] str = utils.extractNameFromUserName(loginDto.getUserName());
		
		if(str.length!=2)
			throw new SpringTodoException("Invalid UserName");
		
		
		UserEntity user = userRepository.findUserFromFirstNameAndLastName(str[0],str[1]);
		
		System.out.println("User -> "+user);
		
		if(user == null)
			throw new SpringTodoException("First Create User, The Given UserName Doesnot Exist");
		
		if(!user.isVerified())
			throw new SpringTodoException("The User is not verified");
		
		user.setLoggedIn(true);
		
		UserEntity savedUser = userRepository.save(user);
		
		response.setUserId(savedUser.getUserId());
		response.setRegPassword(savedUser.getRegPassword());
		response.setUserName(loginDto.getUserName());
		response.setEmail(savedUser.getEmail());
		
		return response;
	}

}
