package com.slcf.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.slcf.dao.UserDao;
import com.slcf.pojo.UserBean;
import com.slcf.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	UserDao userDao;
	
	/**
	 * 分页查询所以user信息
	 * @param spage
	 * @param epage
	 * @return
	 */
	public Map<String,Object> getUserList(int pageNumber, int pageSize) {
		Map<String,Object>map=new HashMap<String, Object>();		
		int count=userDao.getUserCount();
		List<UserBean>ulist=userDao.getUserList((pageNumber-1)*pageSize, pageNumber*pageSize);
		map.put("total", count);
		map.put("rows", ulist);
		return map;
	}

	/**
	 * 修改用户密码
	 */
	public int upPass(int uid, String pass,HttpServletRequest request) {
		int j=0;
		int i=userDao.upPass(uid, pass);
		String name=(String)request.getSession().getAttribute("NAME");
		if(i>0){
			j=userDao.insertUserOpt(uid, "修改", name);
		}
		return j;
	}

	/**
	 * 验证用户登录账号是否唯一
	 */
	public boolean validUserAccount(String account, int uid) {
		boolean flag=true;
		List<UserBean>ulist=userDao.getUserByAccount(account);
		if(uid!=0){//修改
			if(ulist.size()==1&&ulist.get(0).getUser_id()==uid){
				flag=true;
			}else if(ulist.size()==0){
				flag=true;
			}else{
				flag=false;
			}
		}else{//添加
			if(ulist.size()>0){
				flag=false;
			}
		}
		return flag;
	}

	/**
	 * 添加用户信息
	 */
	public int saveUser(UserBean user,HttpServletRequest request) {
		String name=(String)request.getSession().getAttribute("NAME");
		int j=0;
		int i=userDao.saveUser(user);
		if(i>0){
			List<UserBean>list=userDao.getUserByAccount(user.getUser_account());
			j=userDao.insertUserOpt(list.get(0).getUser_id(), "添加", name);
		}
		return j;
	}

	/**
	 * 删除用户
	 */
	public int delUser(int uid) {
		int j=0;
		int i=userDao.delUserOpt(uid);
		if(i>0){
			j=userDao.delUser(uid);
		}
			return j;
	}

	/**
	 * 根据用户id查询用户信息
	 * @param id
	 * @return
	 */
	public UserBean queryUserById(int id) {
		UserBean user=userDao.queryUserById(id);
		return user;
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public int upUser(UserBean user,HttpServletRequest request) {
		String name=(String)request.getSession().getAttribute("NAME");
		int j=0;
		int i=userDao.upUser(user);
		if(i>0){
			j=userDao.insertUserOpt(user.getUser_id(), "修改", name);
		}
		return j;
	}

	/**
	 * 批量删除用户
	 */
	public int delUsers(int[] ids) {
		int sum=0;
		int y=0;
		for(int i=0;i<ids.length;i++){
			int j=userDao.delUserOpt(ids[i]);
			if(j>0){
				int x=userDao.delUser(ids[i]);
				sum+=x;
			}
		}
		if(sum==ids.length){
			y=1;
		}
		return y;
	}

	public List<Integer> getUserRole(int uid) {
		List<Integer> list=userDao.getUserRole(uid);
		return list;
	}

	
	public boolean saveUserRole(int uid, String ridStr) {
		boolean flag=false;
		int sum=0;
		
		int x=userDao.delUserRoleByUid(uid);
		System.out.println(ridStr.contains(",")+"--------lllllllllllll");
		if(x>0){
			if(ridStr.contains(",")){
				String idStrs[]=ridStr.split(",");
				int ids[]=new int[idStrs.length];
				for(int i=0;i<idStrs.length;i++){
					ids[i]=Integer.parseInt(idStrs[i]);
				}
				for(Integer s:ids){
					int j=userDao.saveUserRole(uid, s);
					sum+=j;
				}
				if(sum==ids.length){
					flag=true;
				}
			}else{
				int y=userDao.saveUserRole(uid, Integer.parseInt(ridStr));
				if(y>0){
					flag=true;
				}
			}
		}
		return flag;
	}

	public void ExportUserList(HttpServletResponse response) {

		//HSSFWorkbook对象(excel的文档对象)  
		HSSFWorkbook workBook=new HSSFWorkbook();
		
		//sheet对象（excel的表单）
		HSSFSheet sheet=workBook.createSheet("用户表信息");
		
		//行数，参数为行索引(excel的行)
		HSSFRow rowHead=sheet.createRow(0);//第一行  可以是0～65535之间的任何一个
		
		//创建excel的单元格，参数为列索引，可以是0～255之间的任何一个 
		HSSFCell cellOne=rowHead.createCell(0);
		cellOne.setCellValue("用户信息");//表头
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
		
		HSSFRow rows=sheet.createRow(1);//第二行
		//创建单元格并设置单元格内容
		rows.createCell(0).setCellValue("用户编号");
		rows.createCell(1).setCellValue("登陆账号");
		rows.createCell(2).setCellValue("用户姓名");
		rows.createCell(3).setCellValue("用户年龄");
		rows.createCell(4).setCellValue("用户性别");
		rows.createCell(5).setCellValue("用户地址");
		rows.createCell(6).setCellValue("出生日期");
		rows.createCell(7).setCellValue("Email");
		rows.createCell(8).setCellValue("联系电话");
		rows.createCell(9).setCellValue("所属部门");
	
		List<UserBean>userList=userDao.queryUserList();
		for(int i=0;i<userList.size();i++){
			HSSFRow row=sheet.createRow(i+2);//从第三行开始
			//创建单元格并设置单元格内容
			row.createCell(0).setCellValue(userList.get(i).getUser_id());
			row.createCell(1).setCellValue(userList.get(i).getUser_account());
			row.createCell(2).setCellValue(userList.get(i).getUser_name());
			row.createCell(3).setCellValue(userList.get(i).getUser_age());
			row.createCell(4).setCellValue(userList.get(i).getUser_sex());
			row.createCell(5).setCellValue(userList.get(i).getUser_address());
			row.createCell(6).setCellValue(userList.get(i).getUser_birth());
			row.createCell(7).setCellValue(userList.get(i).getEmail());
			row.createCell(8).setCellValue(userList.get(i).getUser_phone());
			row.createCell(9).setCellValue(userList.get(i).getDept_name());
		}
		//输出Excel文件  
	    try {
			OutputStream output=response.getOutputStream();
			response.reset();  
		    response.setHeader("Content-disposition", "attachment; filename=userinfo.xls");  
		    response.setContentType("application/msexcel");          
		    workBook.write(output);  
		    output.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	     
	}

	public Map<String,Object> importExcel(MultipartFile file) {
		List<UserBean>userList=new ArrayList<UserBean>();
		Map<String,Object>map=new HashMap<String, Object>();
		int x=0,y=0,sum=0;
		boolean flag=false;
		if(file!=null){
			//根据指定的文件输入流导入Excel从而产生Workbook对象  
			try {
				Workbook workBook=new HSSFWorkbook(file.getInputStream());
				//获取Excel文档中的第一个表单
				Sheet sheet=workBook.getSheetAt(0);
				//对Sheet中的每一行进行迭代  
	    		for (Row r : sheet) {  
	    		//如果当前行的行号（从0开始）未达到2（第三行）则从新循环  
	    		if(r.getRowNum()<2){  
	    		      continue;  
	    		     } 
	    		
	    		UserBean user=new UserBean();
	    		user.setUser_account(r.getCell(0).getStringCellValue());
	    		user.setUser_name(r.getCell(1).getStringCellValue());
	    		user.setUser_password(r.getCell(2).getStringCellValue());
	    		user.setUser_address(r.getCell(3).getStringCellValue());
	    		user.setUser_phone(r.getCell(4).getStringCellValue());
	    		userList.add(user);
	    		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			y=userList.size();
			for(UserBean u:userList){
				List<UserBean> user=userDao.getUserByAccount(u.getUser_account());
				if(user!=null&&user.size()>0){
					x++;
				}else{
					int i=userDao.saveUser(u);
					sum+=i;
				}
			}
			if(sum==(y-x)){
				flag=true;
			}
		}
		map.put("msg", "共有"+y+"条数据,成功导入"+sum+"条数据,登陆账号重名的数据有"+x+"条");
		map.put("flag", flag);
		return map;
	}

	public Map<String, Object> getUserListByCon(int spage, int epage, int userId, String userName, int deptId) {
		List<UserBean> userList=userDao.getUserListByCon(spage, epage, userId, userName, deptId);
		int count=userDao.getContByCon(userId, userName, deptId);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("rows", userList);
		map.put("total", count);
		return map;
	}

	public Map<String, Object> getHightsInfo() {
		Map<String,Object> map=new HashMap<String, Object>();
		List<UserBean> nanlist=userDao.getUserCharNan();
		List<UserBean> nvList=userDao.getUserCharNv();
		int naninfo[]=new int[12];
    	int nvinfo[]=new int[12];
    		//男
    		for(UserBean u:nanlist){//数据库月份信息
        		String str[]=u.getMon().split("-");
        		int j=Integer.parseInt(str[1]);
        		for(int i=1;i<=12;i++){
        		if(j==i){
        			naninfo[i-1]=u.getNum();
        		}
        	}
    		}
    		
    		for(UserBean u:nvList){//数据库月份信息
        		String str[]=u.getMon().split("-");
        		int j=Integer.parseInt(str[1]);
        		for(int i=1;i<=12;i++){
        		if(j==i){
        			nvinfo[i-1]=u.getNum();
        		}
        	}
    		}
    	map.put("naninfo", naninfo);
    	map.put("nvinfo", nvinfo);
		return map;
	}


	
	
}
