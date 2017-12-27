package com.slcf.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DeptOpt {

	private Integer dopt_id;
	private Integer d_id;
	private String dopt_name;
	private String dopt_type;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date dopt_time;
	public Integer getDopt_id() {
		return dopt_id;
	}
	public void setDopt_id(Integer dopt_id) {
		this.dopt_id = dopt_id;
	}
	public Integer getD_id() {
		return d_id;
	}
	public void setD_id(Integer d_id) {
		this.d_id = d_id;
	}
	public String getDopt_name() {
		return dopt_name;
	}
	public void setDopt_name(String dopt_name) {
		this.dopt_name = dopt_name;
	}
	public String getDopt_type() {
		return dopt_type;
	}
	public void setDopt_type(String dopt_type) {
		this.dopt_type = dopt_type;
	}
	public Date getDopt_time() {
		return dopt_time;
	}
	public void setDopt_time(Date dopt_time) {
		this.dopt_time = dopt_time;
	}
}
