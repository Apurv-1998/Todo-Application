package com.fullstack.todo.application.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fullstack.todo.application.entity.VerificationTokenEntity;


@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationTokenEntity, Long> {
	
	@Query(value = "SELECT * FROM verification_tokens v WHERE v.token_id= ?1", nativeQuery = true)
	VerificationTokenEntity getToken(String tokenId);

}
