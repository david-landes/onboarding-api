package com.stg.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "tasks")
@NamedQueries({ @NamedQuery(name = "Task.findByProfileId", query = "FROM Task WHERE profile.id = ?1 ORDER BY task.category.displayOrder, task.id") })
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    @RestResource(exported = false)
    private Profile profile;

    @Column(name = "task")
    private String task;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private TaskCategory category;

    @Column(name = "task_date")
    @Temporal(TemporalType.DATE)
    private Date taskDate;

    @Column(name = "completion_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completionDate;

    @OneToMany
    @JoinTable(name = "tasks_labels_map", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "label_id"))
    private Set<TaskLabel> labels;

    @OneToMany(mappedBy = "task")
    private List<TaskComment> comments;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public Profile getProfile() {
	return profile;
    }

    public void setProfile(Profile profile) {
	this.profile = profile;
    }

    public String getTask() {
	return task;
    }

    public void setTask(String task) {
	this.task = task;
    }

    public TaskCategory getCategory() {
	return category;
    }

    public void setCategory(TaskCategory category) {
	this.category = category;
    }

    public Date getTaskDate() {
	return taskDate;
    }

    public void setTaskDate(Date taskDate) {
	this.taskDate = taskDate;
    }

    public Date getCompletionDate() {
	return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
	this.completionDate = completionDate;
    }

    public Set<TaskLabel> getLabels() {
	return labels;
    }

    public void setLabels(Set<TaskLabel> labels) {
	this.labels = labels;
    }

    public List<TaskComment> getComments() {
	return comments;
    }

    public void setComments(List<TaskComment> comments) {
	this.comments = comments;
    }

    @Override
    public String toString() {
	return "Task [id=" + id + ", profile=" + profile + ", task=" + task + ", category=" + category + ", taskDate=" + taskDate + ", completionDate=" + completionDate
		+ ", labels=" + labels + ", comments=" + comments + "]";
    }

}
