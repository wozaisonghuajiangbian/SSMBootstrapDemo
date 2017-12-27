package com.slcf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.slcf.pojo.MenuBean;

@Repository
public interface MenuDao {

	/**
	 * 根据菜单名验证菜单名是否唯一
	 * @param name
	 * @return
	 */
	@Select("select * from tb_menu where menu_name=#{name}")
	public MenuBean getMenuByName(String name);
	
	/**
	 * 分页查询所有
	 * @param spage
	 * @param epage
	 * @return
	 */
	@Select("select * from tb_menu order by menu_id limit #{spage},#{epage}")
	public List<MenuBean> getMenuList(@Param("spage")int spage,@Param("epage")int epage);
	
	/**
	 * 统计数量
	 * @return
	 */
	@Select("select count(*) from tb_menu")
	public int getCount();
	
	/**
	 * 添加菜单
	 * @param menu
	 * @return
	 */
	@Insert("insert into tb_menu(menu_name,url,parentID,icon,statu ) values(#{menu_name},#{url},#{parentId},#{icon},#{statu})")
	public int saveMenu(MenuBean menu);
	
	/**
	 * 根据id删除
	 * @param mid
	 * @return
	 */
	@Delete("delete from tb_menu where menu_id=#{mid}")
	public int delMenuById(int mid);
	
	/**
	 * 根据id查找
	 * @param mid
	 * @return
	 */
	@Select("select * from tb_menu where menu_id=#{mid}")
	public MenuBean quertMenuById(int mid);
	
	/**
	 * 更新菜单
	 * @param menu
	 * @return
	 */
	@Update("update tb_menu set menu_name=#{menu_name},url=#{url},parentID=#{parentId},icon=#{icon},statu=#{statu} where menu_id=#{menu_id}")
	public int upMenu(MenuBean menu);
	
	/**
	 * 查询所有父级菜单
	 * @return
	 */
	@Select("select * from tb_menu where parentID=0")
	public List<MenuBean> getParentMenu();
	
}
