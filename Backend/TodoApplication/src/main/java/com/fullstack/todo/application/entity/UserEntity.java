package com.fullstack.todo.application.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -6793954179990449793L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String userId;
	@Column(nullable = false)
	private String firstName; //
	@Column(nullable = false)
	private String lastName; //
	@Column(nullable = false)
	private Date dob;        //
	@Column(nullable = false)
	private String email;    //
	@Column(nullable = false)
	private String password; //
	@Column(nullable = false)
	private String regPassword;
	@Column(nullable = false)
	private boolean isVerified = false;
	@Column(nullable = false)
	private boolean isLoggedIn = false;
	@CreationTimestamp
	private Date creationDate;
	
	
	//Database Relations
	
	
	/*One-To-One*/
	
	/*One-To-Many*/
	
	@ToString.Exclude
	@OneToMany(mappedBy = "userDetails",cascade = CascadeType.ALL)
	private List<TodoEntity> todoDetails;
	
	/*Many-To-One*/
	
	/*Many-To-Many*/
	

}
