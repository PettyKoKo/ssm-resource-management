<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>${ArticleInfo.articleName }</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="static/js/jquery-1.12.4.min.js"></script>
<script src="static/js/publish_comment.js"></script>
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
						<li><a href="#" style="color:green"><%=session.getAttribute("account")%></a>
							<%
								}
							%>
						<li><a href="logout" style="color: black"
							onmouseover="mouseover(this)" onmouseout="mouseout(this)">注销</a></li>
					</ul>
				</div>

				</nav>
				<div class="col-md-1 column"></div>
				<div class="col-md-10 column" style="background-color: white">
					<div style="height: 20px"></div>
					<div style="height: 55px">
						<c:if test="${ArticleInfo.publishType =='原创'}">
							<label style="color: red;">${ArticleInfo.publishType }</label>
						</c:if>
						<c:if test="${ArticleInfo.publishType =='转载'}">
							<label style="color: green">${ArticleInfo.publishType }</label>
						</c:if>
						<c:if test="${ArticleInfo.publishType =='翻译' }">
							<label style="color: blue">${ArticleInfo.publishType }</label>
						</c:if>
						&nbsp;&nbsp;&nbsp;<label style="font-size: 25px">${ArticleInfo.articleName }</label><br>
					</div>
					<div style="height: 60px">
						<a href="#" style="color: red">${ArticleInfo.authorAccount }</a>&nbsp;&nbsp;&nbsp;
						<fmt:formatDate value="${ArticleInfo.finishTime }" type="date"
							pattern="yyyy-MM-dd HH:mm:ss" />
						<span style="float: right;">阅读数：${ArticleInfo.tread }&nbsp;&nbsp;&nbsp;</span>
						<hr style="height: 1px;" color="#BDBDBD" />
					</div>
					${ArticleInfo.articleContent }
					<hr style="height: 1px;" color="#BDBDBD" />
				</div>
				<div class="col-md-1 column"></div>
			</div>
		</div>

		<div class="col-md-1 column"></div>
		<div class="col-md-10 column" style="background-color: white">
			<form method="post" action="comment_article">
				<div style="height: 90px;">
					<textarea name="comment_content" id="comment_content"
						style="height: 80px" "
						class="form-control"
						placeholder="想对作者说点什么"></textarea>
					<span style="color: red" id="comment_content_msg" class="error"></span>
				</div>
				<div style="height: 40px;">
					<input type="submit" class="btn btn-default"
						style="width: 150px; background: red; color: white; float: right"
						value="发表评论" /> <input type="text" id="observer_account"
						name="observer_account" style="display: none"
						value="<%=session.getAttribute("account")%>"> <input
						type="text" id="article_Id" name="article_Id"
						style="display: none" value="${ArticleInfo.articleId}">
				</div>
				<hr style="height: 1px;" color="#BDBDBD" />
			</form>
			<div>
				<c:forEach items="${comments }" var="comment">
					<div>
						<div style="height: 30px">
							<label>${comment.observerAccount }</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<fmt:formatDate value="${comment.commentTime }" type="date"
								pattern="yyyy-MM-dd HH:mm:ss" />
							<a href="${APP_PATH }/deleteComment?comment_id=${comment.commentId}&article_Id=${comment.articleId}">删除</a>
							<a style="cursor:pointer" onclick="reply()">回复</a>
						</div>
						<div style="height: 35px">${comment.commentContent }</div>
						<hr style="border: 1px dashed #BDBDBD" />
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="col-md-1 column"></div>
	</div>

</body>
</html>