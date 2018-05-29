<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<body>
	<div class="heading"><h4>文章管理</h4></div>
	<h3>${changsStatus }</h3>
  <ul id="myTab" class="nav nav-tabs">
     <li class="active">
        <a href="#all" data-toggle="tab">全部</a>
     </li>
     <li><a href="#published" data-toggle="tab">已发布</a></li>
     <li>
        <a href="#draft" data-toggle="tab">草稿箱</a>
     </li>
  </ul>
  <div id="myTabContent" class="tab-content">
     <div class="tab-pane fade in active" id="all">
        <div class="table-responsive bloglist">
        <table class="table" id="allblist">
            <!-- <tr>
            <th>排序</th>
            <th>标题</th>
            <th>创建时间</th>
            <th>发布状态</th>
            <th>操作</th>
          </tr>
          <c:forEach items="${findAllBlog}" var="allblog">
          <tr>
          <td></td>
          <td class="blogTitle"><a href="article.html">${allblog.blogTitle } </a></td>
          <td class="blogTime">${allblog.blogTime }</td>
          <td class="status">
            
            <c:choose>
            	<c:when test="${allblog.status}">已发布</c:when>
              	<c:when test="${!allblog.status}">草稿</c:when>
            </c:choose>
           
          </td>
          <td> 
          <a href="#edit">编辑</a>
          <a href="#delete">删除</a>
        </tr>

        </c:forEach>-->
        
        </table>
      </div>
     </div>
     <div class="tab-pane fade" id="published">
        <div class="table-responsive bloglist">
        <table class="table"id="pubblist">
          <!--   <tr>
            <th>排序</th>
            <th>标题</th>
            <th>创建时间</th>
            <th>是否发布</th>
            <th>操作</th>
          </tr>
          <c:forEach items="${findPubBlog}" var="blog">
          <tr>
          <td class="">···</td>
          <td class="blogTitle"><a href="article.html">${blog.blogTitle } </a></td>
          <td class="blogTime">${blog.blogTime }</td>
          <td class="status">
            <label class="checkbox-inline">
              <input value="${blog.status}">
              <input type="checkbox" id="check">发布
            </label>
          </td>
          <td> 
          <a href="#edit">编辑</a>
          <a href="#delete">删除</a>
          </td>
        </tr>
        </c:forEach>-->
        </table>
      </div>
     </div>
     <div class="tab-pane fade" id="draft">
        <div class="table-responsive bloglist">
        <table class="table" id="drablist">
         <!-- <tr>
            <th>排序</th>
            <th>标题</th>
            <th>创建时间</th>
            <th>发布状态</th>
            <th>操作</th>
          </tr>
            <c:forEach items="${findDraftBlog}" var="blog">
          <tr>
          <td class="">···</td>
          <td class="blogTitle"><a href="article.html">${blog.blogTitle } </a></td>
          <td class="blogTime">${blog.blogTime }</td>
          <td class="status">
            <label class="checkbox-inline">
              <input value="${blog.status}">
              <input type="checkbox" id="check">发布
            </label>
          </td>
          <td> 
          <a href="#edit">编辑</a>
          <a href="#delete">删除</a>
          </td>
        </tr>
        </c:forEach>-->
        </table>
      </div>
     </div>
  </div>
