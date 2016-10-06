package com.stg.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "profiles")
@NamedQueries({
	@NamedQuery(name = "Profile.findUnwelcomedUsersByStartingDate", query = "SELECT prof.user FROM Profile prof WHERE prof.startDate <= ?1 AND prof.user.isWelcomed = false") })
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @OneToMany(mappedBy = "profile")
    private List<Task> tasks;

    public Date getEndDate() {
	return this.endDate;
    }

    public long getId() {
	return this.id;
    }

    public Date getStartDate() {
	return this.startDate;
    }

    public List<Task> getTasks() {
	return this.tasks;
    }

    public User getUser() {
	return this.user;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    public void setId(long id) {
	this.id = id;
    }

    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    public void setTasks(List<Task> tasks) {
	this.tasks = tasks;
    }

    public void setUser(User user) {
	this.user = user;
    }

    @Override
    public String toString() {
	return "Profile [id=" + this.id + ", user=" + this.user + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", tasks=" + this.tasks + "]";
    }
}
