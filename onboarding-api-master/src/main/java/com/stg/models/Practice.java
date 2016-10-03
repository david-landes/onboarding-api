package com.stg.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "practice")
public class Practice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String practice;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getPractice() {
	return practice;
    }

    public void setPractice(String practice) {
	this.practice = practice;
    }

    @Override
    public String toString() {
	return "Practice [id=" + id + ", practice=" + practice + "]";
    }

}
