package com.fullstack.todo.application.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fullstack.todo.application.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findUserByUserId(String userId);
	
	
	@Query(value = "SELECT * FROM users u WHERE u.first_name = ?1 AND u.last_name = ?2",nativeQuery = true)
	UserEntity findUserFromFirstNameAndLastName(String firstName, String lastName);

}
