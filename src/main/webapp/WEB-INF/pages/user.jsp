<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${path }/jsFiles/user.js"></script>

<title>用户管理</title>
<style type="text/css">
 #roleDlg {
     position: fixed;  
    top: 15%;
    left: 30%;
    width: 800px;
    height: 800px;
     margin: 100px 100 0 0px;/* margin 负值为宽高的一半 */ 
}
#mydlg,#mydlg1{
    position: fixed;  
    top: 0%;
    left: 30%;
    width: 800px;
    height: 800px;
     margin: 100px 100 0 0px;/* margin 负值为宽高的一半 */ 
} 
</style>
</head>
<body>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">
			<strong>条件查询</strong>
		</h4>
	</div>
	<div class="panel-body">
		<form id="conForm" class=" form-inline">
  <div class="form-group">
    <div class="col-md-2 ">
    <input type="text" class="form-control" id="userId" name="userId" placeholder="请输入员工编号">
    </div>
  </div>
  <div class="form-group"  >
    <div class="col-md-2 ">
    <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入员工姓名">
    </div>
  </div>
  <div class="form-group">
    <div class="col-md-2 ">
    <select class="form-control form-control-static" name="deptId" id="deptId"></select>
    </div>
  </div>
  
  <button type="button" onclick="getUserByCon()" class="btn btn-info ">
   <span class="glyphicon glyphicon-search" aria-hidden="true" >  搜索</span></button>
</form>
	</div>
</div>
<table id="test-table" class="table table-hover table-striped table-condensed table-bordered"></table>

<!--toolbar  -->
<div id="toolbar" class="btn-toolbar">
    <button onclick="openDlg()" type="button" class="btn btn-success">
      <span class="glyphicon glyphicon-plus" aria-hidden="true" >添加</span>
    </button>
    <button onclick="ImportExcel()" type="button" class="btn btn-success">
      <span class="glyphicon glyphicon-copy" aria-hidden="true" >导入</span>
    </button>
    <button onclick="ExportExcel()" type="button" class="btn btn-success">
      <span class="glyphicon glyphicon-paste" aria-hidden="true" >导出</span>
    </button>
    <button  type="button" onclick="delMany()" class="btn btn-danger">
      <span class="glyphicon glyphicon-trash" aria-hidden="true">删除</span>
    </button>
</div>


<!-- 模态框（Modal） -->
<!-- 添加用户 -->
<div id="mydlg" class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
   
    <div class="modal-dialog">
        <div class="modal-content">
        
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加用户</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="myform"  method="post">
			<div class="form-group">
			<label class="col-md-2 control-label">登录账号：</label>
			<div class="col-md-3 ">
			<input type="text" onblur="validAccount()" id="user_account" name="user_account" class="form-control form-control-static" placeholder="请输入登陆账号">
			</div>
			<label class="control-label"><span id="mid" style="color:red"></span></label>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">用户姓名：</label>
			<div class="col-md-3 ">
			<input type="text" id="user_name"  name="user_name" class="form-control form-control-static" placeholder="请输入用户姓名">
			</div>
			</div>
			
			
			<div class="form-group">
			<label class="col-md-2 control-label">用户密码：</label>
			<div class="col-md-3 ">
			<input type="password" id="user_password" onblur="clearPass()" name="user_password" class="form-control form-control-static" placeholder="请输入密码">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">确认密码：</label>
			<div class="col-md-3 ">
			<input type="password" id="againpass" name="againpass" class="form-control form-control-static" placeholder="请输入密码">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">用户性别：</label>
			<div class="col-md-3">
			&nbsp;&nbsp;&nbsp;
			<input type="radio" id="nan" checked name="user_sex" value="男">男&nbsp;&nbsp;
			<input type="radio" id="nv" name="user_sex"  value="女">女
			</div>
			</div>
 
 			<div class="form-group">
			<label class="col-md-2 control-label">年龄：</label>
			<div class="col-md-3 ">
			<input type="text" id="user_age"  name="user_age" class="form-control form-control-static" placeholder="请输入年龄">
			</div>
			</div>
			
 			<div class="form-group">
			<label class="col-md-2 control-label">出生日期：</label>
			<div class="col-md-3 ">
			<input type="text"  id="user_birth"  onclick="WdatePicker()"  name="user_birth" class="form-control form-control-static" placeholder="请输入出生日期">
			</div>
			</div>
 
 			<div class="form-group">
			<label class="col-md-2 control-label">联系电话：</label>
			<div class="col-md-3 ">
			<input type="text" id="user_phone" name="user_phone" class="form-control form-control-static" placeholder="请输入联系电话">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">Email：</label>
			<div class="col-md-3 ">
			<input type="text" id="email" name="email" class="form-control form-control-static" placeholder="请输入Email">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">所属部门：</label>
			<div class="col-md-3 ">
			<select id="sid"  name="dept_id" class="form-control form-control-static" > </select>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">联系地址：</label>
			<div class="col-md-3">
			<textarea rows="3" id="user_address" name="user_address" cols="32" class="form-control form-control-static" placeholder="请输入联系地址"></textarea>
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
               <button type="button" onclick="saveUser()" class="btn btn-primary">提交</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div> 



