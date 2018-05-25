<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>个人详细信息</title>
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
<script src="static/js/image_upload.js"></script>
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
						<a href="userInfo_management" style="color: green;">个人详细信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="change_userInfo" style="color: green;">修改信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="change_password" style="color: green;">更换密码</a>
						<hr style="height: 1px;" color="#BDBDBD" />
					</div>
					<div>
						<div class="col-md-4 column">
							<div style="height: 30px"></div>
							<center>
								<img alt="头像" src="${userInfo.imageurl}"
									style="width: 150px; height: 150px;" />
							</center>
							<br>
							<center>
								<form action="upload_image" method="post"
									enctype="multipart/form-data">
									<span>更换个人头像</span> <input type="file" id="upload" name="upload"
										style="width: 200px"
										accept="image/gif,image/jpeg,image/jpg,image/png,image/svg">
									<span style="color: red" id="image-msg"></span><br> <input
										type="submit" class="btn btn-default" value="上传"
										style="width: 160px; background: red; color: white;" />
								</form>
							</center>
							<div style="height: 30px"></div>
						</div>
						<div class="col-md-8 column">
							<div style="height: 30px"></div>
							<div style="font-size: 16px;">
								<span>账号：</span><input type="text"
									value="${userInfo.userAccount}"
									style="border: none; outline: none;" readonly="readonly" /> <span>姓名：</span><input
									type="text" value="${userInfo.userName}"
									style="border: none; outline: none;" readonly="readonly" /><br>
								<span>职位：</span><input type="text" value="${userInfo.position}"
									style="border: none; outline: none;" readonly="readonly" /> <span>邮箱：</span><input
									type="text" value="${userInfo.email}"
									style="border: none; outline: none;" readonly="readonly" /><br>
								<span>手机号：</span><input type="text"
									value="${userInfo.telephone}"
									style="border: none; outline: none;" readonly="readonly" /><br>
								<br>
							</div>
							<div style="font-size: 16px;">
								<span>原创：</span><input type="text" value="${userInfo.original}篇"
									style="border: none; outline: none;" readonly="readonly" /> <span>转载：</span><input
									type="text" value="${userInfo.reprint}篇"
									style="border: none; outline: none;" readonly="readonly" /><br>
								<span>翻译：</span><input type="text"
									value="${userInfo.translate}篇"
									style="border: none; outline: none;" readonly="readonly" /><br>
								<br> <span>访问：</span><input type="text"
									value="${userInfo.visit}次" style="border: none; outline: none;"
									readonly="readonly" /> <span>评论：</span><input type="text"
									value="${userInfo.discuss}条"
									style="border: none; outline: none;" readonly="readonly" /><br>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-1 column"></div>
				<div class="col-md-4 column"></div>
				<div class="col-md-8 column" style="height: 80px"></div>

			</div>
		</div>
	</div>
</body>
</html>