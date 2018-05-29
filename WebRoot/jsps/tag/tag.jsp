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

	<!-- Bootstrap -->
    <link href="<c:url value='/jsps/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
    
	<!--my-style-->
	<link href="<c:url value='/jsps/css/main.css'/>"  rel="stylesheet">
	<link href="<c:url value='/jsps/css/left.css'/>"  rel="stylesheet">
	<link href="<c:url value='/jsps/css/right.css'/>"  rel="stylesheet">
	<link href="<c:url value='/jsps/css/bloglist.css'/>"  rel="stylesheet">
    <script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/calendar.js'/>"></script>
	<script src="<c:url value='/jsps/js/method.js'/>"></script>

</head>
<body onload="findPublishedBlog1();" >
	<%@ include file="/jsps/top.jsp" %>
	
	<!-- 页面主体 -->
	<div class="container layout">
		<div class="row">
			<div class="col-sm-2 col-md-2 sidebar">
				<!-- left.jsp -->
				<ul class="list-group" id="leftlist">
				    <li class="list-group-item">
				    <img src="<c:url value='/jsps/images/p27017001.jpg'/>" width="100" height="100" class="img-responsive img-circle" alt="Generic placeholder thumbnail">
				      <h4>CXMM</h4>
				      <span class="text-muted">demmo@163.com</span>
				    </li>
				    <li class="list-group-item">
				        <a href="#main"  data-toggle="tab" onclick="tomain();">
				        	<span class="glyphicon glyphicon-home">&nbsp;&nbsp;主页</span>
				        </a>
				    </li>
				    <li class="list-group-item">
				    	<a href="#blog"  data-toggle="tab" onclick="tomain();">
				      		<span class="glyphicon glyphicon-book">&nbsp;&nbsp;文章</span>
				      	</a>
				    </li>
				    <li class="list-group-item active">
				          <a href="#tag"  data-toggle="tab" onclick="tomain();">
				          	<span class="glyphicon glyphicon-tags" onclick="findAllTag();">&nbsp;&nbsp;标签 </span>
				          </a>
				    </li>
				    <li class="list-group-item">
						<a href="#aboutme"  data-toggle="tab" onclick="tomain();">
				          <span class="glyphicon glyphicon-user">&nbsp;&nbsp;关于我</span>
				        </a>
				    </li>  
				  </ul>
        	</div>
			<div class="col-sm-10 col-sm-offset-2 col-md-10 col-md-offset-2 tab-content">
				<div class="tab-pane fade" id="main">
			     	<div class="row">
						<div class="col-sm-9 col-md-9 panel-default panel">
							<!-- 动态传内容块 -->
							<div id="bloglist1"></div>
						</div>
						<div class="col-sm-3 col-md-3 main-right">
							<!-- right.jsp -->
							<%@ include file="/jsps/right.jsp" %>
						</div>
					</div>
			   	</div>
			   	<div class="tab-pane fade" id="blog">
			   		<div id="bloglist2" style="display:none;"></div>	   			
					
			   	</div>
				<div class="tab-pane fade in active" id="tag">
					<h2 class="text-primary tagName" id="tit">${tag.tagname}</h2>
					<ul class="list-group panel panel-default" id="bloglist3">
					<c:forEach items="${blogsOfTag}" var="blog">
						<li class="list-group-item">
							<div class="panel-body">
								<span class="blogTime">${blog.blogTime}</span>
								<h3 class="blogTitle text-primary">${blog.blogTitle}</h3>
								<div class="blogContent">${blog.blogContent}</div>
								<a class="'blogtag setval'+${blog.blogId}" href="<c:url value='/BlogServlet?method=showblogbyid&blogId=${blog.blogId}'/>">
									<h6>【显示全文】</h6>
								</a><hr>
								
									<div class="blogtag">
									<c:forEach items="${blog.tags}" var="atag">
										<a href="<c:url value='/BlogServlet?method=findByTagId&tagId=${atag.tagId}'/>" class="label label-warning">${atag.tagname}</a>
									</c:forEach>
									</div>
								
							</div>
						</li>
						</c:forEach>
					</ul>	
				</div>
				<div class="tab-pane fade" id="aboutme"></div>			    
			</div>
		</div>
	</div>
	<div align="center" class="footer">
         designed by Chiu & Cheung_x <br>Copyright © 2018 <br>
    </div>

	<script type="text/javascript" src="<c:url value='jsps/js/calendar.js'/>"></script>
	<script type="text/javascript">
		function tomain(){
			location.href="<c:url value='/index.jsp'/>";
		}
	</script>
	
	<script src="<%=request.getContextPath() %>/jquery/jquery-3.3.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    
    <script src="<%=request.getContextPath() %>/jquery/bootstrap.min.js"></script>
    
</body>
</html>
