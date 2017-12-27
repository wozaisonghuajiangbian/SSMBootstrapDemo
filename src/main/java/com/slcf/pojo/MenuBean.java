package com.slcf.pojo;

import java.util.List;

public class MenuBean {

	private Integer menu_id;
	private String menu_name;
	private String url;
	private Integer parentId;
	private String icon;
	private String statu;
	private List<MenuBean>children;
	private String str;
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public List<MenuBean> getChildren() {
		return children;
	}
	public void setChildren(List<MenuBean> children) {
		this.children = children;
	}
	public Integer getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		if(statu.equals("0")){
			this.str="启用";
		}else{
			this.str="禁用";
		}
		this.statu = statu;
	}
}
