 function getPage(url,name) {
            if(url!=""){
                $.ajax({cache: false});
                $("#title h2").text(name);
                $("#title strong").text(name);
                $("#content").load(url,function(result){
                    //alert(result);
                    //将被加载页的JavaScript加载到本页执行
                    $result = $(result);
                    $result.find("script").appendTo('#content');
                });
            }
        }
        //退出登陆
        function quit(){
        	if(confirm('您确定要退出登陆吗？')){
        		location.href="login/quit.action";
        	}else{
        		return false;
        	}
        }
        //open
        function openPassDlg(){
        	$("#passDlg").modal('show');
        }
        //关闭模态框
        function closeDlgs(){
        	$("#passDlg").modal('hide');
        	$('#oldPass').val("");
        	$('#newPass').val("");
        	$('#againPass').val("");
        	// Modal验证销毁重构，防止第二次打开modal时显示上一次的验证痕迹
        	$('#myform').data('bootstrapValidator', null);
        	formValidator();
        }
        //修改密码
        function upPass(){
        	var p=$("#oldPass").val();
        	var n=$("#newPass").val();
        	var ids=$("#uid").val();
        	if(p!=n){
        		if($("#myform").data('bootstrapValidator').validate().isValid()){
            		$.ajax({
            			url:'user/upPass.action',
            			dataType:'json',
            			type:'post',
            			data:{
            				id:ids,
            				pass:n
            			},
            			success:function(data){
            				if(data>0){
            					alert("密码修改成功，请退出重新登陆！");
            					location.href="login/quit.action";
            				}else{
            					alert("密码修改失败");
            				}
            			},
            			error:function(){ 
            				alert("请求失败");
            			}
            		});
            	}else{
            		return false;
            	}
        	}else if(p==""||n==""){
        		alert("请输入密码");
        	}
        	else{
        		alert("新密码不能与原密码一样");
        	}
        }
        
        $(function(){
        	formValidator();
        });
        function formValidator(){
        	$("#myform").bootstrapValidator({
        		fields:{
        			id:{
        				notEmpty:{
        					message:'不能为空'
        				}
        			},
        			password:{
        				notEmpty:{
        					message:'不能为空'
        				}
        			},
        			oldPass:{
        				validators:{
        					notEmpty:{
        						message:'密码不能为空'
        					},
        					stringLength:{
        						min:6,
        						max:18,
        						message:"字符长度要在6~18之间"
        					},
        					identical:{
        						field:'password',
        						message:'输入旧密码有误'
        					}
        				}
        			},
        			newPass:{
        				validators:{
        					notEmpty:{
        						messgae:'密码不能为空',
        					},
        					stringLength:{
        						min:6,
        						max:18,
        						message:'字符长度要在6~18之间'
        					},
        				}
        			},
        			againPass:{
        				validators:{
        					notEmpty:{
        						message:'密码不能为空'
        					},
        					identical:{
        						field:"newPass",
        						message:'两次输入密码不一致'
        					}
        				}
        			}
        		}
        	});
        }
        
     // Modal验证销毁重构，防止第二次打开modal时显示上一次的验证痕迹
        /*$('#mydlg').on('hidden.bs.modal', function() {
        	$('#myform').data('bootstrapValidator', null);
        	formValidator();
        });*/