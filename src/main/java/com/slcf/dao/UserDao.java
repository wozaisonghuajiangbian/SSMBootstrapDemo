package com.slcf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.slcf.pojo.UserBean;

@Repository
public interface UserDao {

	/**
	 * 分页查询所以user
	 * @param spage
	 * @param epage
	 * @return
	 */
	@Select("select u.*,d.dept_name from tb_user u LEFT JOIN   tb_dept d ON d.dept_id=u.dept_id ORDER BY user_id desc limit #{spage},#{epage}")
	public List<UserBean> getUserList(@Param("spage")int spage,@Param("epage")int epage);
	
	/**
	 * 统计user表总数量
	 * @return
	 */
	@Select("select count(*) from tb_user u  ")
	public int getUserCount();
	
	/**
	 * 修改密码
	 * @param id
	 * @param pass
	 * @return
	 */
	@Update("update tb_user set user_password=#{pass} where user_id=#{id}")
	public int upPass(@Param("id")int id,@Param("pass")String pass);
	
	/**
	 * 添加用户操作表
	 * @param uid
	 * @param type
	 * @param name
	 * @return
	 */
	@Insert("insert into tb_user_opt(u_id,uopt_name,uopt_type,uopt_time) values(#{uid},#{name},#{type},sysdate())")
	public int insertUserOpt(@Param("uid")int uid,@Param("type")String type,@Param("name")String name);
	
	/**
	 * 根据用户登录账号查询
	 * @param account
	 * @return
	 */
	@Select("select * from tb_user where user_account = #{account}")
	public List<UserBean> getUserByAccount(String account);
	
	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	@Insert("insert into tb_user(dept_id,user_account,user_password,user_name,user_age,user_sex,user_address,user_birth,user_phone,email,reg_time)"
			+ " values(#{dept_id},#{user_account},#{user_password},#{user_name},#{user_age},#{user_sex},#{user_address},#{user_birth},#{user_phone},#{email},sysdate())")
	public int saveUser(UserBean user);
	
	/**
	 * 添加用户角色表 
	 * @param uid
	 * @return
	 */
	@Insert("insert into tb_user_role(user_id,role_id)values(#{uid},#{rid})")
	public int saveUserRole(@Param("uid")int uid,@Param("rid")int rid);
	
	/**
	 * 删除用户角色表
	 * @param uid
	 * @return
	 */
	@Delete("delete from tb_user_role where user_id =#{uid}")
	public int delUserRoleByUid(int uid);
	
	/**
	 * 删除用户操作表
	 * @param uid
	 * @return
	 */
	@Delete("delete from tb_user_opt where u_id=#{uid}")
	public int delUserOpt(int uid);
	
	/**
	 * 删除用户表
	 * @param id
	 * @return
	 */
	@Delete("delete from tb_user where user_id=#{uid}")
	public int delUser(int id);
	
	/**
	 * 根据用户id查询用户信息
	 * @param id
	 * @return
	 */
	@Select("select * from tb_user where user_id=#{id}")
	public UserBean queryUserById(int id);
	
	@Update("update tb_user set dept_id=#{dept_id},user_name=#{user_name},user_age=#{user_age},user_sex=#{user_sex},"
			+ "user_address=#{user_address},user_birth=#{user_birth},user_phone=#{user_phone},email=#{email} where user_id=#{user_id}")
	public int upUser(UserBean user);
	
	/**
	 * 查询用户拥有的角色
	 * @param uid
	 * @return
	 */
	@Select("SELECT ur.role_id from tb_user_role ur where ur.user_id=#{uid}")
	public List<Integer> getUserRole(int uid);
	
	/**
	 * 查询所有用户信息以及其对应的部门
	 * @return
	 */
	@Select("select u.*,d.dept_name from tb_user u,tb_dept d where d.dept_id =u.dept_id")
	public List<UserBean> queryUserList();
	
	/**
	 * 条件查询用户
	 * @param userId
	 * @param userName
	 * @param deptId
	 * @return
	 */
	public List<UserBean> getUserListByCon(@Param("spage")int spage,@Param("epage")int epage,@Param("userId")Integer userId,@Param("userName")String userName,@Param("deptId")Integer deptId);

	/**
	 * 根据条件统计数量
	 * @param userId
	 * @param userName
	 * @param deptId
	 * @return
	 */
	public int getContByCon(@Param("userId")Integer userId,@Param("userName")String userName,@Param("deptId")Integer deptId);

	
	/**
	 * 表格男数据
	 * @return
	 */
	@Select("select DATE_FORMAT(reg_time,'%Y-%m') mon,count(*) num from tb_user where  reg_time "
			+ " between str_to_date('2017-01-01','%Y-%m-%d')  "
			+ "and str_to_date('2017-12-31','%Y-%m-%d')  and user_sex='男' "
			+ "group by DATE_FORMAT(reg_time,'%Y-%m') order by DATE_FORMAT(reg_time,'%Y-%m')")
	public List<UserBean> getUserCharNan();
	
	/**
	 * 表格女数据
	 * @return
	 */
	@Select("select DATE_FORMAT(reg_time,'%Y-%m') mon,count(*) num from tb_user where "
			+ " reg_time  between str_to_date('2017-01-01','%Y-%m-%d')  "
			+ "and str_to_date('2017-12-31','%Y-%m-%d')  and user_sex='女' "
			+ "group by DATE_FORMAT(reg_time,'%Y-%m') order by DATE_FORMAT(reg_time,'%Y-%m')")
	public List<UserBean> getUserCharNv();
}
