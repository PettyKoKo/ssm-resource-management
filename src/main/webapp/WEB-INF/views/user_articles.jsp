<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的博客</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<link
	href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/static/js/jquery-1.12.4.min.js"></script>
<script src="static/js/mouse.js"></script>
</head>
<body>
	<div class="container" style="background-color: #F2F2F2">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation">
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="index" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">首页</a></li>
						<li><a href="user_articles" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">我的博客</a></li>
						<li><a href="all_articles" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">博客</a></li>
						<li><a href="resource_upload" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">上传资源</a></li>
						<li><a href="resource_download" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">下载资源</a></li>
					</ul>
					<form class="navbar-form navbar-left" role="search" action="search_article" method="post">
						<div class="form-group">
							<input type="text" class="form-control" name="search_name" placeholder="请输入搜索内容"/>
						</div>
						<button type="search" class="btn btn-default">搜索</button>
					</form>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="write_article" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">写博客</a></li>
						<li><a href="about" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">关于我们</a></li>
						<%
							if (session.getAttribute("account") == null) {
						%>
						<li><a href="login" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">登录</a></li>
						<%
							} else {
						%>
						<li><a href="userInfo_management" style="color: green"><%=session.getAttribute("account")%></a>
							<%
								}
							%>
						<li><a href="logout" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">注销</a></li>
					</ul>
				</div>

				</nav>
			</div>
		</div>
		<div class="col-md-1 column"></div>
		<div class="col-md-10 column" style="background-color: white">
			<c:forEach items="${pageInfo.list }" var="article">
				<div style="height: 20px"></div>
				<div style="height: 55px">
					<c:if test="${article.publishType =='原创'}">
						<label style="color: red;">${article.publishType }</label>
					</c:if>
					<c:if test="${article.publishType =='转载'}">
						<label style="color: green">${article.publishType }</label>
					</c:if>
					<c:if test="${article.publishType =='翻译' }">
						<label style="color: blue">${article.publishType }</label>
					</c:if>
					&nbsp;&nbsp;&nbsp;<a
						href="${APP_PATH }/article_details?articleId=${article.articleId}"
						onmouseover="mouseover(this)" onmouseout="mouseout(this)"
						style="font-size: 25px; color: black; text-decoration: none">${article.articleName }</a><br>
				</div>
				<div style="height: 60px">
					<c:if test="${fn:length(article.plantext)>150}">
					${fn:substring(article.plantext, 0,150)}
				</c:if>
					<c:if test="${fn:length(article.plantext)<=150}">
					 ${article.plantext}
				</c:if>
					...
				</div>
				<div style="height: 60px">
					<a href="#" style="color: red">${article.authorAccount }</a>&nbsp;&nbsp;&nbsp;
					<fmt:formatDate value="${article.finishTime }" type="date"
						pattern="yyyy-MM-dd HH:mm:ss" />
					<span style="float: right;">评论数：${article.tread }条&nbsp;&nbsp;&nbsp;</span>
					<span style="float: right;">阅读数：${article.praise }次&nbsp;&nbsp;&nbsp;</span>
					<hr style="height: 1px;" color="#BDBDBD" />
				</div>
			</c:forEach>

		</div>
		<div class="col-md-1 column"></div>
		<div class="col-md-4 column"></div>
		<div class="col-md-8 column">
			<ul class="pagination">
				<li><a href="${APP_PATH }/user_articles?pn=1">首页</a></li>
				<c:if test="${pageInfo.hasPreviousPage}">
					<li><a
						href="${APP_PATH }/user_articles?pn=${pageInfo.pageNum-1}"
						aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				</c:if>
				<c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
					<c:if test="${page_Num  == pageInfo.pageNum }">
						<li class="active"><a href="#">${page_Num }</a></li>
					</c:if>
					<c:if test="${page_Num != pageInfo.pageNum }">
						<li><a href="${APP_PATH }/user_articles?pn=${page_Num }">${page_Num }</a></li>
					</c:if>
				</c:forEach>
				<c:if test="${pageInfo.hasNextPage}">
					<li><a
						href="${APP_PATH }/user_articles?pn=${pageInfo.pageNum+1}"
						aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
				</c:if>

				<li><a href="${APP_PATH }/user_articles?pn=${pageInfo.pages}">末页</a></li>
			</ul>
		</div>
	</div>
</body>
</html>