/**
 * 
 */

$(function(){
	formValidator();
	$("#test-table").bootstrapTable('destroy');
	$('#test-table').bootstrapTable({
		method : 'GET', //默认是post,不允许对静态文件访问
		url: "role/rolePages.action",
		cache : false,
		striped : true,// 隔行加亮
		pagination : true, //开启分页功能    在表格底部显示分页工具栏
		pageSize : 5, //默认每页条数
		pageNumber : 1, //默认分页
		pageList : [ 10, 20, 50, 100, 200, 500 ],//分页数
		toolbar:"#toolbar",
		singleselect : true,
         clickToSelect: true, // 单击行即可以选中
		search : false,//显示搜素表单
		silent : true, //刷新事件必须设置
		sidePagination : "server", //表示服务端请求  
		columns : [ {
			field : "role_id",
			title : "角色编号",
			class : 'col-md-1',
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "role_name",
			title : "角色名称",
			align : "center",
			valign : "middle",
			sortable : "true"
		}, {
			field : "role_desc",
			title : "角色描述",
			align : "center",
			valign : "middle",
			sortable : "true"
		},{
			field : "pid",
			title : "上级角色ID",
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
    return ['<button type="button" class=" btn btn-warning" onclick="getAuth('+row.role_id+')">权限</button>&nbsp;&nbsp;&nbsp;',
        '<button type="button" class=" btn btn-info" onclick="getvalue('+row.role_id+')">修改</button>',
        '&nbsp;&nbsp;&nbsp;<button class=" btn btn-danger" type="button" onclick="delRole('+row.role_id+')">删除</button>'
        ].join('');
}

//保存权限
function saveAuth(){
	var id=$("#rid").val();
	var ids=[];
	var obj=$('#tree').treeview('getChecked');
	$.each(obj,function(index,items){
		ids.push(items.id);
	});
	$.ajax({
		url:'role/saveRoleMenu.action',
		dataType:'json',
		type:'post',
		traditional:true,
		data:{
			rid:id,
			mid:ids
			},
		success:function(data){
			if(data){
				alert("保存成功！");
			}else{
				alert("保存失败！");
			}
			$("#test-table").bootstrapTable("refresh");
			closeDlg();
		},
		error:function(){
			alert("请求失败！");
		}
	});
	
}

//权限
function getAuth(id){
	$("#rid").val(id);
	$.ajax({
		url:'role/viewTree.action',
		dataType:'json',
		type:'post',
		data:{rid:id},
		success:function(data){
			 $('#tree').treeview({
                 data: data,         // 数据源
                 showCheckbox: true,   //是否显示复选框
                 highlightSelected: false,    //是否高亮选中
                 //nodeIcon: 'glyphicon glyphicon-user',    //节点上的图标
                    //expandIcon: 'glyphicon glyphicon-chevron-right',//展开图标 
                   //  collapseIcon: 'glyphicon glyphicon-chevron-down',//合并图标 
                   //  nodeIcon: 'glyphicon glyphicon-bookmark',//无节点图标 
                  backColor: "purple",//背景色 
                     onhoverColor: "#F5F5DC",//鼠标悬浮颜色 
                     borderColor: "red",//边框颜色 
                     highlightSelected: true,//高亮选中 
                     selectedColor: "red",//选中颜色 
                     selectedBackColor: "#D3D3D3",//选中背景色 
                  color: "#00BFFF", 
                   selectable: false,
                 multiSelect: false,    //多选
                  state: {
                	     checked: true,
                	     disabled: true,
                	     expanded: true,
                	     selected: true
                	   },
                 onNodeChecked: function (event,node) {
                	// 父级节点被选中，那么子级节点都要选中
                	  if (node.nodes != null) {
                	   $.each(node.nodes, function(index, value) {
                	     $('#tree').treeview('checkNode', value.nodeId, {
                	     silent : true
                	    });
                	   });
                	  } else {

                    	   // 子级节点选中的时候，要根据情况判断父节点是否要全部选中
                    	   // 父节点
                    	   var parentNode =  $('#tree').treeview('getParent', node.nodeId);
                    	   var isAllchecked =  $('#tree'); // 是否全部选中
                    	   // 当前子级节点的所有兄弟节点，也就是获取父下面的所有子
                    	   var siblings =  $('#tree').treeview('getSiblings', node.nodeId);
                    	   for ( var i in siblings) {
                    	    // 有一个没选中，则不是全选
                    	    if (!siblings[i].state.checked) {
                    	     isAllchecked = false;
                    	     break;
                    	    }
                    	   }
                    	   // 全选，则打钩
                    	   if (isAllchecked) {
                    	     $('#tree').treeview('checkNode', parentNode.nodeId, {
                    	     silent : true
                    	    });
                    	   } else {// 非全选，则变红
                    	     $('#tree').treeview('checkNode', node.nodeId, {
                    	     silent : true
                    	    });
                    	   }
                    	  
                	 }
                 },
                 onNodeUnchecked : function(event, node) {
                	 silentByChild = true;
                	  // 选中的是父级节点
                	  if (node.nodes != null) {
                	   // 这里需要控制，判断是否是因为子级节点引起的父节点被取消选中
                	   // 如果是，则只管取消父节点就行了
                	   // 如果不是，则子节点需要被取消选中
                	// var silentByChild=false;
                	   if (silentByChild) {
                	    $.each(node.nodes, function(index, value) {
                	     $('#tree').treeview('uncheckNode', value.nodeId, {
                	      silent : true
                	     });
                	    });
                	   }
                	  } else {/*
                	   // 子级节点被取消选中
                	   var parentNode = $('#tree').treeview('getParent', node.nodeId);
                	   var isAllUnchecked = true; // 是否全部取消选中
                	   // 子级节点有一个选中，那么就不是全部取消选中
                	   var siblings = $('#tree').treeview('getSiblings', node.nodeId);
                	   for ( var i in siblings) {
                	    if (siblings[i].state.checked) {
                	     isAllUnchecked = false;
                	     break;
                	    }
                	   }
                	   // 全部取消选中，那么省级节点恢复到默认状态
                	   if (isAllUnchecked) {
                	    $('#tree').treeview('unselectNode', parentNode.nodeId, {
                	     silent : true,
                	    });
                	    $('#tree').treeview('uncheckNode', parentNode.nodeId, {
                	     silent : true,
                	    });
                	   } else {
                	    silentByChild = false;
                	    $('#tree').treeview('selectNode', parentNode.nodeId, {
                	     silent : true,
                	    });
                	    $('#tree').treeview('uncheckNode', parentNode.nodeId, {
                	     silent : true,
                	    });
                	   }
                	  */}
                	  
                	 },
                 onNodeSelected: function (event, data) {
                	
                 }
             });

		},
		error:function(){
			alert("请求失败！");
		}
	});
	$("#authDlg").modal('show');
}

//修改，打开模态框
function getvalue(id){
	$.ajax({
		url:'role/queryRole.action',
		dataType:'json',
		type:'post',
		data:{rid:id},
		success:function(data){
			$("#role_id1").val(data.role.role_id);
			$("#role_name1").val(data.role.role_name);
			$("#role_desc1").val(data.role.role_desc);
			$("#pid1").empty();
			$("#pid1").append("<option value='0'>请选择上级角色</option>");
			$.each(data.rlist,function(index,items){
				if(data.role.pid==items.role_id){
					$("#pid1").append("<option selected value='"+items.role_id+"'>"+items.role_name+"</option>");
				}else{
					$("#pid1").append("<option value='"+items.role_id+"'>"+items.role_name+"</option>");
				}
			});
		},
		error:function(){
			alert("请求失败！");
		}
	});
	$("#mydlg").modal('show');
}

//修改
function upRole(){
	if($("#infos").text()=="名称可用"||$("#infos").text()==""){
		if($("#myform").data('bootstrapValidator').validate().isValid()){
			$.ajax({
				url:'role/upRole.action',
				dataType:'json',
				type:'post',
				data:$("#myform").serialize(),
				success:function(data){
					if(data>0){
						alert("修改成功！");
					}else{
						alert("修改失败！");
					}
					$("#test-table").bootstrapTable("refresh");
					closeDlg();
				},
				error:function(){
					alert("请求失败！");
				}
			});
		}
	}else{
		alert("请正确填写信息！");
	}

}
//删除
function delRole(id){
	if(confirm("您确定要删除这条数据吗？")){
		$.ajax({
			url:'role/delRole.action',
			dataType:'json',
			type:'post',
			data:{rid:id},
			success:function(data){
				if(data>0){
					alert("删除成功！");
				}else{
					alert("删除失败！");
				}
				$("#test-table").bootstrapTable("refresh");
			}
		});
	}else{
		return false;
	}
}

//添加，打开模态框
function addRole(){
	$.ajax({
		url:'role/roleList.action',
		dataType:'json',
		type:'post',
		success:function(data){
			$("#pid").empty();
			$("#pid").append("<option value='0'>请选择上级角色</option>");
			$.each(data,function(){
				$("#pid").append("<option value='"+this.role_id+"'>"+this.role_name+"</option>");
			});
		},
		error:function(){
			alert("请求失败！");
		}
	});
	$("#addDlg").modal('show');
}

//添加
function insertRole(){
	if($("#info").text()=="名称可用"){
		if($("#addForm").data('bootstrapValidator').validate().isValid()){
			$.ajax({
				url:'role/saveRole.action',
				dataType:'json',
				type:'post',
				data:$("#addForm").serialize(),
				success:function(data){
					if(data>0){
						alert("添加成功！");
					}else{
						alert("添加失败！");
					}
					$("#test-table").bootstrapTable("refresh");
					closeDlg();
				},
				error:function(){
					alert("请求失败！");
				}
			});
		}
	}else{
		alert("请正确填写信息！");
	}
}


//添加 角色名称验证
function checkRole(){
	if($("#role_name").val()!=""){
		$.ajax({
			url:'role/checkRole.action',
			dataType:'json',
			type:'post',
			data:{
				roleName:$("#role_name").val()
			},
			success:function(data){
				$("#info").text(data.msg);
			},
			error:function(){
				alert("请求失败！");
			}
		});
	}else{
		return false;
	}
}

//修改 角色名称验证
function checkRole1(){
	var id=$("#role_id1").val();
	if($("#role_name1").val()!=""){
		$.ajax({
			url:'role/checkRole.action',
			dataType:'json',
			type:'post',
			data:{
				roleName:$("#role_name1").val(),
				rid:id
			},
			success:function(data){
				$("#infos").text(data.msg);
			},
			error:function(){
				alert("请求失败！");
			}
		});
	}else{
		return false;
	}
}

//关闭模态框
function closeDlg(){
	$("#authDlg").modal('hide');
	$("#addDlg").modal('hide');
	$("#mydlg").modal('hide');
	$("#info").text(null);
	$("#infos").text(null);
	$("input[type=reset]").trigger("click");
	$('#myform').data('bootstrapValidator', null);
	$('#addForm').data('bootstrapValidator', null);
	formValidator();
}

//表单验证
function formValidator(){
	$("#addForm").bootstrapValidator({
		fields:{
			role_name:{
				validators:{
					notEmpty:{
						message:"角色名称不能为空"
					},
					stringLength:{
						max:20,
						message:'不能超过20个字符长度'
					},
				}
			},
			role_desc:{
				validators:{
					notEmpty:{
						message:'角色描述不能为空',
					},
					stringLength:{
						max:200,
						message:'字符长度不能超过200'
					}
				}
			},
			pid:{
				validators:{
					notEmpty:{
						message:'不能为空'
					}
				}
			}
		}
	});
	

	$("#myform").bootstrapValidator({
		fields:{
			role_name:{
				validators:{
					notEmpty:{
						message:"角色名称不能为空"
					},
					stringLength:{
						max:20,
						message:'不能超过20个字符长度'
					},
				}
			},
			role_desc:{
				validators:{
					notEmpty:{
						message:'角色描述不能为空',
					},
					stringLength:{
						max:200,
						message:'字符长度不能超过200'
					}
				}
			},
			pid:{
				validators:{
					notEmpty:{
						message:'不能为空'
					}
				}
			}
		}
	});

}
