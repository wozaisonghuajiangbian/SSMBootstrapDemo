package com.slcf.pojo;

public class BillNote {

	private Integer bill_id;
	private String type;
	private String reason;
	private String stratTime;
	private String endTime;
	private String reMark;//备注
	private String name;
	private String status;
	public Integer getBill_id() {
		return bill_id;
	}
	public void setBill_id(Integer bill_id) {
		this.bill_id = bill_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStratTime() {
		return stratTime;
	}
	public void setStratTime(String stratTime) {
		this.stratTime = stratTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getReMark() {
		return reMark;
	}
	public void setReMark(String reMark) {
		this.reMark = reMark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
