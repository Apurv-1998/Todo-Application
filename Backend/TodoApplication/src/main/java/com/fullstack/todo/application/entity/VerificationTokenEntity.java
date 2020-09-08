package com.fullstack.todo.application.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "verification_tokens")
@Data
public class VerificationTokenEntity implements Serializable {


	private static final long serialVersionUID = -166575547315216540L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	private String tokenId;
	
	//Database Relations
	
	
	/* One-To-One */
	
	@OneToOne
	@JoinColumn(name = "users_id")
	private UserEntity userDetails;

	/* One-To-Many */

	/* Many-To-One */

	/* Many-To-Many */
		

}
