/**
 * 用户
 */
$(function (){
	getDeptLists();
	
	//select2 多选
    $("#rid").select2({
    	//minimumInputLength: 1, 
        language: "zh-CN", //设置 提示语言
        maximumSelectionLength: 3,  //设置最多可以选择多少项
         //width: "100%", //设置下拉框的宽度
         placeholder: "请选择",
         tags: true,
    });
    
    
	$("#test-table").bootstrapTable('destroy');
	$('#test-table').bootstrapTable({
		method : 'GET', //默认是post,不允许对静态文件访问
		url: "user/userList.action",
		cache : false,
		striped : true,// 隔行加亮
		pagination : true, //开启分页功能    在表格底部显示分页工具栏
		pageSize : 5, //默认每页条数
		pageNumber : 1, //默认分页
		pageList : [ 10, 20, 50, 100, 200, 500 ],//分页数
		showColumns : true, //显示隐藏列
		showRefresh : false, //显示刷新按钮
		toolbar:"#toolbar",
		singleselect : true,
		minimumCountColumns: 2,// 设置最少显示列个数
        clickToSelect: true, // 单击行即可以选中
		search : false,//显示搜素表单
		silent : true, //刷新事件必须设置
		sidePagination : "server", //表示服务端请求  
		columns : [ {
			checkbox:true
		}   ,{
			field : "user_id",
			title : "员工编号",
			class : 'col-md-1',
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "user_account",
			title : "登陆账号",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "user_name",
			title : "姓名",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "user_password",
			title : "密码",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "user_age",
			title : "年龄",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "user_sex",
			title : "性别",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "dept_name",
			title : "所在部门",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "user_address",
			title : "地址",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "birthStr",
			title : "出生日期",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "user_phone",
			title : "联系电话",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
            field: 'operate',
            title: '操作',
           class : 'col-md-2',
            align: 'center',
            valign: 'middle',
           formatter: operateFormatter,
        }],
		queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                // searchText: params.searchText
            };
            return param;
        },
		formatLoadingMessage : function() {
			return "请稍等，正在加载中...";
		},

		formatNoMatches : function() {
			return '无符合条件的记录';
		}
	});
});

function operateFormatter(value, row, index) {
    return ['<button type="button" class=" btn btn-primary" onclick="getRole('+row.user_id+')">角色</button>&nbsp;&nbsp;&nbsp;',
        '<button type="button" class=" btn btn-info" onclick="getValue('+row.user_id+')">修改</button>',
        '&nbsp;&nbsp;&nbsp;<button class=" btn btn-danger" type="button" onclick="delUser('+row.user_id+')">删除</button>'
        ].join('');
}
//条件查询
	function getUserByCon(){
		 
		$("#test-table").bootstrapTable('destroy');
		$('#test-table').bootstrapTable({
			method : 'GET', //默认是post,不允许对静态文件访问
			url: "user/getUserByCon.action",
			cache : false,
			striped : true,// 隔行加亮
			pagination : true, //开启分页功能    在表格底部显示分页工具栏
			pageSize : 5, //默认每页条数
			pageNumber : 1, //默认分页
			pageList : [ 10, 20, 50, 100, 200, 500 ],//分页数
			showColumns : true, //显示隐藏列
			showRefresh : false, //显示刷新按钮
			toolbar:"#toolbar",
			singleselect : true,
			minimumCountColumns: 2,// 设置最少显示列个数
	        clickToSelect: true, // 单击行即可以选中
			search : false,//显示搜素表单
			silent : true, //刷新事件必须设置
			sidePagination : "server", //表示服务端请求  
			columns : [ {
				checkbox:true
			}   ,{
				field : "user_id",
				title : "员工编号",
				class : 'col-md-1',
				align : "center",
				valign : "middle",
				sortable : "true"
			}, {
				field : "user_account",
				title : "登陆账号",
				align : "center",
				valign : "middle",
				sortable : "true"
			}, {
				field : "user_name",
				title : "姓名",
				align : "center",
				valign : "middle",
				sortable : "true"
			}, {
				field : "user_password",
				title : "密码",
				align : "center",
				valign : "middle",
				sortable : "true"
			}, {
				field : "user_age",
				title : "年龄",
				align : "center",
				valign : "middle",
				sortable : "true"
			}, {
				field : "user_sex",
				title : "性别",
				align : "center",
				valign : "middle",
				sortable : "true"
			}, {
				field : "dept_name",
				title : "所在部门",
				align : "center",
				valign : "middle",
				sortable : "true"
			}, {
				field : "user_address",
				title : "地址",
				align : "center",
				valign : "middle",
				sortable : "true"
			}, {
				field : "birthStr",
				title : "出生日期",
				align : "center",
				valign : "middle",
				sortable : "true"
			}, {
				field : "user_phone",
				title : "联系电话",
				align : "center",
				valign : "middle",
				sortable : "true"
			}, {
	            field: 'operate',
	            title: '操作',
	           class : 'col-md-2',
	            align: 'center',
	            valign: 'middle',
	           formatter: operateFormatter,
	        }],
			queryParamsType: "undefined",
	        queryParams: function queryParams(params) {   //设置查询参数
	            var param = {
	                pageNumber: params.pageNumber,
	                pageSize: params.pageSize,
	                userId:$("#userId").val(),
	                userName:$("#userName").val(),
	                deptId:$("#deptId").val()
	                // searchText: params.searchText
	            };
	            return param;
	        },
			formatLoadingMessage : function() {
				return "请稍等，正在加载中...";
			},
	
			formatNoMatches : function() {
				return '无符合条件的记录';
			}
		});
}


