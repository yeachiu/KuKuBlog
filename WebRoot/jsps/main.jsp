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
	
	<title>欢迎来到个人博客！</title>
	<script src="<%=request.getContextPath() %>/jquery/jquery-1.5.1.js"></script>
	<!-- Bootstrap -->
    <link href="<c:url value='/jsps/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
    
	<!--my-style-->
	<link href="<c:url value='/jsps/css/main.css'/>"  rel="stylesheet">
	<link href="<c:url value='/jsps/css/left.css'/>"  rel="stylesheet">
	<link href="<c:url value='/jsps/css/right.css'/>"  rel="stylesheet">
	<link href="<c:url value='/jsps/css/bloglist.css'/>"  rel="stylesheet">

	<script type="text/javascript" src="<c:url value='/jsps/js/calendar.js'/>"></script>
	<script src="<c:url value='/jsps/js/method.js'/>"></script>

</head>
<body onload="loading();" >
	<%@ include file="top.jsp" %>
	
	<!-- 页面主体 -->
	<div class="container layout">
		<div class="row">
			<div class="col-sm-2 col-md-2 sidebar">
				<!-- left.jsp -->
				<%@ include file="left.jsp" %>
        	</div>
			<div class="col-sm-10 col-sm-offset-2 col-md-10 col-md-offset-2 tab-content">
				<div class="tab-pane fade in active" id="main">
			     	<div class="row">
						<div class="col-sm-9 col-md-9 panel-default panel">
							<!-- 动态传内容块 -->
							<div id="bloglist1"></div>
						</div>
						<div class="col-sm-3 col-md-3 main-right">
							<!-- right.jsp -->
							<div class="panel panel-default">	
								<div class="panel-body">
								  	<div id="calen"></div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-body tags">
								  	<h5><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;标签</h5>
									<hr>
									<div id="righttags"></div>
								</div>
							</div>
						</div>
					</div>
			   	</div>
			   	<div class="tab-pane fade" id="blog">
			   		<div id="bloglist2">
			   				
						
					</div>
			   	</div>
				<div class="tab-pane fade" id="tag">
					<div id="showtag1">
						<h2 class="text-primary" id="tit">标签列表</h2>
						<div class="row" id="tagslist"></div>
					</div>
					<div id="showtag2">
						
					</div>
			   	</div>
			   	<div class="tab-pane fade" id="aboutme">
			   		<h3 id="amTit">关于我</h3>
			   		<div id="amCon"></div>
			   	</div>			    
			</div>
		</div>
	</div>
	<%@ include file="/jsps/foot.jsp" %>


	<script type="text/javascript" src="<c:url value='jsps/js/calendar.js'/>"></script>
	<script>
		//只需实例化calendar实例即可，传入的参数为html对象的id，举个例子如下
		new calendar('calen');
	</script>

	
	<script src="<%=request.getContextPath() %>/jquery/jquery-3.3.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
   
    
     <script src="<%=request.getContextPath() %>/jsps/js/bootstrap.js"></script>
    

    
</body>
</html>
