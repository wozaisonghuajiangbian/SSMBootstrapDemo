$(function () {
    $("#register").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: '账号不能为空'
                    },
                    remote: {
                        url: 'user/chkUserName',
                        message: '用户已存在,请修改',
                        type: 'post',
                        delay: 2000
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '账号只能包含大写、小写、数字和下划线'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: 'Email不能为空'
                    },
                    remote: {
                        url: '/user/chkEmail',
                        message: 'Email已注册',
                        type: 'post',
                        delay: 1000
                    },
                    emailAddress: {
                        message: '邮箱地址格式有误'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '密码长度必须在6到30之间'
                    },
                    identical: {//相同
                        field: 'password', //需要进行比较的input name值
                        message: '两次密码不一致'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '密码非法'
                    }
                }
            },
            repassword: {
                validators: {
                    notEmpty: {
                        message: '确认密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '密码长度必须在6到30之间'
                    },
                    identical: {//相同
                        field: 'password',
                        message: '两次密码不一致'
                    },
                    regexp: {//匹配规则
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '密码非法'
                    }
                }
            },
            deptid: {
                validators: {
                    notEmpty: {
                        message: '部门不能为空'
                    }
                }
            }
        }
    })
});