//查询所有部门 追加到 条件查询中
function getDeptLists(){
	$.ajax({
		url:'dept/getDeptList.action',
		dataType:'json',
		type:'post',
		success:function(data){
			$("#deptId").empty();
			$("#deptId").append("<option value='0'>请选择所属部门</option>");
			$.each(data,function(){
				$("#deptId").append("<option value='"+this.dept_id+"'>"+this.dept_name+"</option>");
			});
		},
		error:function(){
			alert("请求失败!");
		}
	});
}

//打开  导入 
function ImportExcel(){
	$("#importDlg").modal('show');
}
//导入
function importExcel(){
	if($("#importForm").data('bootstrapValidator').validate().isValid()){
		$.ajax({
			url:'user/import.action',
			dataType:'json',
			type:'post',
			data:new FormData($("#importForm")[0]),
			contentType: false, //必须false才会避开jQuery对 formdata 的默认处理 
            processData: false, //必须false才会自动加上正确的Content-Type
			success:function(data){
				if(data.flag){
					alert("导入成功！"+data.msg);
				}else{
					alert("导入失败！"+data.msg);
				}
				closeDlg();
				$("#test-table").bootstrapTable('refresh');
			},
			error:function(){
				alert("请求失败！");
			}
		});
	}else{
		return false;
	}
}

//导出
function ExportExcel(){
	if(confirm("您确定要导出所有用户信息吗？")){
		location.href="user/export.action";
	}else{
		return false;
	}
}


//角色分配
function getRole(uid){
	$("#hid").val(uid);
	$.ajax({
		url:'user/getRole.action',
		dataType:'json',
		type:'post',
		traditional:true,
		data:{
			uid:uid
		},
		success:function(data){
			$("#rid").empty();
			$.each(data.role,function(index,items){
				$("#rid").append("<option value='"+items.role_id+"'>"+items.role_name+"</option>");
			});
			if((data.userRole!=null)){
				$.each(data.userRole,function(index,items){
					$("#rid").val(data.userRole).trigger("change");//select2 选中
				});
			}else{
				$("#rid").val(0).trigger("change");
			}
		},
		error:function(){
			alert("请求失败！");
		}
	})
	$("#roleDlg").modal('show');
}
//保存修改角色
function saveRole(){
	var uid=$("#hid").val();
	//var rid=$("#rid option:selected").val();
	var rids=$("#rid").val();//select2 获取多选值
	$.ajax({
		url:'user/upUserRple.action',
		dataType:'json',
		type:'post',
		traditional:true,
		data:{
			uid:uid,
			rid:rids
		},
		success:function(data){
			if(data){
				alert("保存成功！");
			}else{
				alert("保存失败！");
			}
			closeDlg();
			$('#test-table').bootstrapTable('refresh'); 
		}
	})
};

