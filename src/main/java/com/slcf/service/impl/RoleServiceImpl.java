package com.slcf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.slcf.dao.RoleDao;
import com.slcf.pojo.MenuBean;
import com.slcf.pojo.RoleBean;
import com.slcf.pojo.RoleMenu;
import com.slcf.service.RoleService;
import com.slcf.util.Trees;
import com.slcf.util.ViewTree;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	RoleDao roleDao;
	
	public List<RoleBean> getRoleList() {
		return roleDao.getRoleList();
	}

	public Map<String, Object> getRoleListByPage(int spage, int epage) {
		Map<String,Object>map=new HashMap<String, Object>();
		int count=roleDao.getCount();
		List<RoleBean>list=roleDao.getRoleListByPage(spage, epage);
		map.put("total", count);
		map.put("rows", list);
		return map;
	}

	public boolean checkRoleName(String name,int rid) {
		boolean flag=false;
		RoleBean role=roleDao.queryRoleByName(name);
		if(rid!=0){//修改
			if((role!=null&&role.getRole_id()==rid)||role==null){
				flag=true;
			}
		}else{//添加
			if(role==null){
				flag=true;
				}
		}
		return flag;
	}

	public int saveRole(RoleBean role, HttpServletRequest request) {
		int j=0;
		String name=(String)request.getSession().getAttribute("NAME");
		int i=roleDao.saveRole(role);
		int id=roleDao.queryRoleByName(role.getRole_name()).getRole_id();
		if(i>0){
			j=roleDao.saveRoleOpt(name, "添加", id);
		}
		return j;
	}

	public int delRole(int rid) {
		int j=0;
		int i=roleDao.delRoleOpt(rid);
		if(i>0){
			j=roleDao.delRole(rid);
		}
		return j;
	}

	public RoleBean queryRoleById(int rid) {
		
		return roleDao.queryRoleById(rid);
	}

	public int upRole(RoleBean role,HttpServletRequest request) {
		String name=(String)request.getSession().getAttribute("NAME");
		int j=0;
		int i=roleDao.upRole(role);
		if(i>0){
			j=roleDao.saveRoleOpt(name, "修改", role.getRole_id());
		}
		return j;
	}

	/**
	 * 菜单
	 */
	public List<ViewTree> getViewTree(int rid) {
		List<ViewTree>tree=new ArrayList<ViewTree>();
		List<Integer>list=roleDao.queryMenuByRoleId(rid);
		List<MenuBean>plist=roleDao.getParentMenuList();//父菜单
		for(MenuBean m:plist){
			ViewTree parent=new ViewTree();
			parent.setId(m.getMenu_id());
			parent.setPid(m.getParentId());
			parent.setText(m.getMenu_name());
			parent.setIcon(m.getIcon());
			
			for(Integer k:list){
				if(m.getMenu_id().equals(k)){
					Trees t=new Trees();
					t.setChecked(true);
					parent.setState(t);
					}
			}
			List<MenuBean >clist=roleDao.queryChildMenuByPid(m.getMenu_id());//子菜单
			List<ViewTree>childList=new ArrayList<ViewTree>();
			for(MenuBean c:clist){
				ViewTree child=new ViewTree();
				child.setId(c.getMenu_id());
				child.setPid(c.getParentId());
				child.setText(c.getMenu_name());
				child.setIcon(c.getIcon());
				for(Integer k:list){
					if(c.getMenu_id().equals(k)){
						Trees tt=new Trees();
						tt.setChecked(true);
						child.setState(tt);
						}
				}
				
				childList.add(child);
			}
			if(childList.size()>=1){
				parent.setNodes(childList);
			}
			tree.add(parent);
		}
		return tree;
	}

	
	public boolean saveRoleMenu(int rid, int[] mid) {
		boolean flag=false;
		int sum=0;
		List<RoleMenu>rmList=roleDao.queryRoleMenu(rid);
		if(rmList!=null&&rmList.size()>0){
			int x=roleDao.delRoleMenu(rid);
			if(x>0){
				for(Integer i:mid){
					int j=roleDao.saveRoleMenu(rid, i);
					sum+=j;
				}
				if(sum==mid.length){
					flag=true;
				}
			}
		}else{
			for(Integer i:mid){
				int j=roleDao.saveRoleMenu(rid, i);
				sum+=j;
			}
			if(sum==mid.length){
				flag=true;
			}
		}
		return flag;
	}
}