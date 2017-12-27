package com.slcf.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.slcf.pojo.UserBean;


public interface UserService {

	/**
	 * 分页查询所以user信息
	 * @param spage
	 * @param epage
	 * @return
	 */
	public Map<String,Object> getUserList(int pageNumber, int pageSize);
	
	/**
	 * 修改用户密码
	 * @param uid
	 * @param name
	 * @return
	 */
	public int upPass(int uid, String pass,HttpServletRequest request);
	
	/**
	 * 验证用户登录账号是否唯一
	 * @param account
	 * @param uid
	 * @return
	 */
	public boolean validUserAccount(String account,int uid);
	
	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	public int saveUser(UserBean user,HttpServletRequest request);
	
	/**
	 * 删除用户
	 * @param uid
	 * @return
	 */
	public int delUser(int uid);
	
	/**
	 * 批量删除用户
	 * @param ids
	 * @return
	 */
	public int delUsers(int[] ids);
	
	/**
	 * 根据用户id查询用户信息
	 * @param id
	 * @return
	 */
	public UserBean queryUserById(int id);
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public int upUser(UserBean user,HttpServletRequest request);
	
	/**
	 * 查询用户拥有的角色
	 * @param uid
	 * @return
	 */
	public List<Integer> getUserRole(int uid);
	
	/**
	 * 保存角色分配
	 * @param uid
	 * @param ridStr
	 * @return 保存成功返回true
	 */
	public boolean saveUserRole(int uid,String ridStr);
	
	
	/**
	 * 导出所有用户信息以及其对应的部门
	 * @return
	 */
	public void ExportUserList(HttpServletResponse response);
	
	/**
	 * 导入excel
	 * @param file
	 * @return
	 */
	public Map<String,Object> importExcel(MultipartFile file);
	
	/**
	 * 根据条件查询
	 * @param spage
	 * @param epage
	 * @param userId
	 * @param userName
	 * @param deptId
	 * @return
	 */
	public Map<String,Object> getUserListByCon(int spage,int epage,int userId,String userName,int deptId);
	
	/**
	 * 获取报表数据
	 * @return
	 */
	public Map<String,Object> getHightsInfo();
}
