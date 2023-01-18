package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="persona", schema="public")
public class Empleado {
	@Id
	private Integer id;
	private Integer gender_id;
	private Integer job_id;
	private Integer name;
	private Integer last_name;
	private Date birthdate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGender_id() {
		return gender_id;
	}
	public void setGender_id(Integer gender_id) {
		this.gender_id = gender_id;
	}
	public Integer getJob_id() {
		return job_id;
	}
	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}
	public Integer getName() {
		return name;
	}
	public void setName(Integer name) {
		this.name = name;
	}
	public Integer getLast_name() {
		return last_name;
	}
	public void setLast_name(Integer last_name) {
		this.last_name = last_name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	
	
}
