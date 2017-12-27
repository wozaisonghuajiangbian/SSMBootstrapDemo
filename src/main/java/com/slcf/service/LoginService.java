package com.slcf.service;

import java.util.List;

import com.slcf.pojo.MenuBean;
import com.slcf.pojo.UserBean;

public interface LoginService {
	//查询用户拥有的菜单
	public List<MenuBean>getMenuByUserId(int id);
	
	//根据登陆账号查询用户信息
	public UserBean getUserInfoByAccount(String name,String pass);
}
