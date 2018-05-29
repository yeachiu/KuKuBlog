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
	<link href="<c:url value='/jsps/css/manage.css'/>" rel="stylesheet">
	<link href="<c:url value='/jsps/css/main.css'/>"  rel="stylesheet">
	<link href="<c:url value='/jsps/css/left.css'/>"  rel="stylesheet">

</head>
<body>
  <!-- left.jsp -->
  <ul class="list-group" id="leftlist">
    <li class="list-group-item">
    <img src="<c:url value='/jsps/images/p27017001.jpg'/>" width="100" height="100" class="img-responsive img-circle" alt="Generic placeholder thumbnail">
      <h4>CXMM</h4>
      <span class="text-muted"> demmo@163.com</span>
    </li>
    <li class="list-group-item active">
        <a href="#main"  data-toggle="tab" onclick="findPublishedBlog1();">
        	<span class="glyphicon glyphicon-home">&nbsp;&nbsp;主页</span>
        </a>
    </li>
    <li class="list-group-item">
    	<a href="#blog"  data-toggle="tab" onclick="findPublishedBlog2();">
      		<span class="glyphicon glyphicon-book">&nbsp;&nbsp;文章</span>
      	</a>
    </li>
    <li class="list-group-item">
          <a href="#tag"  data-toggle="tab">
          	<span class="glyphicon glyphicon-tags" onclick="findAllTag1();">&nbsp;&nbsp;标签 </span>
          </a>
    </li>
    <li class="list-group-item">
		<a href="#aboutme"  data-toggle="tab">
          <span class="glyphicon glyphicon-user" onclick="aboutme();">&nbsp;&nbsp;关于我</span>
        </a>
    </li>  
  </ul>

</body>
</html> 
