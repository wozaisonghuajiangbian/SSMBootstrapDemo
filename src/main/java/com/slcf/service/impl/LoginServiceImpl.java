package com.slcf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slcf.dao.LoginDao;
import com.slcf.pojo.MenuBean;
import com.slcf.pojo.UserBean;
import com.slcf.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao loginDao;
	
	/**
	 * 根据用户id查找此用户所用户的菜单
	 */
	public List<MenuBean> getMenuByUserId(int id) {
		List<MenuBean>plist=loginDao.getParenMenuByUserId(id);
		for(MenuBean m:plist){
			List<MenuBean>clist=loginDao.getChildMenuByPid(id,m.getMenu_id());
			if(clist!=null){
				m.setChildren(clist);
			}
		}
		return plist;
	}

	/**
	 * 根据用户登陆账号查询用户信息
	 */
	public UserBean getUserInfoByAccount(String name,String pass) {
		UserBean user=loginDao.getInfoByAccount(name);
		return user;
	}
}
