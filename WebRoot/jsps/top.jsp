<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<header>
		<!--Start||navbar-->
		<div class="nav">
		    <nav class="navbar navbar-fixed-top navbar-default affix-top" id="a-nav">
		        <div class="container">  	
					<div class="navbar-header">
					    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					        <span class="sr-only">Toggle navigation</span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					    </button>
					    <a class="navbar-brand" href="<c:url value='/index.jsp'/>">DEMMO</a>
					</div>
				    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				    	<!-- 手机上不可见 -->
				
				    	
				      	<form class="navbar-form navbar-right">
				    		<div class="dropdown">
								<button type="button" class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">
									<span class="glyphicon glyphicon-cog" aria-hidden="true"></span> 管理
								</button>
								<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
								<c:if test="${not empty sessionScope.sessionUser }">
									<li>
								       <a href="<c:url value='/index.jsp'/>">主页</a>            
								    </li>
								    <li>
								       <a href="<c:url value='/MUserServlet?method=loadUser'/>">我的</a>            
								    </li>
								    <li>
								       <a href="<c:url value='/jsps/manage/manage.jsp'/>">博客管理</a>            
								    </li>
								    <li role="separator" class="divider"></li>
								    <li>
								     	<a href="<c:url value='/MUserServlet?method=quit'/>">注销</a>
								    </li>
								</c:if>
								<c:if test="${empty sessionScope.sessionUser }">
									<li>
								       <a href="<c:url value='/index.jsp'/>">主页</a>            
								    </li>
								    <li>
							         <a href="#blog"  data-toggle="tab" onclick="findPublishedBlog2();">文章</a>            
							      	</li>
							      	<li>
							         <a href="#tag"  data-toggle="tab" onclick="findAllTag1();">标签</a>            
							     	</li>
							     	<li>
							         <a href="#aboutme"  data-toggle="tab" onclick="aboutme();">关于我</a>            
							     	</li>
							     </c:if>
								</ul>
							</div>
				    	</form>
				    	
				    	<ul class="nav navbar-nav navbar-right">
				    	<c:choose>
				    		<c:when test="${empty sessionScope.sessionUser }">
				    			<li>
						    		<a href="<c:url value='/jsps/user/log.jsp'/>">Sign in</a>
						    	</li>
				    		</c:when>
				    		<c:otherwise>
				    			<li>
				    				<a  href="<c:url value='/jsps/manage/blog/write.jsp'/>" >写博客</a>
				    			</li>
				    		</c:otherwise>
				    	</c:choose>	
				      	</ul>
				      
				    		
				    			<!-- 小屏幕视图  -->
						    	<ul class="dropdowninphone nav navbar-nav">
						    		<li>
								       <a href="<c:url value='/index.jsp'/>">主页</a>            
								    </li>
			    				  	<li>
							         <a href="#blog"  data-toggle="tab" onclick="findPublishedBlog2();">文章</a>            
							      	</li>
							      	<li>
							         <a href="#tag"  data-toggle="tab" onclick="findAllTag1();">标签</a>            
							     	</li>
							     	<li>
							         <a href="#aboutme"  data-toggle="tab" onclick="aboutme();">关于我</a>            
							     	</li>
							     	<c:if test="${not empty sessionScope.sessionUser }">
							      	<li>
							         <a href="<c:url value='/UserServlet?method=loadUser'/>">我的</a>            
							      	</li>
							      	<li>
							         <a href="<c:url value='/jsps/manage/manage.jsp'/>">博客管理</a>            
							      	</li>
							      	<hr>
							        <li>
							      	 <a href="<c:url value='/MUserServlet?method=quit'/>">注销</a>
							        </li>
							        </c:if>
								</ul>
				    		
				      	<form class="navbar-form navbar-right" action="<c:url value='/BlogServlet'/>" method="post">
				      		<input type="hidden" name="method" value="searchByKeywords">
				        	<div class="form-group">
				          		<input type="text" class="form-control" placeholder="Search" name="search">
				        	</div>
				        	<button type="submit" class="btn btn-default" >搜索</button>
				     	</form>
				    </div>
				</div>
			</nav>
		</div>
	</header>
