<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门管理</title>

<script type="text/javascript" src="${path }/jsFiles/dept.js"></script>
<style type="text/css">
 .modal {
     position: fixed;  
    top: 12%;
    left: 30%;
    width: 800px;
    height: 800px;
     margin: 100px 100 0 0px;/* margin 负值为宽高的一半 */ */
} 
</style>
</head>
<body>
<table id="test-table" class="table table-hover table-striped table-condensed table-bordered"></table>

<!--toolbar  -->
<div id="toolbar" class="btn-toolbar">
    <button onclick="addDept()" type="button" class="btn btn-success">
      <span class="glyphicon glyphicon-plus" aria-hidden="true">添加</span>
    </button>
</div>


<!-- 模态框（Modal） -->
<!-- 添加部门 -->
<div id="addDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加部门</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="addForm"  method="post">
			
			<div class="form-group">
			<label class="col-md-2 control-label">部门名称：</label>
			<div class="col-md-3 ">
			<input type="text" id="dept_name1" name="dept_name" class="form-control form-control-static" placeholder="必填">
			<span id="info" style="color:red"></span>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">部门描述：</label>
			<div class="col-md-3">
			<textarea rows="3" id="dept_desc1" name="dept_desc" cols="30" class="form-control form-control-static" placeholder="必填"></textarea>
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closedlg()">关闭</button>
               <button type="button" onclick="insertDept()" class="btn btn-primary">保存</button>
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
                <h4 class="modal-title" id="myModalLabel">修改部门</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="myform"  method="post">
			<div class="form-group">
			<label class="col-md-2 control-label">部门编号：</label>
			<div class="col-md-3 ">
			<input type="text" id="dept_id" name="dept_id" class="form-control form-control-static" readonly="readonly" placeholder="必填">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">部门名称：</label>
			<div class="col-md-3 ">
			<input type="text" id="dept_name"  name="dept_name" class="form-control form-control-static" placeholder="必填">
			<span id="infos" style="color:red"></span>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">部门描述：</label>
			<div class="col-md-3">
			<textarea rows="3" id="dept_desc" name="dept_desc" cols="30" class="form-control form-control-static" placeholder="必填"></textarea>
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closedlg()">关闭</button>
               <button type="button" onclick="upDept()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>