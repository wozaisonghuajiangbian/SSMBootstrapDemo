package com.slcf.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserBean {
	
	private Integer user_id;
	private Integer dept_id;
	private String user_account;
	private String user_password;
	private String user_name;
	private String img;
	private Integer user_age;
	private String user_sex;
	private String user_address;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date user_birth;
	private String user_phone;
	private String email;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date reg_time;
	
	private String dept_name;
	private String birthStr;
	private Integer role_id;
	
	private String mon;
	private int num;
	public String getMon() {
		return mon;
	}
	public void setMon(String mon) {
		this.mon = mon;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getBirthStr() {
		return birthStr;
	}
	public void setBirthStr(String birthStr) {
		this.birthStr = birthStr;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getDept_id() {
		return dept_id;
	}
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getUser_age() {
		return user_age;
	}
	public void setUser_age(Integer user_age) {
		this.user_age = user_age;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public Date getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(Date user_birth) {
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
		birthStr=sm.format(user_birth);
		this.user_birth = user_birth;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getReg_time() {
		return reg_time;
	}
	public void setReg_time(Date reg_time) {
		this.reg_time = reg_time;
	}
}
