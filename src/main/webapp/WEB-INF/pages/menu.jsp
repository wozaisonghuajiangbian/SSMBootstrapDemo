<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/core.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>
<script type="text/javascript" src="${path }/jsFiles/menu.js"></script>
<link href="${path }/static/js/plugins/switch/bootstrap-switch.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${path }/static/js/plugins/switch/bootstrap-switch.js"></script>
<style type="text/css">
 .modal {
     position: fixed;  
    top: 10%;
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
    <button onclick="addMenu()" type="button" class="btn btn-success">
      <span class="glyphicon glyphicon-plus" aria-hidden="true">添加</span>
    </button>
</div>

<!-- 模态框（Modal） -->
<!-- 添加菜单 -->
<div id="addDlg" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加菜单</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="addForm"  method="post">
			
			<div class="form-group">
			<label class="col-md-2 control-label">菜单名称：</label>
			<div class="col-md-3 ">
			<input type="text" id="menu_name" onblur="checkMenu()" name="menu_name" class="form-control form-control-static" placeholder="请输入菜单名称">
			</div>
			<label class="control-label"><span id="info" style="color:red"></span></label>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">请求地址：</label>
			<div class="col-md-3">
			<input type="text" id="url" name="url" class="form-control form-control-static" placeholder="请输入请求地址" >
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">父级菜单ID：</label>
			<div class="col-md-3">
			<select  id="parentId" name="parentId" class="form-control form-control-static"></select>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">图标样式：</label>
			<div class="col-md-3">
			<input type="text" id="icon" name="icon" class="form-control form-control-static" placeholder="请输入图标样式" >
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">状态：</label>
			<div class="col-md-3" class="form-control form-control-static">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="qid"  type="radio" checked name="statu" value="0">启用&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="jid" type="radio" name="statu" value="1">禁用
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
               <button type="button" onclick="saveMenu()" class="btn btn-primary">保存</button>
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
                <h4 class="modal-title" id="myModalLabel">修改菜单</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="myform"  method="post">
			<div class="form-group">
			<label class="col-md-2 control-label">菜单名称：</label>
			<div class="col-md-3 ">
			<input type="hidden" id="menu_id1" name="menu_id">
			<input type="text" id="menu_name1" onblur="checkMenu1()" name="menu_name" class="form-control form-control-static" placeholder="请输入菜单名称">
			</div>
			<label class="control-label"><span id="info1" style="color:red"></span></label>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">请求地址：</label>
			<div class="col-md-3">
			<input type="text" id="url1" name="url" class="form-control form-control-static" placeholder="请输入请求地址" >
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">父级菜单ID：</label>
			<div class="col-md-3">
			<select  id="parentId1" name="parentId" class="form-control form-control-static"></select>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">图标样式：</label>
			<div class="col-md-3">
			<input type="text" id="icon1" name="icon" class="form-control form-control-static" placeholder="请输入图标样式" >
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">状态：</label>
			<div class="col-md-3" class="form-control form-control-static">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="qid1"  type="radio" checked name="statu" value="0">启用&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="jid1" type="radio" name="statu" value="1">禁用
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closeDlg()">关闭</button>
               <button type="button" onclick="upMenu()" class="btn btn-primary">保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
</html>