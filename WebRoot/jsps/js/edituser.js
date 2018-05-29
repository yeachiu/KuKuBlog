$(function(){
	
	/*
	 * 1. 给提交按钮添加submit()事件，完成表单校验
	 */
	$("#submit").submit(function(){
		alert(2222);
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
		alert(333);
		$("#msg").css("display", "none");
	});
	
	/*
	 * 3. 输入框推动焦点时进行校验
	 */
	$(".input").blur(function() {
		alert(444);
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
 * 校验密码
 */
function validateUserPwd() {
	alert(1);
	var bool = true;
	$("#Error").css("display", "none");
	var value = $("#userPwd").val();
	if(!value) {// 非空校验
		$("#Error").css("display", "");
		$("#Error").text("密码不能为空！");
		bool = false;
	} else if(value.length < 6 || value.length > 20) {//长度校验
		$("#Error").css("display", "");
		$("#Error").text("密码长度必须在6 ~ 20之间！");
		bool = false;
	}
	return bool;
}

// 校验新密码
function validateNewloginpass() {
	alert(2);
	var bool = true;
	$("#Error").css("display", "none");
	var value = $("#newloginpass").val();
	if(!value) {// 非空校验
		$("#Error").css("display", "");
		$("#Error").text("新密码不能为空！");
		bool = false;
	} else if(value.length < 3 || value.length > 20) {//长度校验
		$("#Error").css("display", "");
		$("#Error").text("新密码长度必须在3 ~ 20之间！");
		bool = false;
	}
	return bool;
}

/*
 * 校验确认密码
 */
function validateReloginpass() {
	alert(3);
	var bool = true;
	$("#Error").css("display", "none");
	var value = $("#reloginpass").val();
	if(!value) {// 非空校验
		$("#Error").css("display", "");
		$("#Error").text("确认密码不能为空！");
		bool = false;
	} else if(value != $("#newloginpass").val()) {//两次输入是否一致
		$("#Error").css("display", "");
		$("#Error").text("两次密码输入不一致！");
		bool = false;
	}
	return bool;	
}
