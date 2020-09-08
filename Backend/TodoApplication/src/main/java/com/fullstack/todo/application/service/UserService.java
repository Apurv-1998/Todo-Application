package com.fullstack.todo.application.service;

import com.fullstack.todo.application.dto.CreateUserDto;
import com.fullstack.todo.application.dto.LoginDto;
import com.fullstack.todo.application.entity.TodoEntity;
import com.fullstack.todo.application.entity.UserEntity;
import com.fullstack.todo.application.model.response.UserRest;

public interface UserService {

	UserEntity createNewUser(CreateUserDto userDto);

	boolean verifyAccount(String tokenId);

	UserEntity finduser(String userId);

	void saveTodo(UserEntity user,TodoEntity savedTodo);

	void updateUserTodo(UserEntity user, TodoEntity savedTodo);

	UserRest loginUser(LoginDto loginDto);

}
