package com.slcf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.slcf.pojo.MenuBean;
import com.slcf.pojo.RoleBean;
import com.slcf.pojo.RoleMenu;

@Repository
public interface RoleDao {

	/**
	 * 根据角色名称查询
	 * @param name
	 * @return
	 */
	@Select("select * from tb_role where role_name =#{name}")
	public RoleBean queryRoleByName(String name);
	
	/**
	 * 查询所有
	 * @return
	 */
	@Select("select * from tb_role order by role_id")
	public List<RoleBean> getRoleList();
	
	/**
	 * 分页查询
	 * @param spage
	 * @param epage
	 * @return
	 */
	@Select("select * from tb_role r  order by r.role_id desc limit #{spage},#{epage}")
	public List<RoleBean> getRoleListByPage(@Param("spage")int spage,@Param("epage")int epage);
	
	/**
	 * 统计数量
	 * @return
	 */
	@Select("select count(*) from tb_role")
	public int getCount();
	
	/**
	 * 添加角色操作表
	 * @param name
	 * @param type
	 * @param rid
	 * @return
	 */
	@Insert("insert into tb_role_opt(r_id,ropt_name,ropt_type,ropt_time) values(#{rid},#{name},#{type},sysdate())")
	public int saveRoleOpt(@Param("name")String name,@Param("type")String type,@Param("rid")int rid);
	
	/**
	 * 添加角色表
	 * @param role
	 * @return
	 */
	@Insert("insert into tb_role(role_name,role_desc,pid) values(#{role_name},#{role_desc},#{pid})")
	public int saveRole(RoleBean role);
	
	/**
	 * 删除角色操作记录表
	 * @param rid
	 * @return
	 */
	@Delete("delete from tb_role_opt where r_id=#{rid}")
	public int delRoleOpt(int rid);
	
	/**
	 * 删除角色表
	 * @param rid
	 * @return
	 */
	@Delete("delete from tb_role where role_id=#{rid}")
	public int delRole(int rid);
	
	/**
	 * 根据id查找
	 * @param rid
	 * @return
	 */
	@Select("select * from tb_role where role_id=#{rid}")
	public RoleBean queryRoleById(int rid);
	
	/**
	 * 跟新角色
	 * @param role
	 * @return
	 */
	@Update("update tb_role set role_name=#{role_name},role_desc=#{role_desc},pid=#{pid} where role_id=#{role_id}")
	public int upRole(RoleBean role);
	
	/**
	 * 查询所有启用父级菜单
	 * @return
	 */
	@Select("select * from tb_menu where parentID=0 and statu='0' ORDER BY menu_id")
	public List<MenuBean> getParentMenuList();
	
	/**
	 * 查询父级菜单下的所有启用子菜单
	 * @param pid
	 * @return
	 */
	@Select("select * from tb_menu where parentID=#{pid} and statu='0' ORDER BY menu_id")
	public List<MenuBean> queryChildMenuByPid(int pid);
	
	/**
	 * 根据角色id查询此角色用有的菜单权限
	 * @param rid
	 * @return
	 */
	@Select("select menu_id from tb_role_menu  where role_id=#{rid} ")
	public List<Integer> queryMenuByRoleId(int rid);
	
	/**
	 * 添加角色菜单表
	 * @param rid
	 * @param mid
	 * @return
	 */
	@Insert("insert into tb_role_menu (menu_id,role_id) values(#{mid},#{rid})")
	public int saveRoleMenu(@Param("rid")int rid,@Param("mid")int mid);
	
	/**
	 * 删除角色菜单表
	 * @param rid
	 * @return
	 */
	@Delete("delete from tb_role_menu where role_id=#{rid}")
	public int delRoleMenu(int rid);
	
	/**
	 * 根据角色id查询
	 * @param rid
	 * @return
	 */
	@Select("select * from tb_role_menu where role_id=#{rid}")
	public List<RoleMenu> queryRoleMenu(int rid);
}
