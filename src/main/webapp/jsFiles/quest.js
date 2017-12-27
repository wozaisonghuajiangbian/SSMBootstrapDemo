/**
 * 
 */

$(function(){
	formValidator();
	$("#test-table").bootstrapTable('destroy');
	$('#test-table').bootstrapTable({
		method : 'GET', //默认是post,不允许对静态文件访问
		url: "activiti/.action",
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

//打开  填写请假
function addQuest(){
	$("#noteDlg").modal('show');
}


//关闭模态框
function closeDlg(){
	$("#noteDlg").modal('hide');
	$("#mydlg").modal('hide');
	$("input[type=reset]").trigger("click");
	$('#myform').data('bootstrapValidator', null);
	$('#noteForm').data('bootstrapValidator', null);
	formValidator();
}

//保存
function saveNote(){
	$.ajax({
		url:'activiti/addNote.action',
		dataType:'json',
		type:'post',
		data:$("#noteForm").serialize(),
		success:function(data){
			
		},
		error:function(){
			alert("请求失败！");
		}
	});
}

function formValidator(){
	
}