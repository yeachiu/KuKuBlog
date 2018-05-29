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
	
	<title>内容管理</title>

	<!-- Bootstrap -->
    <link href="<c:url value='/jsps/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
    
	<!--my-style-->
	<link href="<c:url value='/jsps/css/main.css'/>" rel="stylesheet">
	<link href="<c:url value='/jsps/css/manage.css'/>" rel="stylesheet">
	<script src="<%=request.getContextPath() %>/jsps/js/manage.js"></script>
	
</head>
<body onload="loading1();" >
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
					<li class="list-group-item active"><a href="<c:url value='/jsps/manage/manage.jsp'/>"><span>&nbsp;&nbsp;文章管理</span></a></li>
  					<li class="list-group-item"><a href="<c:url value='/jsps/manage/tag/mantag.jsp'/>"><span>&nbsp;&nbsp;标签管理</a></li>
  					<li class="list-group-item"><a href="<c:url value=''/>"><span>&nbsp;&nbsp;评论管理</a></li>
					  
					<li class="list-group-item">
					  	<span class="glyphicon glyphicon-pencil"></span>
					    个人信息管理
					</li>
					<li class="list-group-item"><a href="<c:url value='/MUserServlet?method=loadUser'/>"><span>&nbsp;&nbsp;修改资料</a></li>
  					<li class="list-group-item"><a href="<c:url value=''/>"><span>&nbsp;&nbsp;更换头像</a></li>
  					<li class="list-group-item"><a href="<c:url value='/jsps/user/editpass.jsp'/>"><span>&nbsp;&nbsp;账号设置</a></li>

				</ul>
        	</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 " id="main">
				<!-- 动态传内容块 -->
				<%@ include file="blog/manblog.jsp" %>
					    
			</div>
		</div>
		<div id="msg" style="display:none;"></div>
	</div>
	
	<%@ include file="/jsps/foot.jsp" %>
	<script src="<%=request.getContextPath() %>/jquery/jquery-3.3.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    
    <script src="<%=request.getContextPath() %>/jquery/bootstrap.min.js"></script>
    
</body>
</html>