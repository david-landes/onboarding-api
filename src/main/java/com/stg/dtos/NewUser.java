package com.stg.dtos;

public class NewUser {

    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private String practice;
    private String date;

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

    public String getPosition() {
	return position;
    }

    public void setPosition(String position) {
	this.position = position;
    }

    public String getPractice() {
	return practice;
    }

    public void setPractice(String practice) {
	this.practice = practice;
    }

    public String getDate() {
	return date;
    }

    public void setDate(String date) {
	this.date = date;
    }

    @Override
    public String toString() {
	return "NewUser [firstName=" + firstName + ", lastName=" + lastName
		+ ", email=" + email + ", position=" + position + ", practice="
		+ practice + ", date=" + date + "]";
    }
}
