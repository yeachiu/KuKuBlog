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
	
	<title>信息管理</title>

	<!-- Bootstrap -->
    <link href="<c:url value='/jsps/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
    
	<!--my-style-->
	<link href="<c:url value='/jsps/css/manage.css'/>" rel="stylesheet">

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
					<li class="list-group-item active"><a href="<c:url value='/MUserServlet?method=loadUser'/>"><span>&nbsp;&nbsp;修改资料</a></li>
  					<li class="list-group-item"><a href="<c:url value=''/>"><span>&nbsp;&nbsp;更换头像</a></li>
  					<li class="list-group-item"><a href="<c:url value='/jsps/manage/user/editpass.jsp'/>"><span>&nbsp;&nbsp;账号设置</a></li>

				</ul>
        	</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 " id="main">
				<!-- 动态传内容块 -->
					<div class="panel-body panel-default editmess">
						<div  class="text-success msgbox">
							<h4>${msg}</h4>
						</div>
						<div  class="errorbox">
							<h4>${error}</h4>
						</div>
						<form class="form-horizontal" action="/MUserServlet" method="post" id="editForm">
							<input type="hidden" method="updateUserMessage"/>
							<input type="hidden" name="userId" value="${getuser.userId }${user.userId}"/>
							<div class="form-group">
								<label for="userName" class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-10">
							      <input type="text" class="form-control" id="userName" name="userName" value="${getuser.userName }${user.userName}"/>
							    </div>  	
							</div>
							<div class="form-group">
			            		<label for="introduction" class="col-sm-2 control-label">个人简介</label>
								<input type="hidden" id="introduction" name="introduction" >
								<div id="editor" class="col-sm-10">${getuser.introduction }${user.introduction}</div>
							</div>
							<div class="form-group">
							    <div class="col-sm-offset-2 col-sm-10">
							      <button class="btn btn-primary btn-lg" id="save" type="button">保存</button>
							    </div>
							</div>
						</form>
					</div>

					    
			</div>
		</div>
	</div>
	<div align="center" class="footer">
         designed by Chiu & Cheung_x <br>Copyright © 2018 <br>
    </div>
<script src="<%=request.getContextPath() %>/jquery/jquery-3.3.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    
    <script src="<%=request.getContextPath() %>/jquery/bootstrap.min.js"></script>
    <!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
    <script type="text/javascript" src="<c:url value='/jsps/js/wangEditor.min.js'/>"></script>
    <script type="text/javascript">
    	 var E = window.wangEditor;
    	var editor = new E('#editor');
   	 	editor.create();
    	document.getElementById('save').addEventListener('click', function () {
	        // 读取 html
	    	$("#introduction").attr("value",editor.txt.html());
	    	editmess();
	    }, false);  
	    function  editmess()	{
		$("#editForm").removeAttr("action");
		$("#editForm").attr("action","<c:url value='/MUserServlet?method=updateUserMessage'/>");
		$("#editForm").submit();
	}	
    </script>
</body>
</html>	    
    
    

