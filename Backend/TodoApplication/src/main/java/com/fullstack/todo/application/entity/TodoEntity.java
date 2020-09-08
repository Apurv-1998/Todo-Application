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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "todo")
@Data
public class TodoEntity implements Serializable {

	
	private static final long serialVersionUID = 3889916961221320289L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String todoId;
	@Column(nullable = false)
	private String todoName; //
	@CreationTimestamp
	private Date creationDate;
	@UpdateTimestamp
	private Date updatedDate;
	
	
	//Database Relations
	
	
	/* One-To-One */

	/* One-To-Many */
	
	@OneToMany(mappedBy = "todoDetails",cascade = CascadeType.ALL)
	private List<ActivityEntity> activityDetails;

	/* Many-To-One */
	
	@ManyToOne
	@JoinColumn(name = "users_id")
	@JsonIgnore
	private UserEntity userDetails;

	/* Many-To-Many */

}
