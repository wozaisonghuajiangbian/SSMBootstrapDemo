/**
 * 部门
 */

$(function(){
	$('#test-table').bootstrapTable('destroy')
	$('#test-table').bootstrapTable({
		method : 'GET',
		url: "dept/deptList.action",
		cache : false,
		striped : true,
		pagination : true, //在表格底部显示分页工具栏
		pageSize : 5, //默认每页条数
		pageNumber : 1, //默认分页
		pageList : [ 10, 20, 50, 100, 200, 500 ],//分页数
		showColumns : false, //显示隐藏列
		showRefresh : false, //显示刷新按钮
		showExport : false,
		toolbar:"#toolbar",
		singleselect : true,
		clickToSelect: true, // 单击行即可以选中
		search : false,//显示搜素表单
		silent : true, //刷新事件必须设置
		sidePagination : "server", //表示服务端请求  
		columns : [    {
			field : "dept_id",
			title : "部门编号",
			class : 'col-md-1',
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "dept_name",
			title : "部门名称",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "dept_desc",
			title : "部门描述",
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
		},
		 //注册加载子表的事件。注意下这里的三个参数！
       onExpandRow: function (index, row, $detail) {
            oInit.InitSubTable(index, row, $detail);
        }

	});
}); 

function operateFormatter(value, row, index) {
    return [
        '<button type="button" class=" btn btn-info" onclick="getvalue('+row.dept_id+')">修改</button>',
        '&nbsp;&nbsp;&nbsp;<button class=" btn btn-danger" type="button" onclick="delDept('+row.dept_id+')">删除</button>'
        ].join('');
}



function formValidator(){
	$("#addForm").bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
			},
			
		fields:{
			dept_desc:{
				message: '部门名称验证失败',
				validators:{
					notEmpty:{
						message:'部门描述不能为空'
					},
					stringLength:{
						max:200,
						message:'字符长度不能超过200'
					}
				}
			},
			dept_name:{
				message: '部门名称验证失败',
				validators:{
					notEmpty:{
						message:'部门名称不能为空'
					},
					stringLength:{
						max:20,
						message:'字符长度不能超过20'
					}
				}
			}
		}
	});
	
	$("#myform").bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
			},

	        fields: {
	        	dept_id:{
	        		message: '部门编号验证失败',
	                validators: {
	                    notEmpty: {
	                        message: '部门编号不能为空'
	                    }
	                }
	        	},
	        	dept_name:{
	        		validators:{
	        			notEmpty:{
	        				message:'部门名称不能为空'
	        			},
	        			stringLength: {
	                        max: 20,
	                        message: '字符长度不能超过20'
	                    }
	        		},
	        		dept_desc:{
	        			validators:{
	        				notEmpty:{
	        					message:'部门描述不能为空'
	        				},
	        				stringLength:{
	        					max:200,
	        					message:'字符长度不能超过200'
	        				}
	        			}
	        		},
	        	}
	        }
	});
}
//表单验证
$(function(){
	formValidator();
	
});

//添加部门，打开模态框
function addDept(){
	$("#addDlg").modal('show');
}

// 提交部门
function insertDept(){
	var name=$("#dept_name1").val();
	$.ajax({//部门名称唯一验证
		url:'dept/valid.action',
		dataType:'json',
		type:'post',
		data:{name:name},
		success:function(data){
			if(data.i>0){
				if($("#addForm").data('bootstrapValidator').validate().isValid()){
					$.ajax({
						url:'dept/addDept.action',
						type:'post',
						dataType:'json',
						data:$("#addForm").serialize(),
						success:function(info){
							if(info>0){
								alert("添加成功");
							}else{
								alert("添加失败");
							}
							$("#test-table").bootstrapTable('refresh');
							$("#addDlg").modal('hide');
							$("#dept_name1").val("");
							$("#dept_desc1").val(null);
						},
						error:function(){
							alert('请求失败');
						}
					});
				}else{
					return false;
				}
			}else{
				$("#info").text(data.msg);
			}
		},
		error:function(){
			alert("请求失败");
		}
	});
}


//打开模态框,数据回写
function getvalue(id){
	$.ajax({
		url:'dept/deptById.action',
		type:'post',
		dataType:'json',
		data:{did:id},
		success:function(data){
			$("#dept_id").val(data.dept_id);
			$("#dept_name").val(data.dept_name);
			$("#dept_desc").val(data.dept_desc);
		},
		error:function(){
			alert("请求失败");
		}
	});
	$("#mydlg").modal("show");
}
//保存修改内容
function upDept(){
	var name=$("#dept_name").val();
	var id=$("#dept_id").val();
	$.ajax({//部门名称唯一验证
		url:'dept/valid.action',
		dataType:'json',
		type:'post',
		data:{name:name,id:id},
		success:function(data){
			if(data.i>0){
				if($("#myform").data('bootstrapValidator').validate().isValid()){
					$.ajax({
						url:'dept/upDept.action',
						type:'post',
						dataType:'json',
						data:$("#myform").serialize(),
						success:function(data){
							if(data>0){
								alert("修改成功");
							}else{
								alert("修改失败");
							}
							$("#test-table").bootstrapTable('refresh');
							$("#mydlg").modal("hide");
						},
						error:function(){
							alert("请求失败");
						}
					});
				}else{
					return false;
				}
			}else{
				$("#infos").text(data.msg);
			}
		},
		error:function(){
			alert("请求失败");
		}
	});
}
//关闭模态框
function closedlg(){
	$("#mydlg").modal("hide");
	$("#addDlg").modal("hide")
	$("#info").text("");
	$("#infos").text("");
	
	$('#myform').data('bootstrapValidator', null);
	$("#addForm").data('bootstrapValidator',null);
	formValidator();
	//$('#mydlg').data('bootstrapValidator').resetForm(true);
	$("input[type=reset]").trigger("click");
	
	
}

//单个删除
function delDept(id){
	if(confirm('您确定要删除这条数据吗')){
		$.ajax({
			url:'dept/delDept.action',
			type:'post',
			dataType:'json',
			data:{did:id},
			success:function(data){
				if(data>0){
					alert("删除成功");
				}else{
					alert("删除失败");
				}
				$("#test-table").bootstrapTable('refresh');
			},
			error:function(){
				alert("请求失败");
			}
		});
	}
}