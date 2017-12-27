package com.slcf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.slcf.pojo.DeptBean;
import com.slcf.pojo.DeptOpt;

@Repository
public interface DeptDao {

	/**
	 *根据部门名称或部门id验证部门名称是否唯一 
	 * @param map
	 * @return
	 */
	public List<DeptBean> getDeptByCon(Map<String,Object>map);
	
	/**
	 * 添加部门
	 * @param dept
	 * @return
	 */
	@Insert("insert into tb_dept(dept_name,dept_desc) values(#{dept_name},#{dept_desc})")
	public int insertDept(DeptBean dept);
	
	
	//分页查询所以部门
	@Select("select * from tb_dept ORDER BY dept_id desc LIMIT #{spage},#{epage}")
	public List<DeptBean> queryDeptList(@Param("spage")int spage,@Param("epage")int epage);
	
	//统计所以部门数量
	@Select("select count(*) from tb_dept")
	public int getDeptCount();
	
	/**
	 * 查询所有部门
	 * @return
	 */
	@Select("select * from tb_dept order by dept_id")
	public List<DeptBean> getDeptList();
	
	/**
	 * 根据部门id查询部门信息
	 * @param id
	 * @return
	 */
	@Select("select * from tb_dept where dept_id=#{id}")
	public DeptBean getDeptById(int id);
	
	//更新
	@Update("update tb_dept set dept_name=#{dept_name},dept_desc=#{dept_desc} where dept_id=#{dept_id}")
	public int upDept(DeptBean dept);
	
	//根据部门id删除部门表的信息
	@Delete("delete from tb_dept where dept_id=#{id}")
	public int delDeptById(int id);
	
	/**
	 * 添加部门操作记录表的信息
	 * @param did  部门id
	 * @param dname 操作人名字
	 * @param dtype 操作类型
	 * @return
	 */
	@Insert("insert into tb_dept_opt(d_id,dopt_name,dopt_type,dopt_time) values(#{did},#{dname},#{dtype},sysdate())")
	public int insertDeptOpt(@Param("did")int did,@Param("dname")String dname,@Param("dtype")String dtype);
	
	//根据部门id删除部门操作记录表信息
	@Delete("delete from tb_dept_opt where d_id=#{id}")
	public int delDeptOpt(int id);
	
	/**
	 * 根据部门id查询部门操作表
	 * @param id
	 * @return
	 */
	@Select("select * from tb_dept_opt where d_id=#{id}")
	public List<DeptOpt> getOptList(int id);
}
