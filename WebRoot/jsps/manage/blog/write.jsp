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

    <title>写博客</title>
    <script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script src="<c:url value='/jsps/js/write.js'/>"></script>
	
    <!-- Bootstrap -->
    <link href="<c:url value='/jsps/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
	<!--my-style-->
	<link href="<c:url value='/jsps/css/main.css'/>"  rel="stylesheet">
	<link href="<c:url value='/jsps/css/blog/write.css'/>" rel="stylesheet">

</head>
<body>
	<%@ include file="/jsps/top.jsp" %>
	<div class="container">
		<div class="panel-body panel-default writeblog">
			<div  class="text-danger error">
				<h4 class="error" id="blogTitleError">${errors.blogTitle}</h4>
			</div>
			<form class="form-horizontal" action="MBlogServlet" method="post" id="wblogForm">
				<input type="hidden" id="target" method="addBlog"> 
				<div class="form-group input-group-lg">
				  <input type="text" class="form-control" placeholder="输入文章标题" id="blogTitle" name="blogTitle"/>
				</div>
				<input type="hidden" id="blogContent" name="blogContent">
				<div id="editor"></div>
				<div class="settag">
					<label>文章标签：</label>
					<div class="blogtags" id="blogtags">
						<input type="hidden" id="tagNames" name="tagNames" value="">
						<c:forEach items="${tagofblog}" var="tag">
							<!--  <input type="hidden" name="tagId" value="${tag.tagId}"> -->
							<span class="label label-info" id="${tag.tagId}"><span class="ttag">${tag.tagname}</span><span class="glyphicon glyphicon-remove text-danger remove"></span></span>
						</c:forEach>
					</div> 
				  	<div class="form-inline  input-group-sm">
				    	<label for="InputName0"><small>添加标签：</small></label>
				    	<input type="text" class="form-control" id="InputName0" placeholder="输入标签...">
				        <a class="btn btn-default btn-sm" href="#addtag" id="addtag" type="button">添加</a>
				  	</div>
				  	<div class="tijiao">
					<input class="btn btn-primary btn-lg " id="tijiao1"  type="button" value="发布博客">
					<input class="btn btn-success btn-lg " id="tijiao2"  type="button" value="保存草稿">
					</div>
				</div> 
			</form>
		</div>
	</div>
	<%@ include file="/jsps/foot.jsp" %>
	<script type="text/javascript" src="<c:url value='/jsps/js/wangEditor.min.js'/>"></script>
    <script type="text/javascript">
    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片
    editor.create();
    
    document.getElementById('tijiao1').addEventListener('click', function () {
    	// 获取标签集字符串
    	var ttag="";
    	$(".ttag").each(function(i,ele){
    		if(i<5){
    			ttag = ttag + $(ele).text() + ",";
    		}
    	})
    	$("#tagNames").attr("value",ttag);
    	// 读取 html
    	$("#blogContent").attr("value",editor.txt.html());
    	publishblog();
    }, false);

    document.getElementById('tijiao2').addEventListener('click', function () {
    	// 获取标签集字符串
    	var ttag="";
    	$(".ttag").each(function(i,ele){
    		if(i<5){
    			ttag = ttag + $(ele).text() + ",";
    		}
    	})
    	$("#tagNames").attr("value",ttag);
    	// 读取 html
    	$("#blogContent").attr("value",editor.txt.html());
    	savedraft();
    }, false);
    
    function  publishblog()	{
		$("#wblogForm").removeAttr("action");
		$("#wblogForm").attr("action","<c:url value='/MBlogServlet?method=addBlog'/>");
		$("#wblogForm").submit();
	}
	function  savedraft() {
		$("#wblogForm").removeAttr("action");
		$("#wblogForm").attr("action","<c:url value='/MBlogServlet?method=saveDrafts'/>");
		$("#wblogForm").submit();
	}
</script>
   
	<script src="<%=request.getContextPath() %>/jquery/jquery-3.3.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    
    <script src="<%=request.getContextPath() %>/jquery/bootstrap.min.js"></script>

</body>
</html>
<!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
    
				
