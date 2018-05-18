<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>我收到的评论</title>
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
						<li><a href="question" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">问答</a></li>
						<li><a href="activity" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">活动</a></li>
						<li><a href="resource_upload" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">上传资源</a></li>
						<li><a href="resource_download" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">下载资源</a></li>
					</ul>
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" />
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
						<li><a href="#" style="color: green"><%=session.getAttribute("account")%></a>
							<%
								}
							%>
						<li><a href="logout" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">注销</a></li>
					</ul>
				</div>
				</nav>
			</div>
			<div class="row clearfix">
				<div class="col-md-1 column"></div>
				<div class="col-md-10 column" style="background-color: white">
					<nav class="navbar navbar-default" role="navigation">
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a href="write_article" style="color: black;"
								onmouseover="mouseover(this)" onmouseout="mouseout(this)">发布文章</a></li>
							<li><a href="article_management" style="color: black;"
								onmouseover="mouseover(this)" onmouseout="mouseout(this)">文章管理</a></li>
							<li><a href="comment_management" style="color: black"
								onmouseover="mouseover(this)" onmouseout="mouseout(this)">评论管理</a></li>
							<li><a href="userInfo_management" style="color: black;"
								onmouseover="mouseover(this)" onmouseout="mouseout(this)">个人信息管理</a></li>
						</ul>
					</div>
					</nav>
					<div style="height: 70px">
						<a href="comment_management" style="color: green;">我收到的评论</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="mycomment_management" style="color: green;">我发表的评论</a>
						<hr style="height: 1px;" color="#BDBDBD" />
					</div>
					<div>
						<c:forEach items="${comments }" var="comment">
							<div style="height: 30px">
								<label>${comment.observerAccount }</label>&nbsp;&nbsp;&nbsp;&nbsp;
								<fmt:formatDate value="${comment.commentTime }" type="date"
									pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;&nbsp;&nbsp;
								<span>回复了你关于文章</span>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="article_details?articleId=${comment.articleId}">${comment.articleName }</a>
							</div>
							<div style="height: 35px">${comment.commentContent }</div>
							<hr style="border: 1px dashed #BDBDBD" />
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-md-4 column"></div>
			<div class="col-md-8 column">
				<ul class="pagination">
					<li><a href="${APP_PATH }/article_management?pn=1">首页</a></li>
					<c:if test="${pageInfo.hasPreviousPage}">
						<li><a
							href="${APP_PATH }/article_management?pn=${pageInfo.pageNum-1}"
							aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					</c:if>
					<c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
						<c:if test="${page_Num  == pageInfo.pageNum }">
							<li class="active"><a href="#">${page_Num }</a></li>
						</c:if>
						<c:if test="${page_Num != pageInfo.pageNum }">
							<li><a
								href="${APP_PATH }/article_management?pn=${page_Num }">${page_Num }</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${pageInfo.hasNextPage}">
						<li><a
							href="${APP_PATH }/article_management?pn=${pageInfo.pageNum+1}"
							aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
					</c:if>

					<li><a
						href="${APP_PATH }/article_management?pn=${pageInfo.pages}">末页</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>