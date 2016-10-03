package com.stg.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "configs")
public class Config {

    @Id
    private String config;

    @Column
    private String value;

    public String getConfig() {
	return config;
    }

    public void setConfig(String config) {
	this.config = config;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return "Config [config=" + config + ", value=" + value + "]";
    }

}
