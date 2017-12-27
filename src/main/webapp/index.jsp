<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
      <title>Title</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">

    <!-- Mainly scripts -->
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="${pageContext.request.contextPath}/static/js/inspinia.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/plugins/pace/pace.min.js"></script>
<script type="text/javascript" src="jsFiles/index.js"></script>

<link href="${pageContext.request.contextPath }/static/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/bootstrap-validator/bootstrapValidator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/bootstrap-validator/zh_CN.js"></script>
<link href="${pageContext.request.contextPath }/static/css/plugins/bootstrap-validator/bootstrapValidator.css" rel="stylesheet" type="text/css">
</head>
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
<body class="">
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element"> <span>
                        <c:if test=""></c:if>
                            <img alt="image" class="img-circle" src="${pageContext.request.contextPath}/static/img/profile_small.jpg" />
                             </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${user.username}</strong>
                             </span> <span class="text-muted text-xs block">${NAME } <b class="caret"></b></span> </span> </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="profile.html" id="">个人信息</a></li>
                            <li><a onclick="openPassDlg()">修改密码</a></li>
                            <li class="divider"></li>
                            <li><a onclick="quit()">安全退出</a></li>
                        </ul>
                    </div>
                    <div class="logo-element">
                        IN+
                    </div>
                </li>
                <c:forEach items="${mlist}" var="tree">
                <li>
                    <a onclick="getPage('${tree.url}','${tree.menu_name}')"><i class="${tree.icon}"></i> <span class="nav-label">${tree.menu_name}</span>
                        <c:if test="${!empty tree.children}">
                        <span class="fa arrow"></span>
                        </c:if>
                     </a>
                    <c:if test="${!empty tree.children}">
                    <ul class="nav nav-second-level collapse">
                        <c:forEach items="${tree.children}" var="menu">
                        <li><a onclick="getPage('${menu.url}','${menu.menu_name}')">
                        <i class="${menu.icon}"></i>
                        <span class="nav-label">${menu.menu_name}</span>
                        </a></li>
                        </c:forEach>
                    </ul>
                    </c:if>
                </li>
                </c:forEach>
                <!--下面两个菜单是固定死的  -->
                <li> <a onclick="openPassDlg()"><i class="fa fa-key"></i>
                 <span class="nav-label">修改密码</span>
                </a></li>
                <li> <a onclick="quit()"><i class="fa fa-sign-out"></i>
                 <span class="nav-label">退出登陆</span>
                </a></li>
            </ul>
        </div>
    </nav>
    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top  " role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                </div>
                <div class="nav navbar-top-links navbar-right">
                        <span class="m-r-sm text-muted welcome-message">
                        <iframe frameborder='0' scrolling='auto' src='include/time.html' style='padding:0px;width:100%;height:5%;' ></iframe>
                        </span>
				</div>
            </nav>
        </div>
        <div class="row wrapper border-bottom white-bg page-heading" id="title">
            <div class="col-sm-4">
                <h2></h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="index.html" id="index">首页</a>
                    </li>
                    <li class="active">
                        <span>Breadcrumb</span>
                    </li>
                    <li class="active">
                        <strong>Breadcrumb</strong>
                    </li>
                </ol>
            </div>
        </div>
        <div class="wrapper wrapper-content" id="content"></div>
        <div class="footer">
            <div class="pull-right">
                10GB of <strong>250GB</strong> Free.
            </div>
            <div>
                <strong>Copyright</strong> Example Company &copy; 2014-2017
            </div>
        </div>
    </div>
</div>


<!-- 模态框（Modal） -->
<!-- 修改 -->
<div id="passDlg" class="modal fade"  tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
            </div>
            <div class="container">
			<form class="form-horizontal" id="myform"  method="post">
			<div class="form-group">
			<label class="col-md-2 control-label">旧密码：</label>
			<div class="col-md-3 ">
			<input type="password" id="oldPass" name="oldPass" class="form-control form-control-static"  placeholder="请输入原始密码">
			<input  type="hidden" id="pass" value="${PASS }" name="password">
			<input  type="hidden" id="uid" value="${ID }" name="id">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">新密码：</label>
			<div class="col-md-3 ">
			<input type="password" id="newPass"  name="newPass" class="form-control form-control-static" placeholder="请输入新密码">
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-2 control-label">确认密码：</label>
			<div class="col-md-3">
			<input type="password" id="againPass"  name="againPass" class="form-control form-control-static" placeholder="请输入新密码">
			</div>
			</div>
            <div class="modal-footer col-md-6">
            <!--用来清空表单数据-->
            <input type="reset" name="reset" style="display: none;" />
                <button type="button" class="btn btn-default" onclick="closeDlgs()">关闭</button>
               <button type="button" onclick="upPass()" class="btn btn-primary">修改</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>
