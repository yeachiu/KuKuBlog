/*
 *	表单提交后，首先用js进行一轮校验，以节省服务器的运行消耗。
 *	 
 */
$(function(){
	/*
	 * 1. 给提交按钮添加submit()事件，完成表单校验
	 */
	$("#submit").submit(function(){
		$("#msg").text("");
		var bool = true;
		alert(true);
		$(".input").each(function() {
			alert(false);
			var inputName = $(this).attr("name");
			if(!invokeValidateFunction(inputName)) {
				bool = false;
			}
		});
		return bool;
	});
	
	
	/*
	 * 2. 输入框得到焦点时隐藏错误信息
	 */
	$(".input").focus(function() {
		var inputName = $(this).attr("name");
		$("#" + inputName + "Error").css("display", "none");
	});
	
	/*
	 * 3. 输入框推动焦点时进行校验
	 */
	$(".input").blur(function() {
		var inputName = $(this).attr("name");
		invokeValidateFunction(inputName);
	});
});//文件加载完执行

/*
 * 输入input名称，调用对应的validate方法。
 * 例如input名称为：loginname，那么调用validateLoginname()方法。
 */
function invokeValidateFunction(inputName) {
	inputName = inputName.substring(0, 1).toUpperCase() + inputName.substring(1);
	var functionName = "validate" + inputName;
	return eval(functionName + "()");	
}

/*
 * 校验登录名
 */
function validateUserName() {
	var bool = true;
	$("#userNameError").css("display", "none");
	var value = $("#userName").val();
	if(!value) {// 非空校验
		$("#userNameError").css("display", "");
		$("#userNameError").text("用户名不能为空！");
		bool = false;
	} else if(value.length < 3 || value.length > 20) {//长度校验
		$("#userNameError").css("display", "");
		$("#userNameError").text("用户名长度必须在3 ~ 20之间！");
		bool = false;
	}
	return bool;
}
/*
*	校验密码
*/
function validateUserPwd() {
	var bool = true;
	$("#userPwdError").css("display", "none");
	var value = $("#userPwd").val();
	if(!value) {// 非空校验
		$("#userPwdError").css("display", "");
		$("#userPwdError").text("密码不能为空！");
		bool = false;
	}
	return bool;
}
/*
 *	校验验证码 
 * 
 */
function validateVerifyCode() {
	var bool = true;
	$("#verifyCodeError").css("display", "none");
	var value = $("#verifyCode").val();
	if(!value) {// 非空校验
		$("#verifyCodeError").css("display", "");
		$("#verifyCodeError").text("验证码不能为空！");
		bool = false;
	}else if(value.length !=4){//验证码长度不为4，错误！
		$("#verifyCodeError").css("display","");
		$("#verifyCodeError").text("验证码错误！");
	}else{
		$.ajax({
			cache: false,
			async: false,
			type: "POST",
			dataType: "json",
			data: {method: "ajaxValidateVerifyCode", verifyCode: value},
			url: "/blog/UserServlet",
			success: function(flag) {
				if(!flag) {
					$("#verifyCodeError").css("display", "");
					$("#verifyCodeError").text("验证码错误！");
					bool = false;					
				}
			}
		});
	}
	return bool;
}