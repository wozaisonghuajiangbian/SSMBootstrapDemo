/**
 * 
 */

$(function (){
	formValidator();
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
		url: "menu/menuList.action",
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
			field : "menu_id",
			title : "菜单编号",
			class : 'col-md-1',
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "menu_name",
			title : "菜单名称",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "url",
			title : "请求地址",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "parentId",
			title : "父级菜单id",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "icon",
			title : "图标样式",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "str",
			title : "状态",
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
    return ['<button type="button" class=" btn btn-info" onclick="getValue('+row.menu_id+')">修改</button>',
        '&nbsp;&nbsp;&nbsp;<button class=" btn btn-danger" type="button" onclick="delMenu('+row.menu_id+')">删除</button>'
        ].join('');
}

//删除
function delMenu(id){
	if(confirm('您确定要删除这条数据吗？')){
		$.ajax({
			url:'menu/delMenu.action',
			dataType:'json',
			type:'post',
			data:{
				mid:id
			},
			success:function(data){
				if(data){
					alert('删除成功！');
				}else{
					alert('删除失败！');
				}
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

//修改
function upMenu(){
	var x=$("#info1").text();
	if(x=='此名称可用'||x==""){

		if($("#addForm").data('bootstrapValidator').validate().isValid()){
			$.ajax({
				url:'menu/upMenu.action',
				dataType:'json',
				type:'post',
				data:$("#myform").serialize(),
				success:function(data){
					if(data){
						alert('修改成功！');
					}else{
						alert('修改失败！');
					}
					$("#test-table").bootstrapTable('refresh');
					closeDlg();
				},
				error:function(){
					alert("请求失败！");
				}
			});
		}
	}else{
		alert("填写信息不合法！");
	}
}
//修改  打开
function getValue(id){

	$.ajax({
		url:'menu/getMenu.action',
		dataType:'json',
		type:'post',
		data:{
			mid:id
		}, 	 	
		success:function(data){
			$("#menu_id1").val(id);
			$("#menu_name1").val(data.menu.menu_name);
			$("#url1").val(data.menu.url);
			$("#icon1").val(data.menu.icon);
			if(data.menu.statu=='0'){
				$("#qid1").prop('checked',true);
			}else{
				$("#jid1").prop('checked',true);
			}
			
			$("#parentId1").empty();
			$("#parentId1").append("<option value='0'>请选择</option>");
			$.each(data.list,function(index,items){
				if(data.menu.parentId==items.menu_id){
					$("#parentId1").append("<option selected value="+items.menu_id+">"+items.menu_name+"</option>");
				}else{
					$("#parentId1").append("<option value="+items.menu_id+">"+items.menu_name+"</option>");
				}
				
				
			});
		},
		error:function(){
			alert("请求失败！");
		}
	});
	$("#mydlg").modal('show');

}

//修改  验证菜单名称是否唯一
function checkMenu1(){
	var y=$("#menu_id1").val();
	var x=$("#menu_name1").val();
	if(x!=""){
		$.ajax({
			url:'menu/checkName.action',
			dataType:'json',
			type:'post',
			data:{
				menuName:x,
				mid:y
			},
			success:function(data){
				$("#info1").text(data.msg);
			},
			error:function(){
				alert("请求失败！");
			}
		});
	}
}


//添加  验证菜单名称是否唯一
function checkMenu(){
	var x=$("#menu_name").val();
	if(x!=""){
		$.ajax({
			url:'menu/checkName.action',
			dataType:'json',
			type:'post',
			data:{
				menuName:x
			},
			success:function(data){
				$("#info").text(data.msg);
			},
			error:function(){
				alert("请求失败！");
			}
		});
	}
}

//添加
function saveMenu(){
	var x=$("#info").text();
	if(x=="此名称可用"){
		if($("#addForm").data('bootstrapValidator').validate().isValid()){
			$.ajax({
				url:'menu/saveMenu.action',
				dataType:'json',
				type:'post',
				data:$("#addForm").serialize(),
				success:function(data){
					if(data){
						alert('添加成功！');
					}else{
						alert('添加失败！');
					}
					$("#test-table").bootstrapTable('refresh');
					closeDlg();
				},
				error:function(){
					alert("请求失败！");
				}
			});
		}
	}else{
		alert("填写信息不合法！");
	}
}
//打开  添加
function addMenu(){
	$.ajax({
		url:'menu/parentMenu.action',
		dataType:'json',
		type:'post',
		success:function(data){
			$("#parentId").empty();
			$("#parentId").append("<option value='0'>请选择</option>");
			$.each(data,function(index,items){
				$("#parentId").append("<option value="+items.menu_id+">"+items.menu_name+"</option>");
			});
		},
		error:function(){
			alert("请求失败！");
		}
	});
	$("#addDlg").modal('show');
}

//关闭
function closeDlg(){
	$("#addDlg").modal('hide');
	$("#mydlg").modal('hide');
	$("#info").text(null);
	$("#info1").text(null);
	$("input[type=reset]").trigger("click");
	$('#myform').data('bootstrapValidator', null);
	$('#addForm').data('bootstrapValidator', null);
	formValidator();
}

function formValidator(){

	$("#addForm").bootstrapValidator({
		fields:{
			menu_name:{
				validators:{
					notEmpty:{
						message:"菜单名称不能为空"
					},
					stringLength:{
						max:20,
						message:"字符长度不能超过20个字符"
					}
				}
			},
			url:{
				validators:{
					notEmpty:{
						message:'请求地址不能为空'
					},
					stringLength:{
						max:200,
						message:'字符长度不能超过200个字符'
					}
				}
			},
			icon:{
				validators:{
					notEmpty:{
						message:'图标样式不能为空'
					}
				}
			},
			statu:{
				validators:{
					notEmpty:{
						message:"状态不能为空"
					}
				}
			},
			parentId:{
				validators:{
					notEmpty:{
						message:'父级ID不能为空'
					},
					digits:{
						message: '该值只能包含数字'
					}
				}
			},
		}
	});
	

	$("#myform").bootstrapValidator({
		fields:{
			menu_name:{
				validators:{
					notEmpty:{
						message:"菜单名称不能为空"
					},
					stringLength:{
						max:20,
						message:"字符长度不能超过20个字符"
					}
				}
			},
			url:{
				validators:{
					notEmpty:{
						message:'请求地址不能为空'
					},
					stringLength:{
						max:200,
						message:'字符长度不能超过200个字符'
					}
				}
			},
			icon:{
				validators:{
					notEmpty:{
						message:'图标样式不能为空'
					}
				}
			},
			statu:{
				validators:{
					notEmpty:{
						message:"状态不能为空"
					}
				}
			},
			parentId:{
				validators:{
					notEmpty:{
						message:'父级ID不能为空'
					},
					digits:{
						message: '该值只能包含数字'
					}
				}
			},
		}
	});
}