<!-- 模态框（Modal） -->
<!-- 修改用户 -->
<div id="mydlg1" class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
   
    <div class="modal-dialog">
        <div class="modal-content">
        
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改用户</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="myform1"  method="post">
			
			<div class="form-group">
			<label class="col-md-2 control-label">用户编号：</label>
			<div class="col-md-3 ">
			<input type="text" id="user_id1" readonly="readonly"  name="user_id" class="form-control form-control-static" placeholder="请输入用户编号">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">登录账号：</label>
			<div class="col-md-3 ">
			<input type="text" disabled="disabled" id="user_account1" name="user_account" class="form-control form-control-static" placeholder="请输入登陆账号">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">用户姓名：</label>
			<div class="col-md-3 ">
			<input type="text" id="user_name1"  name="user_name" class="form-control form-control-static" placeholder="请输入用户姓名">
			</div>
			</div>
			
			
			<div class="form-group">
			<label class="col-md-2 control-label">用户密码：</label>
			<div class="col-md-3 ">
			<input type="text" id="user_password1"  disabled="disabled" name="user_password" class="form-control form-control-static" placeholder="请输入密码">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">用户性别：</label>
			<div class="col-md-3">
			&nbsp;&nbsp;&nbsp;
			<input type="radio" id="nan1" checked name="user_sex" value="男">男&nbsp;&nbsp;
			<input type="radio" id="nv1" name="user_sex"  value="女">女
			</div>
			</div>
 
 			<div class="form-group">
			<label class="col-md-2 control-label">年龄：</label>
			<div class="col-md-3 ">
			<input type="text" id="user_age1"  name="user_age" class="form-control form-control-static" placeholder="请输入年龄">
			</div>
			</div>
			
 			<div class="form-group">
			<label class="col-md-2 control-label">出生日期：</label>
			<div class="col-md-3 ">
			<input type="text"  id="user_birth1"  onclick="WdatePicker()"  name="user_birth" class="form-control form-control-static" placeholder="请输入出生日期">
			</div>
			</div>
 
 			<div class="form-group">
			<label class="col-md-2 control-label">联系电话：</label>
			<div class="col-md-3 ">
			<input type="text" id="user_phone1" name="user_phone" class="form-control form-control-static" placeholder="请输入联系电话">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">Email：</label>
			<div class="col-md-3 ">
			<input type="text" id="email1" name="email" class="form-control form-control-static" placeholder="请输入Email">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">所属部门：</label>
			<div class="col-md-3 ">
			<select id="sid1"  name="dept_id" class="form-control form-control-static" placeholder="请输入Email">
			
			</select>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">联系地址：</label>
			<div class="col-md-3">
			<textarea rows="3" id="user_address1" name="user_address" cols="32" class="form-control form-control-static" placeholder="请输入联系地址"></textarea>
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
               <button type="button" onclick="upUser()" class="btn btn-primary">提交</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div> 


<!-- 模态框（Modal） -->
<!-- 角色分配-->
<div id="roleDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">分配角色</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="roleForm"  method="post">
			
			<div class="form-group">
			<label class="col-md-2 control-label">我的角色：</label>
			<div class="col-md-3 ">
			<input type="hidden" id="hid" >
			<select  style= "width:280px" id="rid" name="role" multiple="multiple"  class="form-control form-control-static "></select>
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
               <button type="button" onclick="saveRole()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>



<!-- 模态框（Modal） -->
<!-- 导入-->
<div id="importDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">导入</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="importForm"  method="post" enctype="multipart/form-data">
			
			<div class="form-group">
			<label class="col-md-2 control-label">导入文件：</label>
			<div class="col-md-3 ">
			<input  type="file" name="files" class="form-control form-control-static ">
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
               <button type="button" onclick="importExcel()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


</body>
</html>