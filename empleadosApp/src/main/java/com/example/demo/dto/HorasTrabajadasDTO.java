package com.example.demo.dto;


public class HorasTrabajadasDTO {
	
	private Integer id;
	private Integer employee_id;
	private Integer worked_hours;
	private String worked_date;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getWorked_hours() {
		return worked_hours;
	}
	public void setWorked_hours(Integer worked_hours) {
		this.worked_hours = worked_hours;
	}
	public String getWorked_date() {
		return worked_date;
	}
	public void setWorked_date(String date) {
		this.worked_date = date;	
	}
	
}
