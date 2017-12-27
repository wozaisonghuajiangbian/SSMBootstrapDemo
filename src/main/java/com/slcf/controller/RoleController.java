package com.slcf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.slcf.pojo.RoleBean;
import com.slcf.service.RoleService;
import com.slcf.util.ViewTree;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleService roleService;
	
	/**
	 * 验证角色名称是否唯一
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkRole.action")
	public Map<String,Object> checkRoleName(
			@RequestParam("roleName")String name,
			@RequestParam(value="rid",defaultValue="0",required=false)int rid
			){
		boolean flag=roleService.checkRoleName(name,rid);
		Map<String,Object>map=new HashMap<String, Object>();
		if(flag){
			map.put("msg", "名称可用");
		}else{
			map.put("msg", "名称已被占可用");
		}
		return map;
	}
	
	/**
	 * 查询所有角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/roleList.action")
	public List<RoleBean> getRoleList(){
		return roleService.getRoleList();
	}
	
	/**
	 * 分页查询
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/rolePages.action")
	public Map<String,Object> getRoleListByPage(
			@RequestParam(value="pageNumber",defaultValue="0",required=false)int pageNumber,
			@RequestParam("pageSize")int pageSize){
		Map<String,Object>map=roleService.getRoleListByPage((pageNumber-1)*pageSize, pageNumber*pageSize);
		return map;
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveRole.action")
	public int saveRole(RoleBean role,HttpServletRequest request){
		int i=roleService.saveRole(role, request);
		return i;
	}
	
	/**
	 * 根据id删除角色
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delRole.action")
	public int delRole(@RequestParam("rid")int id){
		return roleService.delRole(id);
	}
	
	/**
	 * 根据id查找
	 * @param rid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryRole.action")
	public Map<String,Object> queryRoleById(@RequestParam("rid")int rid){
		Map<String,Object>map=new HashMap<String, Object>();
		List<RoleBean>rlist=roleService.getRoleList();
		RoleBean role=roleService.queryRoleById(rid);
		map.put("role", role);
		map.put("rlist", rlist);
		return map;
	}
	
	/**
	 * 更新角色
	 * @param role
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upRole.action")
	public int upRole(RoleBean role,HttpServletRequest request){
		System.out.println(role.getRole_id()+"==========");
		return roleService.upRole(role, request);
	}
	
	/**
	 * 查找菜单
	 * @param rid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/viewTree.action")
	public List<ViewTree> getRoleTree(@RequestParam("rid")int rid){
		return roleService.getViewTree(rid);
	}
	
	/**
	 * 保存角色权限
	 * @param rid
	 * @param mid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveRoleMenu.action")
	public boolean saveRoleMenu(@RequestParam("rid")int rid,@RequestParam("mid")int []mid){
		boolean flag=false;
		flag=roleService.saveRoleMenu(rid, mid);
		return flag;
	}
}
