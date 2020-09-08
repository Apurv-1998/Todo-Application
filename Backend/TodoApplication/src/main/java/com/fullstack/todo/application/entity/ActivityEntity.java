package com.fullstack.todo.application.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Table(name = "activity")
@Data
public class ActivityEntity implements Serializable {

	
	private static final long serialVersionUID = 6256631274525613482L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String activityId;
	@Column(nullable = false)
	private String activityName;

	@Lob
	@Column(nullable = false, length = 3000)
	private String text; //
	
	@CreationTimestamp
	private Date creationTime;
	@UpdateTimestamp
	private Date updateTime;
	
	
	@Column(nullable = false)
	private Date startDate; //
	@Column(nullable = false)
	private Date endDate; //
	
	@Column(nullable = false)
	private boolean isCompleted = false;

	
	
	
	//Database Relations
	
	
	/* One-To-One */

	/* One-To-Many */

	/* Many-To-One */
	
	@ManyToOne
	@JoinColumn(name = "todo_id")
	@JsonIgnore
	private TodoEntity todoDetails;

	/* Many-To-Many */
	
	
	
}