//批量删除
function delMany(){
	var idstr=[];
	//获取选中的行
	var a= $('#test-table').bootstrapTable('getSelections');  
	if(a.length>0){  
		if(confirm("您确定要删除选中的数据吗？")){
			$.each(a,function(index){
				idstr.push(a[index].user_id);
			});
			$.ajax({
				url:'user/delUsers.action',
				dataType:'json',
				type:'post',
				traditional:true,
				data:{
					ids:idstr
				},
				success:function(data){
					if(data>0){
						alert("删除成功！");
					}else{
						alert("删除失败！");
					}
					$('#test-table').bootstrapTable('refresh'); 
				},
				error:function(){
					alert("请求失败！");
				}
			});
		}else{
			return false;
		}
	}else{
		alert("请至少选中一行数据");
	}
}
//修改前，打开模态框
function getValue(id){
	$.ajax({
		url:'user/getUserById.action',
		dataType:'json',
		type:'post',
		data:{
			uid:id
		},
		success:function(data){
			$("#user_id1").val(data.user.user_id);
			$("#user_account1").val(data.user.user_account);
			$("#user_password1").val(data.user.user_password);
			$("#user_name1").val(data.user.user_name);
			$("#user_age1").val(data.user.user_age);
			$("#user_address1").val(data.user.user_address);
			$("#user_birth1").val(data.user.birthStr);
			$("#email1").val(data.user.email);
			$("#user_phone1").val(data.user.user_phone);
			
			if("男"==data.user.user_sex){
				$("#nan1").prop('checked',true);
			}else{
				$("#nv1").prop('checked',true);
			}
			
			$("#sid1").empty();
			$("#sid1").append("<option value='0'>请选择</option>");
			$.each(data.dept,function(){
				if(data.user.dept_id==this.dept_id){
					$("#sid1").append("<option selected value='"+this.dept_id+"'>"+this.dept_name+"</option>");
				}else{
					$("#sid1").append("<option value='"+this.dept_id+"'>"+this.dept_name+"</option>");
				}
			});
			$("#mydlg1").modal('show');
		},
		error:function(){
			alert('请求失败！');
		}
	});
}
//修改用户
function upUser(){
		if($("#myform1").data("bootstrapValidator").validate().isValid()){
			$.ajax({
				url:'user/upUser.action',
				dataType:'json',
				type:'post',
				data:$("#myform1").serialize(),
				success:function(data){
					if(data>0){
						alert("修改成功！");
					}else{
						alert("修改失败！");
					}
					closeDlg();
					$("#test-table").bootstrapTable('refresh');
				}
			});
		}else{
			alert("请按规则填写信息");
		}
}

//删除员工
function delUser(id){
	if(confirm("您确定要删除这条数据吗?")){
		$.ajax({
			url:'user/delUser.action',
			dataType:'json',
			type:'post',
			data:{uid:id},
			success:function(data){
				if(data>0){
					alert("删除成功！");
				}else{
					alert("删除失败！");
				}
				$("#test-table").bootstrapTable('refresh');
			},
			error:function(){
				alert("请求失败！");
			}
		});
	}
}
//添加，打开模态框
function openDlg(){
	getDeptList();
	$("#mydlg").modal('show');
};

//关闭模态框
function closeDlg(){
	$("#importDlg").modal('hide');
	$("#roleDlg").modal('hide');
	$("#mydlg1").modal('hide');
	$("#mydlg").modal('hide');
	$("#mid").text(null);
	$("input[type=reset]").trigger("click");
	$('#importForm').data('bootstrapValidator', null);
	$('#myform').data('bootstrapValidator', null);
	$('#myform1').data('bootstrapValidator', null);
	formValidator();
};

//添加用户
function saveUser(){
	var msg=$("#mid").text();
	if(msg=="此账号可用"){
		if($("#myform").data('bootstrapValidator').validate().isValid()){
			$.ajax({
				url:'user/saveUser.action',
				type:'post',
				dataType:'json',
				data:$("#myform").serialize(),
				success:function(data){
					if(data>0){
						alert("添加成功！");
					}else{
						alert("添加失败！");
					}
					$("#test-table").bootstrapTable('refresh');
					closeDlg();
				},
				error:function(){
					alert("请求失败");
				}
			});
		}else{
			alert("请填写合法信息");
		}
	}else{
		alert("请填写合法信息");
	}
}

//添加用户时验证登录账号是否唯一
function validAccount(){
	var x=$("#user_account").val();
	if(x!=""){
		$.ajax({
			url:'user/validAccount.action',
			dataType:'json',
			type:'post',
			data:{
				account:$("#user_account").val()
			},
			success:function(data){
				$("#mid").text(data.msg);
			},
			error:function(){
				alert("请求失败");
			}
		});
	}else{
		return false;
	}
};



//查询所有部门
function getDeptList(){
	$.ajax({
		url:'dept/getDeptList.action',
		dataType:'json',
		type:'post',
		success:function(data){
			$("#sid").empty();
			$("#sid").append("<option value='0'>请选择</option>");
			$.each(data,function(){
				$("#sid").append("<option value='"+this.dept_id+"'>"+this.dept_name+"</option>");
			});
		},
		error:function(){
			alert("请求失败!");
		}
	});
}
//当修改密码后清空确认密码
function clearPass(){
	$("#againpass").val("");
	$('#myform').data('bootstrapValidator', null);
	formValidator();
}


