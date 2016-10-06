package com.stg.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "task_templates")
public class TaskTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "task")
    private String task;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private TaskCategory category;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "practice_id")
    private Practice practice;

    @OneToMany
    @JoinTable(name = "templates_labels_map", joinColumns = @JoinColumn(name = "template_id"), inverseJoinColumns = @JoinColumn(name = "label_id"))
    private Set<TaskLabel> labels;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
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

    public Position getPosition() {
	return position;
    }

    public void setPosition(Position position) {
	this.position = position;
    }

    public Practice getPractice() {
	return practice;
    }

    public void setPractice(Practice practice) {
	this.practice = practice;
    }

    public Set<TaskLabel> getLabels() {
	return labels;
    }

    public void setLabels(Set<TaskLabel> labels) {
	this.labels = labels;
    }

    @Override
    public String toString() {
	return "TaskTemplate [id=" + id + ", task=" + task + ", category=" + category + ", position=" + position + ", practice=" + practice + ", labels=" + labels + "]";
    }

}
