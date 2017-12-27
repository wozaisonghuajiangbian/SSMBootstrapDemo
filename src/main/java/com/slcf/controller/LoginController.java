package com.slcf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.slcf.pojo.MenuBean;
import com.slcf.pojo.UserBean;
import com.slcf.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	LoginService loginService;
	
	/**
	 * 获取菜单
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/login.action")
	public String getMenuByUserId(HttpServletRequest request,@RequestParam(value="uid")Integer id){
		List<MenuBean>menuList=loginService.getMenuByUserId(id);
		request.getSession().setAttribute("mlist", menuList);
		return "redirect:../index.jsp";
	}
	
	/**
	 * 用户登陆
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doLogin.action")
	public Map<String,Object> doLoginByAccount(UserBean user,HttpServletRequest request){
		Map<String,Object>map=new HashMap<String, Object>();
		Integer i=null;
		UserBean userBean=loginService.getUserInfoByAccount(user.getUser_account(),user.getUser_password());
		if(userBean!=null){
			if(user.getUser_password().equals(userBean.getUser_password())){
				i=0;//登陆成功
				map.put("id", userBean.getUser_id());
				request.getSession().setAttribute("USER", userBean);
				request.getSession().setAttribute("NAME", userBean.getUser_name());
				request.getSession().setAttribute("PASS", userBean.getUser_password());
				request.getSession().setAttribute("ID", userBean.getUser_id());
			}else{
				i=1;//登录密码不正确
			}
		}else{
				i=2;//此账号不存在
		}
		map.put("i", i);
		return map;
	}
	
	/**
	 * 退出登陆
	 * @param request
	 * @return
	 */
	@RequestMapping("/quit.action")
	public String quitLogin(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:../login.jsp";
	}
}
