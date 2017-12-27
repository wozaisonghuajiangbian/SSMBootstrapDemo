package com.slcf.service;

import java.util.List;
import java.util.Map;

import com.slcf.pojo.DeptBean;

public interface DeptService {

	//添加部门信息
	public int insertDept(DeptBean dept,String name);
	
	//统计部门数量
	public int queryDeptCount();
	
	//查询所有
	public List<DeptBean> queryDeptList(int spage,int epage);
	
	//根据部门id查询
	public DeptBean queryDeptById(int id);
	
	//更新部门信息
	public int upDept(DeptBean dept,String name);
	
	//根据部门id删除部门表的信息
	public int delDept(int did);
	
	/**
	 * 根据部门名称或部门id验证是否唯一
	 * @param map
	 * @return
	 */
	public boolean validDept(Map<String,Object>map);
	
	/**
	 * 查询所以部门
	 * @return
	 */
	public List<DeptBean >getDeptList();
}
