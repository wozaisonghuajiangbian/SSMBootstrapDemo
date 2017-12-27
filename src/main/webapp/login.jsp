<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="static/js/jquery-3.1.1.min.js"></script>
	<script src="static/js/bootstrap.js"></script>
	<script type="text/javascript" src="static/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="static/js/plugins/bootstrap-validator/bootstrapValidator.js"></script>
	<script type="text/javascript" src="static/js/plugins/bootstrap-validator/zh_CN.js"></script>
	<link href="static/css/plugins/bootstrap-validator/bootstrapValidator.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="static/layer/layer.js"></script>
	<link href="static/layer/mobile/need/layer.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="jsFiles/login.js"></script>
	<style type="text/css">
	body{
	   background: url(images/a.jpg)repeat;
	}
	#login-box {
		/*border:1px solid #F00;*/
		padding: 35px;
		border-radius:15px;
		background: #56666B;
		color: #fff;
	}

	</style>
</head>
<body>
	<div class="container" id="top">
		<div class="row" style="margin-top: 400px; ">
			<div class="col-md-4"></div>
			<div class="col-md-4" id="login-box">
				<form class="form-horizontal" role="form" action="" id="form" method="post">
				  <div class="form-group">
				    <label for="firstname" class="col-sm-3 control-label">用户登录</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control"  placeholder="请输入账号" name="user_account">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="lastname" class="col-sm-3 control-label">密码</label>
				    <div class="col-sm-9">
				      <input type="password" class="form-control" placeholder="请输入密码" name="user_password">
				    </div>
				  </div>
				  <div class="form-group " style="margin-right: 15px;">
				  <label for="lastname" class="col-sm-4 control-label"></label>
				    <div class="col-sm-4">
				      <button type="button" onclick="doLogin()" class="btn btn-primary btn-info">登录</button>
				    </div>
				    <div class="col-sm-4">
				      <button type="button" onclick="location.reload()" class="btn btn-warning btn-info">重置</button>
				    </div>
				  </div>
				</form>
			</div>
		</div>		
	</div>
	
	
	<!-- 模态框（Modal） -->
<!-- 添加 -->
<div id="mydlg" class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
   
    <div class="modal-dialog">
        <div class="modal-content">
        
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">账号注册</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="myform"  method="post">
			<div class="form-group">
			<label class="col-md-2 control-label">登录账号：</label>
			<div class="col-md-3 ">
			<input type="text" id="user_account" name="user_account" class="form-control form-control-static" placeholder="请输入账号">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">登录密码：</label>
			<div class="col-md-3 ">
			<input type="text" id="user_password" name="user_password" class="form-control form-control-static" placeholder="请输入密码">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">确认密码：</label>
			<div class="col-md-3 ">
			<input type="text" id="againpass" name="againpass" class="form-control form-control-static" placeholder="请输入密码">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">姓名：</label>
			<div class="col-md-3 ">
			<input type="text" id="user_name" name="user_name" class="form-control form-control-static" placeholder="请输入姓名">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">性别：</label>
			<div class="col-md-3">
			&nbsp;&nbsp;&nbsp;<input type="radio"  checked name="user_sex" value="男">男&nbsp;&nbsp;
			<input type="radio" name="user_sex"  value="女">女
			</div>
			</div>
 
 			<div class="form-group">
			<label class="col-md-2 control-label">入职日期：</label>
			<div class="col-md-3 ">
			<input type="text" id="user_birth" onclick="WdatePicker()" readonly="readonly" name="user_birth" class="form-control form-control-static" placeholder="请输入入职日期">
			</div>
			</div>
 
 			<div class="form-group">
			<label class="col-md-2 control-label">电话：</label>
			<div class="col-md-3 ">
			<input type="text" id="user_phone" name="user_phone" class="form-control form-control-static" placeholder="请输入电话">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">地址：</label>
			<div class="col-md-3">
			<textarea rows="3" id="address" name="address" cols="36" placeholder="请输入地址"></textarea>
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closedlg()">关闭</button>
               <button type="button" onclick="regUser()" class="btn btn-primary">提交</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div> 
</body>
</html>