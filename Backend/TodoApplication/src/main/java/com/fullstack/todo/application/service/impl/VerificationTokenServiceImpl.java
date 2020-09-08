package com.fullstack.todo.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.todo.application.entity.UserEntity;
import com.fullstack.todo.application.entity.VerificationTokenEntity;
import com.fullstack.todo.application.repository.VerificationTokenRepository;
import com.fullstack.todo.application.service.VerificationTokenService;
import com.fullstack.todo.application.shared.Utils;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {
	
	@Autowired
	VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	Utils utils;
	
	
	@Override
	public VerificationTokenEntity generateToken(UserEntity savedUser) {
		
		VerificationTokenEntity returnValue = new VerificationTokenEntity();
		
		returnValue.setTokenId(utils.generateRegPassword(10));
		returnValue.setUserDetails(savedUser);
		
		
		return verificationTokenRepository.save(returnValue);
		
		
	}


	@Override
	public VerificationTokenEntity getTokenFromTokenId(String tokenId) {
		
		return verificationTokenRepository.getToken(tokenId);
		
	}

}
