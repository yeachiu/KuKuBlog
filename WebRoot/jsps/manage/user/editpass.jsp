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

    <title>账号设置</title>
	<!-- Bootstrap -->
    <link href="<c:url value='/jsps/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
    
	<!--my-style-->
	
  	<link href="<c:url value='/jsps/css/user/form-editPwd.css'/>" rel="stylesheet">
  	<link href="<c:url value='/jsps/css/manage.css'/>" rel="stylesheet">
  	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script src="<c:url value='/jsps/js/manage.js'/>"></script>
	<script src="<c:url value='/jsps/js/edituser.js'/>"></script>

  </style>
</head>
<body>
	<%@ include file="/jsps/manage/mtop.jsp" %>
	
	<!-- 文章主体 -->
	<div class="container layout">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<!-- left.jsp -->
		        <ul class="list-group">
		        	<li class="list-group-item">
					  	<span class="glyphicon glyphicon-pencil">	</span>
					  	内容管理  	 
					</li>
					
					<li class="list-group-item"><a href="<c:url value='/jsps/manage/manage.jsp'/>"><span>&nbsp;&nbsp;文章管理</span></a></li>
  					<li class="list-group-item"><a href="<c:url value='/jsps/manage/tag/mantag.jsp'/>"><span>&nbsp;&nbsp;标签管理</a></li>
  					<li class="list-group-item"><a href="<c:url value=''/>"><span>&nbsp;&nbsp;评论管理</a></li>
					  
					<li class="list-group-item">
					  	<span class="glyphicon glyphicon-pencil"></span>
					    个人信息管理
					</li>
					<li class="list-group-item"><a href="<c:url value='/MUserServlet?method=loadUser'/>"><span>&nbsp;&nbsp;修改资料</a></li>
  					<li class="list-group-item"><a href="<c:url value=''/>"><span>&nbsp;&nbsp;更换头像</a></li>
  					<li class="list-group-item active"><a href="<c:url value='/jsps/manage/user/editpass.jsp'/>"><span>&nbsp;&nbsp;账号设置</a></li>

				</ul>
        	</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 " id="main">
				<!-- 动态传内容块 -->
				<c:if test="${!empty msg}">
				<div  class="text-danger error" id="msg">
					<span class="errors" style="LINE-HEIGHT: 30PX;    margin-left: 3%;">${msg}</span>
				</div>
				</c:if>
			    <form class="form-editPwd" action="<c:url value='/MUserServlet'/>" method="post">
			    <input type="hidden" name="method" value="updatePassword" /><!-- 用于访问Servlet中特定请求处理方法 -->
			      <h2 class="form-editPwd-heading">修改密码</h2>
			      <label for="oldPwd" class="sr-only">当前密码</label>
			      <input type="password" name="userPwd" id="userPwd" class="form-control" placeholder="当前密码" value="${user.userPwd }">
			      <label for="newPwd" class="sr-only">Password</label>
			      <input type="password"  name="newloginpass" id="newloginpass" class="form-control" placeholder="新密码" value="${user.newloginpass }">
			      <label for="newPwd" class="sr-only">Password</label>
			      <input type="password"  name="reloginpass" id="reloginpass" class="form-control" placeholder="重复输入新密码" value="${user.reloginpass }">
			      <button class="btn btn-lg btn-primary btn-block" type="submit" id="submit">提交</button>
			    </form>  
 			</div>
		</div>
	</div>
	<div align="center" class="footer">
         designed by Chiu & Cheung_x <br>Copyright © 2018 <br>
    </div>
	<script src="<%=request.getContextPath() %>/jquery/jquery-3.3.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed --> 
    <script src="<%=request.getContextPath() %>/jquery/bootstrap.min.js"></script>
</body>
</html>	    