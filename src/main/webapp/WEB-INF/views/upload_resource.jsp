<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>上传资源</title>
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
<script src="static/js/upload_resource.js"></script>
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
			<div class="row clearfix">
				<div class="col-md-1 column"></div>
				<div class="col-md-10 column" style="background-color: white">
					<nav class="navbar navbar-default" role="navigation">
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a href="resource_upload" style="color: black;"
								onmouseover="mouseover(this)" onmouseout="mouseout(this)">上传资源</a></li>
							<li><a href="my_upload_resource" style="color: black;"
								onmouseover="mouseover(this)" onmouseout="mouseout(this)">我上传的资源</a></li>
						</ul>
					</div>
					</nav>
					<form action="file_upload" method="post" enctype="multipart/form-data">
					<div>
						<div class=col-md-3 column>
						<div style="height:30px"></div>
						<input type="file" id="upload" name="upload"
										style="width: 200px">
									<span style="color: red" id="upload_msg"></span><br> <input
										type="submit" class="btn btn-default" value="上传"
										style="width: 160px; background: red; color: white;" />
						
						</div>
						<div class=col-md-4 column>
							<label>资源名称</label><span style="color: red" id="resource_name_msg"></span>
							<input type="text" class="form-control" id="resource_name" name="resource_name"><br>
							<label>资源类型</label><span style="color: red" id="resource_type_msg"></span>
							<input type="text" class="form-control" id="resource_type" name="resource_type"><br>
							<input type="text" id="uploader" name="uploader" value='<%=session.getAttribute("account")%>'
										style="display: none">
						</div>
						<div class=col-md-4 column>
						<div style="height:78px"></div>
						<label>资源描述</label><span style="color: red" id="resource_describe_msg"></span>
							<input type="text" class="form-control" id="resource_describe" name="resource_describe"><br>
						</div>
					</div>
					</form>
					</div>
					<div class="col-md-1 column"></div>
					<div class="col-md-4 column"></div>
					<div class="col-md-8 column" style="height: 80px"></div>
				
			</div>
		</div>
	</div>
</body>
</html>