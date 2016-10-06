package com.stg.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task_categories")
public class TaskCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "category")
    private String category;

    @Column(name = "display_order")
    private int displayOrder;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getCategory() {
	return category;
    }

    public void setCategory(String category) {
	this.category = category;
    }

    public int getDisplayOrder() {
	return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
	this.displayOrder = displayOrder;
    }

    @Override
    public String toString() {
	return "TaskCategory [id=" + id + ", category=" + category + ", displayOrder=" + displayOrder + "]";
    }

}
