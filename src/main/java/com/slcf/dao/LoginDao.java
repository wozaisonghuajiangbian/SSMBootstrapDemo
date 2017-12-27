package com.slcf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.slcf.pojo.MenuBean;
import com.slcf.pojo.UserBean;

@Repository
public interface LoginDao {

	/**
	 * 根据用户id查找父级菜单
	 * @param id 登陆用户的id
	 * @return
	 */
	@Select("select m.menu_id,m.menu_name,m.parentID,m.icon,m.url from tb_menu m,"
			+ "tb_role_menu rm,tb_role r,tb_user_role ur,tb_user u "
			+ "WHERE m.menu_id=rm.menu_id and rm.role_id=r.role_id and "
			+ "r.role_id=ur.role_id and ur.user_id =u.user_id and m.statu=0 and m.parentID=0 and u.user_id=#{id} ORDER BY m.menu_id")
	public List<MenuBean> getParenMenuByUserId(int id);
	
	/**
	 * 根据父级菜单id查找对应的子级菜单id
	 * @param pid
	 * @return
	 */
	@Select("select b.menu_id,b.menu_name,b.parentID,b.icon,b.url ,b.statu from ( "
			+ "select m.menu_id ,m.menu_name,m.parentID,m.icon,m.url,m.statu from tb_menu m, "
			+ "tb_role_menu rm,tb_role r,tb_user_role ur,tb_user u WHERE m.menu_id=rm.menu_id "
			+ "and rm.role_id=r.role_id and  r.role_id=ur.role_id and ur.user_id =u.user_id and "
			+ "m.statu=0 and m.parentID!=0 and u.user_id=#{uid} )b WHERE b.statu=0 and b.parentID=#{pid} "
			+ "ORDER BY b.menu_id")
	public List<MenuBean> getChildMenuByPid(@Param("uid")int uid,@Param("pid")int pid);
	
	/**
	 * 根据登陆账号查询用户信息
	 * @param name
	 * @return
	 */
	@Select("select * from tb_user u where u.user_account=#{name}")
	public UserBean getInfoByAccount(String name);
}
