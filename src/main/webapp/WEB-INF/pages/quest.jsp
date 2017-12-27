<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../include/core.jsp" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假请求</title>
<script type="text/javascript" src="${path }/jsFiles/quest.js"></script>
<style type="text/css">
 #addDlg,#mydlg {
     position: fixed;  
    top: 10%;
    left: 30%;
    width: 800px;
    height: 800px;
     margin: 100px 100 0 0px;/* margin 负值为宽高的一半 */ 
} 
</style>
</head>
<body>
<table id="test-table" class="table table-hover table-striped table-condensed table-bordered"></table>

<!--toolbar  -->
<div id="toolbar" class="btn-toolbar">
    <button onclick="addQuest()" type="button" class="btn btn-success">
      <span class="glyphicon glyphicon-plus" aria-hidden="true">添加</span>
    </button>
</div>


<!-- 模态框（Modal） -->
<!-- 填写请假单 -->
<div id="noteDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">请假单</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="noteForm"  method="post">
			
			<div class="form-group">
			<label class="col-md-2 control-label">请假类型：</label>
			<div class="col-md-3 ">
			<select name="type" class="form-control form-control-static" placeholder="请选择请假类型">
			<option value="公休" selected="selected">公休</option>
			<option value="病假">病假</option>
			<option value="婚假">婚假</option>
			<option value="年假">年假</option>
			<option value="其他" >其他</option>
			</select>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">请假原因：</label>
			<div class="col-md-3">
			<textarea rows="3" id="reason" name="reason" cols="32" class="form-control form-control-static" placeholder="请填写请假原因"></textarea>
			</div>
			</div>	
			
			<div class="form-group">
			<label class="col-md-2 control-label">开始时间：</label>
			<div class="col-md-3">
			<input type="text" name="stratTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="form-control form-control-static" placeholder="请选择开始时间">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">结束时间：</label>
			<div class="col-md-3">
			<input type="text" name="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class=" form-control form-control-static" placeholder="请选择结束时间">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">备注信息：</label>
			<div class="col-md-3">
			<textarea rows="3" id="reMark" name="reMark" cols="32" class="form-control form-control-static" placeholder="请填写备注信息"></textarea>
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
               <button type="button" onclick="saveNote()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="mydlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改角色</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="myform"  method="post">
			<div class="form-group">
			<label class="col-md-2 control-label">角色编号：</label>
			<div class="col-md-3 ">
			<input type="text" id="role_id1" name="role_id"  class="form-control form-control-static" readonly="readonly" placeholder="必填">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">角色名称：</label>
			<div class="col-md-3 ">
			<input type="text" id="role_name1" onblur="checkRole1()" name="role_name" class="form-control form-control-static" placeholder="必填">
			</div>
			<label class="control-label"><span id="infos" style="color:red"></span></label>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">上级角色：</label>
			<div class="col-md-3">
			<select id="pid1" name="pid" class="form-control form-control-static" ></select>
			</div>
			</div>
			
			
			<div class="form-group">
			<label class="col-md-2 control-label">部门描述：</label>
			<div class="col-md-3">
			<textarea rows="3" id="role_desc1" name="role_desc" cols="30" class="form-control form-control-static" placeholder="必填"></textarea>
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
               <button type="button" onclick="upRole()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<!-- 权限-->
<div id="authDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">分配权限</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="authForm"  method="post">
			
			<div class="form-group">
			<label class="col-md-2 control-label">角色权限：</label>
			<div class="col-md-3 ">
			<input type="hidden" id="rid" name="role_id">
			
			<div id="tree" ></div>
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
               <button type="button" onclick="saveAuth()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>