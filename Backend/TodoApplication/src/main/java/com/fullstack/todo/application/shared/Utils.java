package com.fullstack.todo.application.shared;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fullstack.todo.application.entity.ActivityEntity;
import com.fullstack.todo.application.entity.TodoEntity;
import com.github.marlonlom.utilities.timeago.TimeAgo;

@Component
public class Utils {
	
	
	private final Random RANDOM = new SecureRandom();
	private final String USER_SALT = "akjfhqeirhfoiq34uyr982137yu98407ur901u_hqiuehfolqe89ry829hyuhHH98G98PYHPQ8YHF8GHP3";
	
	
	
	
	

	public String generateUserId(int length) {
		
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			sb.append(USER_SALT.charAt(RANDOM.nextInt(USER_SALT.length())));
		}

		return new String(sb);
		
	}
	
	public String generateRegPassword(int length) {
		
		
		String redpas = UUID.randomUUID().toString();

		return redpas;
	}

	public String setCreationDuration(TodoEntity todo) {
		
		
		return TimeAgo.using(todo.getCreationDate().toInstant().toEpochMilli());
		
	}

	public String setCreationDuration(ActivityEntity activity) {
		
		
		return TimeAgo.using(activity.getCreationTime().toInstant().toEpochMilli());
		
	}

	public String[] extractNameFromUserName(String username) {
		
		return username.trim().split(" ");
		
	}

}
