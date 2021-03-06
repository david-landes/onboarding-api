package com.stg.models;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task_comments")
public class TaskComment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@RestResource(exported = false)
	private User user;

	@Column(name = "comment")
	private String comment;

	@Column(name = "creation_time", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;

	@Column(name = "modification_time", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	@Override
	public String toString() {
		return "TaskComment [id=" + id + ", task=" + task + ", comment=" + comment + ", creationTime=" + creationTime + ", modificationTime=" + modificationTime + "]";
	}

}
