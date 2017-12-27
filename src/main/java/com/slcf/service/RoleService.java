package com.slcf.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.slcf.pojo.RoleBean;
import com.slcf.util.ViewTree;

public interface RoleService {

	/**
	 * 查询所有角色
	 * @return
	 */
	public List<RoleBean> getRoleList();
	
	/**
	 * 分页查询所有
	 * @param spage
	 * @param epage
	 * @return
	 */
	public Map<String,Object> getRoleListByPage(int spage,int epage);
	
	/**
	 * 验证角色名称是否唯一
	 * @param name
	 * @return 唯一返回true
	 */
	public boolean checkRoleName(String name,int rid);
	
	/**
	 * 添加角色
	 * @param role
	 * @param request
	 * @return
	 */
	public int saveRole(RoleBean role,HttpServletRequest request);
	
	/**
	 * 删除角色表
	 * @param rid
	 * @return
	 */
	public int delRole(int rid);
	
	/**
	 * 根据角色id查找
	 * @param rid
	 * @return
	 */
	public RoleBean queryRoleById(int rid);
	
	/**
	 * 跟新角色
	 * @param role
	 * @return
	 */
	public int upRole(RoleBean role,HttpServletRequest request);
	
	/**
	 * 查询所有的菜单，并让此角色拥有的菜单选中
	 * @param rid
	 * @return
	 */
	public List<ViewTree> getViewTree(int rid);
	
	/**
	 * 添加角色菜单表
	 * @param rid
	 * @param mid
	 * @return
	 */
	public boolean saveRoleMenu(int rid,int[] mid);
}
