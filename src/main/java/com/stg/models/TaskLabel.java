package com.stg.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task_labels")
public class TaskLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "label")
    private String label;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getLabel() {
	return label;
    }

    public void setLabel(String label) {
	this.label = label;
    }

    @Override
    public String toString() {
	return "TaskLabel [id=" + id + ", label=" + label + "]";
    }

}