$(document).ready(function(){
	formValidator();
});
function formValidator(){
	$("#myform").bootstrapValidator({
		fields:{
			user_account:{
				validators:{
					notEmpty:{
						message:"登陆账号不能为空"
					},
					stringLength:{
						max:20,
						message:"字符长度不能超过20个字符"
					}
				}
			},
			user_name:{
				validators:{
					notEmpty:{
						message:'姓名不能为空'
					},
					stringLength:{
						max:20,
						message:'字符长度不能超过20个字符'
					}
				}
			},
			user_password:{
				validators:{
					notEmpty:{
						message:'密码不能为空'
					},
					stringLength:{
						min:6,
						max:18,
						message:'字符长度要在6~18之间'
					}
				}
			},
			againpass:{
				validators:{
					notEmpty:{
						message:'密码不能为空'
					},
					identical:{
						field:'user_password',
						message:'两次输入密码不一致'
					}
				}
			},
			user_sex:{
				validators:{
					notEmpty:{
						message:'性别不能为空'
					}
				}
			},
			user_age:{
				validators:{
					notEmpty:{
						message:'年龄不能为空'
					},
					digits:{
						message: '该值只能包含数字'
					},
					stringLength:{
						max:3,
						message:'年龄不能超过三位数'
					}
				}
			},
			user_birth:{
				validators:{
					notEmpty:{
						message:"出生日期不能为空"
					}
				}
			},
			user_phone:{
				validators:{
					notEmpty:{
						message:'电话不能为空'
					},
					digits:{
						message: '该值只能包含数字'
					},
					stringLength:{
						min:8,
						max:11,
						message:'长度在8~11之间'
					}
				}
			},
			email:{
				validators:{
					notEmpty:{
						message:"Email不能为空"
					},
					emailAddress: {
						 message: 'Email格式不符合法'
					}
				}
			},
			dept_id:{
				validators:{
					notEmpty:{
						message:'部门不能为空'
					}
				}
			},
			user_address:{
				validators:{
					notEmpty:{
						message:'地址不能为空'
					},
					stringLength:{
						max:200,
						message:"字符长度不能超过200"
					}
				}
			}
		}
	});
	

	$("#myform1").bootstrapValidator({
		fields:{
			user_id:{
				validators:{
					notEmpty:{
						message:'用户编号不能为空'
					},
				}
			},
			user_account:{
				validators:{
					notEmpty:{
						message:"登陆账号不能为空"
					},
					stringLength:{
						max:20,
						message:"字符长度不能超过20个字符"
					}
				}
			},
			user_name:{
				validators:{
					notEmpty:{
						message:'姓名不能为空'
					},
					stringLength:{
						max:20,
						message:'字符长度不能超过20个字符'
					}
				}
			},
			user_password:{
				validators:{
					notEmpty:{
						message:'密码不能为空'
					},
					stringLength:{
						min:6,
						max:18,
						message:'字符长度要在6~18之间'
					}
				}
			},
			user_sex:{
				validators:{
					notEmpty:{
						message:'性别不能为空'
					}
				}
			},
			user_age:{
				validators:{
					notEmpty:{
						message:'年龄不能为空'
					},
					digits:{
						message: '该值只能包含数字'
					},
					stringLength:{
						max:3,
						message:'年龄不能超过三位数'
					}
				}
			},
			user_birth:{
				validators:{
					notEmpty:{
						message:"出生日期不能为空"
					}
				}
			},
			user_phone:{
				validators:{
					notEmpty:{
						message:'电话不能为空'
					},
					digits:{
						message: '该值只能包含数字'
					},
					stringLength:{
						min:8,
						max:11,
						message:'长度在8~11之间'
					}
				}
			},
			email:{
				validators:{
					notEmpty:{
						message:"Email不能为空"
					},
					emailAddress: {
						 message: 'Email格式不符合法'
					}
				}
			},
			dept_id:{
				validators:{
					notEmpty:{
						message:'部门不能为空'
					}
				}
			},
			user_address:{
				validators:{
					notEmpty:{
						message:'地址不能为空'
					},
					stringLength:{
						max:200,
						message:"字符长度不能超过200"
					}
				}
			}
		}
	});
	$("#importForm").bootstrapValidator({
		fields:{
			files:{
				validators:{
					notEmpty:{
						message:"导入文件不能为空"
					},
				}
			},
		}
	});
	
}
