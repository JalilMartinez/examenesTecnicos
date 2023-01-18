package com.example.demo.dto;



public class EmpleadoDTO {
	private Integer id;
	private Integer gender_id;
	private Integer job_id;
	private String name;
	private String last_name;
	private String birthdate;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
}
