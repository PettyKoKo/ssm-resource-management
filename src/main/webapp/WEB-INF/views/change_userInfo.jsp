<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>修改个人信息</title>
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
<script src="static/js/userinfo.js"></script>
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
					<form method="post" action="update_userInfo" >
						<div>
							<div class="col-md-4 column">
								<div style="height: 30px"></div>
								<center>
									<img alt="头像" src="${userInfo.imageurl}"
										style="width: 200px; height: 200px; " />
								</center>
								<br>
								<div style="height: 80px"></div>
							</div>
							<div class="col-md-4 column">
								<div style="height: 30px"></div>
								<div>
									<label>姓名</label><span id="username-msg" style="color:red;"></span><input type="text" class="form-control" id="username" name="username"
										value="${userInfo.userName}" style="width: 200px" /> <br>
										<label>职位</label><span id="position-msg" style="color:red;"></span><input
										type="text" class="form-control" id="position" name="position" value="${userInfo.position}" 
										style="width: 200px" /><br><br><br><br><br>
								</div>
							</div>
							<div class="col-md-4 column">
								<div style="height: 30px"></div>
								<div>
									<label>邮箱</label><span id="email-msg" style="color:red;"></span><input type="text" id="email" name="email" class="form-control"
										value="${userInfo.email}" style="width: 200px" /> <br>
										<label>手机号</label><span id="telephone-msg" style="color:red;"></span><input
										type="text" class="form-control" id="telephone" name="telephone" value="${userInfo.telephone}"
										style="width: 200px" /><br><br><br><br><br>
								</div>
							</div>
							
							<center>
							<div>
								<input type="submit" class="btn btn-default"
									style="width: 180px; background: red; color: white; "
									value="保存修改" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a class="btn btn-default"
									href="change_userInfo"
									style="width: 180px; background: red; color: white;">取消修改</a> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
							</center>
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