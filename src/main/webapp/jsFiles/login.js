/**
 * 登陆
 */

//表单验证
$(function(){
	$('#form').bootstrapValidator({
			message: 'This value is not valid',
			feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
				},

		        fields: {
		        	user_account: {
		                message: '登录名验证失败',
		                validators: {
		                    notEmpty: {
		                        message: '登录账号不能为空'
		                    }
		                }
		            },
		            user_password: {
		                validators: {
		                    notEmpty: {
		                        message: '登录密码不能为空'
		                    },
		                    regexp: {
		                        regexp: /^[a-zA-Z0-9_\.]+$/,
		                        message: '密码非法'
		                    },
		                    stringLength: {
		                        min: 6,
		                        max: 12,
		                        message: '密码长度必须在6到18之间'
		                    }
		                }
		            },
		         
		        }
		    });
	});

//登陆
function doLogin(){
	if($("#form").data('bootstrapValidator').validate().isValid()){
		//layer.msg('玩命提示中');
		//layer.msg('的确很重要', {icon: 1});
		$.ajax({
			type:'post',
			dataType:'json',
			url:'login/doLogin.action',
			data:$("#form").serialize(),
			success:function(data){
				if(data.i==0){
					location.href="login/login.action?uid="+data.id;
				}else if(data.i==1){
					layer.msg('登录密码不正确',{
						time:30000,
						btn:['确定']
					});
					//location.reload();
				}else{
					layer.msg('此账号不存在',{
						time:30000,
						btn:['确定']
					});
					//location.reload();
				}
			},
			error:function(){
				layer.msg("请求失败！");
			}
		});
		
	}else{
		/*layer.msg('取消',{
			time:2000,
			btn:['明白了','知道了']
		});*/
		return false;
	}
	
	
}