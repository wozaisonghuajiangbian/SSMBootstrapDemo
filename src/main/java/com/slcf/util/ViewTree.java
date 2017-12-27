package com.slcf.util;

import java.util.List;

public class ViewTree {

	private Integer id;
	private Integer pid;
	private String text;
	private String icon;//<span class="icon check-icon glyphicon glyphicon-check"></span>
	private List<ViewTree> nodes;
	private Trees state;
	public Trees getState() {
		return state;
	}
	public void setState(Trees state) {
		this.state = state;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<ViewTree> getNodes() {
		return nodes;
	}
	public void setNodes(List<ViewTree> nodes) {
		this.nodes = nodes;
	}
}
