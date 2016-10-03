package com.stg.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @RestResource(exported = true)
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @RestResource(exported = true)
    @ManyToOne
    @JoinColumn(name = "practice_id")
    private Practice practice;

    public User() {

    }

    public User(long id) {
	this.id = id;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
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

    @Override
    public String toString() {
	return "User [id=" + id + ", firstName=" + firstName + ", lastName="
		+ lastName + ", email=" + email + ", position=" + position
		+ ", practice=" + practice + "]";
    }
}
