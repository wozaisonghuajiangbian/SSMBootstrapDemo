package com.slcf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.slcf.pojo.MenuBean;
import com.slcf.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	MenuService menuService;
	
	/**
	 * 验证菜单名称是否唯一
	 * @param name
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkName.action")
	public Map<String,Object> checkMenuName(
			@RequestParam("menuName")String name,
			@RequestParam(value="mid",defaultValue="0",required=false)int id){
		String msg="";
		Map<String,Object>map=new HashMap<String, Object>();
		boolean flag=menuService.checkMenu(name, id);
		if(flag){
			msg="此名称可用";
		}else{
			msg="此名称已被占用";
		}
		map.put("msg", msg);
		
		return map;
	}
	
	/**
	 * 根据id删除
	 * @param mid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delMenu.action")
	public boolean delMenu(@RequestParam("mid")int mid){
		boolean flag=false;
		int i=menuService.delMenuById(mid);
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 根据id查询
	 * @param mid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMenu.action")
	public Map<String,Object> quertMenuById(@RequestParam("mid")int mid){
		Map<String,Object>map=new HashMap<String, Object>();
		List<MenuBean> list=menuService.getParentMenu();
		MenuBean menu=menuService.quertMenuById(mid);
		map.put("list", list);
		map.put("menu", menu);
		return map;
	}
	
	/**
	 * 分页查询所有
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/menuList.action")
	public Map<String,Object> getMenuList(
			@RequestParam("pageNumber")int pageNumber,
			@RequestParam("pageSize")int pageSize){
		Map<String,Object>map=menuService.getMenuList(pageNumber, pageSize);
		return map;
	}
	
	/**
	 * 添加
	 * @param menu
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveMenu.action")
	public boolean saveMenu(MenuBean menu){
		boolean flag=menuService.saveMenu(menu);
		return flag;
	}
	
	/**
	 * 修改
	 * @param menu
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upMenu.action")
	public boolean upMenu(MenuBean menu){
		boolean flag=menuService.upMenu(menu);
		return flag;
	}
	
	/**
	 * 查询所有父级菜单
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/parentMenu.action")
	public List<MenuBean> getParentMenu(){
		return menuService.getParentMenu();
	}
}
