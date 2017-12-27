package com.slcf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slcf.dao.MenuDao;
import com.slcf.pojo.MenuBean;
import com.slcf.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuDao menuDao;
	
	public boolean checkMenu(String name, int id) {
		boolean flag=false;
		if(id!=0){//修改
			MenuBean menu=menuDao.getMenuByName(name);
			if(menu==null||(menu!=null&&menu.getMenu_id()==id)){
				flag=true;
			}
		}else{//添加
			MenuBean menu=menuDao.getMenuByName(name);
			if(menu==null){
				flag=true;
			}
		}
		
		return flag;
	}

	public MenuBean quertMenuById(int id) {
		return menuDao.quertMenuById(id);
	}

	public int delMenuById(int mid) {
		// TODO Auto-generated method stub
		return menuDao.delMenuById(mid);
	}

	public boolean upMenu(MenuBean menu) {
		boolean flag=false;
		int i=menuDao.upMenu(menu);
		if(i>0){
			flag=true;
		}
		return flag;
	}

	public boolean saveMenu(MenuBean menu) {
		boolean flag=true;
		int i=menuDao.saveMenu(menu);
		if(i>0){
			flag=true;
		}
		return flag;
	}

	public Map<String,Object> getMenuList(int pageNumber, int pageSize) {
		Map<String,Object>map=new HashMap<String, Object>();
		List<MenuBean>list=menuDao.getMenuList((pageNumber-1)*pageSize,pageNumber*pageSize);
		int count =menuDao.getCount();
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	public List<MenuBean> getParentMenu() {
		// TODO Auto-generated method stub
		return menuDao.getParentMenu();
	}

}
