package com.example.demo.dto;


public class HorasTrabajadasDTO {
	
	private Integer id;
	private Integer employee_id;
	private Integer worked_hours;
	private String worked_date;
	private String start_date;
	private String end_date;
	
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
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
