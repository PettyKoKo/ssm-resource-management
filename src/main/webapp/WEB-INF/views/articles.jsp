<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8" />
<title>我的文章</title>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/static/js/jquery-1.12.4.min.js"></script>
</head>

<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span><span
							class="icon-bar"></span><span class="icon-bar"></span><span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index" style="color:black">首页</a>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="articles" style="color:black">我的文章</a></li>
						<li><a href="forum" style="color:black">论坛</a></li>
						<li><a href="question" style="color:black">问答</a></li>
						<li><a href="activity" style="color:black">活动</a></li>
						<li><a href="resource_upload" style="color:black">上传资源</a></li>
						<li><a href="resource_download" style="color:black">下载资源</a></li>
					</ul>
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" />
						</div>
						<button type="search" class="btn btn-default">搜索</button>
					</form>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="write_article" style="color:black">写文章</a></li>
						<li><a href="about" style="color:black">关于我们</a></li>
						<%if(session.getAttribute("account")==null){ %>
						<li><a href="login" style="color:black">登录</a></li>
						<%}else{ %>
						<li><a href="#" style="color:red"><%=session.getAttribute("account")%></a>
						<%} %>
						<li><a href="logout" style="color:black">注销</a></li>
					</ul>
				</div>

				</nav>
			</div>
		</div>
		<div class="col-md-4 column"></div>
		<div class="col-md-8 column">
			<ul class="pagination" >
				<li>
					<a href="#">首页</a>
				</li>
				<li>
					 <a href="#">上一页</a>
				</li>
				<li>
					 <a href="#">1</a>
				</li>
				<li>
					 <a href="#">2</a>
				</li>
				<li>
					 <a href="#">3</a>
				</li>
				<li>
					 <a href="#">4</a>
				</li>
				<li>
					 <a href="#">5</a>
				</li>
				<li>
					 <a href="#">下一页</a>
				</li>
				<li>
					 <a href="#">末页</a>
				</li>
			</ul>
		</div>
		</div>
	</div>
</body>
</html>