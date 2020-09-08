package com.fullstack.todo.application.service;

import com.fullstack.todo.application.entity.UserEntity;
import com.fullstack.todo.application.entity.VerificationTokenEntity;

public interface VerificationTokenService {

	VerificationTokenEntity generateToken(UserEntity savedUser);

	VerificationTokenEntity getTokenFromTokenId(String tokenId);

}
