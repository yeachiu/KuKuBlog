<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="images/logo.gif" rel="SHORTCUT ICON">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <title>BLOG</title>
	
    <!-- Bootstrap -->
    <link href="<c:url value='/jsps/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
    
	<!--my-style-->
	<link rel="stylesheet" type="text/css" href=" <c:url value='/jsps/css/user/scrollbar.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/log.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/common.css'/>">
    
    <script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script src="<c:url value='/jsps/js/log.js'/>"></script>
	<script src="<c:url value='/jsps/js/common.js'/>"></script> 
	
</head>

<body>
	
</head>
<body>
	
	<div id="log">
		<div class="demmo">				
			<a href="<c:url value='/index.jsp'/>"><img src="<c:url value='/jsps/images/demmo.png'/>" width="150px" title="返回主页？"></a>
		</div>	
		<div class="panel panel-default">
	  		<div class="panel-body">
	    		<!-- Nav tabs -->
	  			<ul class="nav nav-tabs text-center" role="tablist">
	    			<li role="presentation" class="active"><a href="#login" aria-controls="login" role="tab" data-toggle="tab">登录</a></li>
	    			<!-- <li role="presentation"><a href="#logup" aria-controls="logup" role="tab" data-toggle="tab">注册</a></li> -->
	  			</ul>
	  			<!-- Tab panes -->
	  			<div class="tab-content">
	  				<!--登陆框-->
				    <div role="tabpanel" class="tab-pane active" id="login">
				    	<form class="form-horizontal" action="<c:url value='/UserServlet'/>" method="post" id="loginForm">
				    	  <input type="hidden" name="method" value="login" /><!-- 用于访问Servlet中特定请求处理方法 -->
				    	  <div  class="col-sm-12 text-center text-danger error" id="msg">
						   	<small class="errors" id="msg">${msg}</small>
						  </div>	
						  <div class="form-group">
						    <label  class="col-sm-3 control-label">用户名</label>
						    <div class="col-sm-8">
						      <input class="input form-control" id="userName" name="userName" type="text" value="${user.userName }">
						    </div>
						    <div  class="col-sm-12 text-center text-danger error">
						    	<small class="error" id="userNameError">${errors.userName}</small>
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="loginpass" class="col-sm-3 control-label">密码</label>
						    <div class="col-sm-8">
						      <input type="password" name="userPwd" id="userPwd" class="input form-control">
						    </div>
						    <div  class="col-sm-12 text-center text-danger error">
						    	<small class="error" id="userPwdError">${errors.userPwd}</small>
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="verifyCode" class="col-sm-3 control-label">验证码</label>
						    <div class="col-sm-4">
						      <input type="text" class="input form-control" id="verifyCode" name="verifyCode">
						    </div>
						    <div class="col-sm-4">
						      <a id="verifyCode" href="javascript: change();">
						      	<img id="vCode" src="<c:url value='/VerifyCodeServlet'/>" width="100px" height="35px" />
						      </a>
						    </div>
						    <div  class="col-sm-12 text-center text-danger error">
						    	<small class="error" id="verifyCodeError">${errors.verifyCode}</small>
						    </div>
						  </div>
						  <div class="form-group">
						  	<div class="col-sm-1"></div>
						    <div class="col-sm-10">
						      <div class="checkbox">
						        <label class="pull-left">
						          <input type="checkbox"> 记住我
						        </label>
						        <label class="pull-right forget">
						          <span type="checkbox"><a href="#"> 忘记密码？</a></span>
						        </label>
						      </div>
						    </div>
						  </div>
						  <div class="form-group">
						    <div class="col-sm-12 col-sm-12 text-center">
						      <button type="submit" class="btn btn-default" id="submit" >登录</button>
						    </div>
						  </div>
						</form>
				    </div>
				    <!--注册框-->
				    <!-- <div role="tabpanel" class="tab-pane" id="logup">
						<form class="form-horizontal">
							<div class="form-group">
						    <label  class="col-sm-3 control-label">用户名</label>
						    <div class="col-sm-8">
						      <input  class="form-control" id="inputEmail3" placeholder="名字">
						    </div>
						 </div>
						  <div class="form-group">
						    <label  class="col-sm-3 control-label">邮箱/手机</label>
						    <div class="col-sm-8">
						      <input  class="form-control" id="inputEmail3" placeholder="邮箱/手机号">
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="inputPassword3" class="col-sm-3 control-label">密码</label>
						    <div class="col-sm-8">
						      <input type="password" class="form-control" id="inputPassword3" placeholder="密码(不少于6位)">
						    </div>
						  </div>
							<div class="form-group">
							    <label class="t-pico tos col-sm-offset-2 col-sm-9 text-right">
							    	* 点击注册，表示同意<a href="#" target="_window">服务条款</a>和<a href="#" target="_window">隐私条款</a>
							    </label>
							</div>
						  <div class="form-group">
						    <div class="col-sm-12 col-sm-12 text-center">
						      <button type="submit" class="btn btn-default">注册</button>
						    </div>
						  </div>
						</form>
				    </div> -->
				  </div>
	  		</div>
		</div>
	</div>
	
	<canvas id="canvas"></canvas>
	
	<!--尾部开始-->
	<div align="center" class="footer">
       <a href="#">kuku</a> designed by Chiu & Cheung_x <br>Copyright © 2018 <br>
	</div>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<c:url value='https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js'/>"></script>
   <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value='jsps/js/bootstrap.min.js'/>"></script>
</body>
</html>