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
	
	<title>标签管理</title>

	<!-- Bootstrap -->
    <link href="<c:url value='/jsps/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
    
	<!--my-style-->
	<link href="<c:url value='/jsps/css/main.css'/>" rel="stylesheet">
	<link href="<c:url value='/jsps/css/manage.css'/>" rel="stylesheet">
	
	<script src="<c:url value='/jsps/js/manage.js'/>"></script>
	<style>

	</style>

</head>
</head>
<body onload="loading();">
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
  					<li class="list-group-item active"><a href="<c:url value='/jsps/manage/tag/mantag.jsp'/>"><span>&nbsp;&nbsp;标签管理</a></li>
  					<li class="list-group-item"><a href="<c:url value=''/>"><span>&nbsp;&nbsp;评论管理</a></li>
					  
					<li class="list-group-item">
					  	<span class="glyphicon glyphicon-pencil"></span>
					    个人信息管理
					</li>
					<li class="list-group-item"><a href="<c:url value='/MUserServlet?method=loadUser'/>"><span>&nbsp;&nbsp;修改资料</a></li>
  					<li class="list-group-item"><a href="<c:url value=''/>"><span>&nbsp;&nbsp;更换头像</a></li>
  					<li class="list-group-item"><a href="<c:url value='/jsps/manage/user/editpass.jsp'/>"><span>&nbsp;&nbsp;账号设置</a></li>

				</ul>
        	</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 " id="main">
				<!-- 动态传内容块 -->
				<div class="heading"><h4>标签管理</h4></div>
				<div class="form-inline input-group-sm" id="addtag1">
			    	<label for="InputName0"><small>添加标签：</small></label>
			    	<input type="text" class="form-control" id="InputName0" method="addTagname" placeholder="输入标签...">
				        <a class="btn btn-default btn-sm" href="#addtag" id="addtag" type="button">添加</a>
			  	</div>
			  	<div class="table-responsive bloglist">
					  <table class="table" id="mtagtable"></table>
				</div>
								    
			</div>
		</div>
		<div id="msg" style="display:none;"></div>
	</div>


	

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">修改标签</h4>
      </div>
      <div class="modal-body">
        <input type="text" class="form-control"  id="newtag">
       	<input type="hidden" class="form-control" id="theId"> 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="changetag();">Save changes</button>
      </div>
    </div>
  </div>
</div>

	<%@ include file="/jsps/foot.jsp" %>
    <script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script src="<%=request.getContextPath() %>/jquery/jquery-3.3.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
     <script src="<%=request.getContextPath() %>/jsps/js/bootstrap.js"></script>
     
    <script type="text/javascript">
		function changetag(){
			// location.href="https://www.baidu.com";
			var aaa=$("#newtag").val();
			alert(aaa);
			$('#myModal').modal('hide');
		}
	</script>	
</body>
</html>

    