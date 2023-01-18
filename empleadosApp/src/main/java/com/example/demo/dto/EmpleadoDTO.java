package com.example.demo.dto;

public class EmpleadoDTO {
	private Integer id;
	private Integer gender_id;
	private Integer job_id;
	private Integer name;
	private Integer last_name;
	private Integer birthdate;
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
	public Integer getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Integer birthdate) {
		this.birthdate = birthdate;
	}
}
