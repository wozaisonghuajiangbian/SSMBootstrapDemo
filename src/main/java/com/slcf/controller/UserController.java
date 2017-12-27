package com.slcf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.slcf.pojo.DeptBean;
import com.slcf.pojo.RoleBean;
import com.slcf.pojo.UserBean;
import com.slcf.service.DeptService;
import com.slcf.service.RoleService;
import com.slcf.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	UserService userService;
	
	@Autowired
	DeptService deptService;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping("/goMenu.action")
	public String goMenuPage(){
		return "menu";
	}
	@RequestMapping("/goRole.action")
	public String goRolePage(){
		return "role";
	}
	@RequestMapping("/goAuth.action")
	public String goAuthPage(){
		return "auth";
	}
	@RequestMapping("/goUser.action")
	public String goUserPage(){
		return "user";
	}
	@RequestMapping("/goChar.action")
	public String goHightPage(){
		return "hight";
	}
	/**
	 * 分页查询所以user表
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userList.action")
	public Map<String,Object> getUserList(
			@RequestParam(value="pageNumber",defaultValue="0",required=false)int pageNumber,
			@RequestParam("pageSize")int pageSize){
		Map<String,Object>map=userService.getUserList(pageNumber, pageSize);
		
		return map;
	}
	
	/**
	 * 修改用户密码
	 * @param uid
	 * @param pass
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upPass.action")
	public int upPass(@RequestParam("id")int uid,@RequestParam("pass")String pass,HttpServletRequest request){
		int i=userService.upPass(uid, pass, request);
		return i;
	}
	
	/**
	 * 验证用户登录账号是否唯一
	 * @param account
	 * @param uid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/validAccount.action")
	public Map<String,Object> validUserAccount(
			@RequestParam("account")String account,
			@RequestParam(value="uid",defaultValue="0",required=false)int uid){
		Map<String,Object> map=new HashMap<String, Object>();
		boolean flag=userService.validUserAccount(account, uid);
		if(flag){
			map.put("msg", "此账号可用");
		}else{
			map.put("msg", "此账号不可用");
		}
		return map;
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveUser.action")
	public int saveUser(UserBean user,HttpServletRequest request){
		int i=userService.saveUser(user, request);
		return i;
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delUser.action")
	public int delUser(@RequestParam("uid")int id){
		int i=userService.delUser(id);
		return i;
	}
	
	/**
	 * 批量删除用户
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delUsers.action")
	public int delUsers(@RequestParam("ids")int[] ids){
		int i=userService.delUsers(ids);
		return i;
	}
	
	/**
	 * 根据用户id查询用户信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserById.action")
	public Map<String,Object> queryUserById(@RequestParam("uid")int id){
		Map<String,Object>map=new HashMap<String, Object>();
		List<DeptBean>dlist=deptService.getDeptList();
		UserBean user=userService.queryUserById(id);
		map.put("user", user);
		map.put("dept", dlist);
		return map;
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upUser.action")
	public int upUser(UserBean user,HttpServletRequest request){
		int i=userService.upUser(user, request);
		return i;
	}
	
	/**
	 * 查询用户拥有的角色
	 * @param uid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRole.action")
	public Map<String,Object> getRole(@RequestParam("uid")int uid){
		List<RoleBean>rlist=roleService.getRoleList();
		List<Integer> userRoleList=userService.getUserRole(uid);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("role", rlist);
		map.put("userRole", userRoleList);
		return map;
	}
	
	/**
	 * 修改用户角色
	 * @param uid
	 * @param rid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upUserRple.action")
	public boolean saveUserRole(@RequestParam("uid")int uid,@RequestParam("rid")String ridStr){
		boolean flag=userService.saveUserRole(uid, ridStr);
		return flag;
	}
	
	/**
	 * 导出所有
	 * @param response
	 */
	@RequestMapping("/export.action")
	public void ExportExcel(HttpServletResponse response){
		userService.ExportUserList(response);
	}
	
	/**
	 * 导入
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/import.action")
	public Map<String,Object> importExcel(@RequestParam("files")MultipartFile file){
		return userService.importExcel(file);
	}
	
	/**
	 * 条件查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserByCon.action")
	public Map<String,Object> getUseByCon(
			@RequestParam("pageNumber")Integer pageNumber,
			@RequestParam("pageSize")Integer pageSize,
			@RequestParam(value="userId",required=false,defaultValue="0")Integer userId,
			@RequestParam(value="userName",required=false,defaultValue="values")String userName,
			@RequestParam(value="deptId",required=false,defaultValue="0")Integer deptId){
		return userService.getUserListByCon((pageNumber-1)*pageSize, pageNumber*pageSize, userId, userName, deptId);
	}
	
	/**
	 * 获取报表数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/hightInfo.action")
	public Map<String,Object> getHightsInfo(){
		return userService.getHightsInfo();
	}
}
