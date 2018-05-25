<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8" />
<title>写博客</title>
<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="static/js/jquery-1.12.4.min.js"></script>
<script src="static/js/write_article.js"></script>
<script src="static/ueditor/ueditor.config.js"></script>
<script src="static/ueditor/ueditor.all.js"></script>
<script src="static/ueditor/lang/zh-cn/zh-cn.js"></script>
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
				<div class="row clearfix">
					<div class="col-md-1 column"></div>
					<div class="col-md-10 column" style="background-color: white">
						<nav class="navbar navbar-default" role="navigation">
						<div class="navbar-header"></div>
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
								<li><a href="write_article" style="color: black;"  onmouseover="mouseover(this)"onmouseout="mouseout(this)">发布文章</a></li>
								<li><a href="article_management" style="color: black;" onmouseover="mouseover(this)" onmouseout="mouseout(this)">文章管理</a></li>
								<li><a href="comment_management" style="color: black" onmouseover="mouseover(this)" onmouseout="mouseout(this)">评论管理</a></li>
								<li><a href="userInfo_management" style="color: black;" onmouseover="mouseover(this)" onmouseout="mouseout(this)">个人信息管理</a></li>
							</ul>
						</div>
						</nav>
						<form role="form" method="post" action="user_publish_article">
							<div class="form-group">
								<div>
									<label>发表类型</label>&nbsp;&nbsp;<span id="selType_msg"
										style="color: red; font-size: 12px"></span><br> <select
										id="selType" style="height: 30px; width: 100px">
										<option value="0">请选择</option>
										<option value="1">原创</option>
										<option value="2">转载</option>
										<option value="4">翻译</option>
									</select> <input type="text" id="publishType" name="publishType"
										style="display: none">
								</div>
								<br>
								<div>
									<label>文章标题</label>&nbsp;&nbsp;<span id="article_title_msg"
										style="color: red; font-size: 12px"></span> <input type="text"
										class="form-control" id="article_title" name="article_title"
										style="width: 400px" />
								</div>
								<br>
								<div>
									<label>文章类型</label>&nbsp;&nbsp;<span id="article_type_msg"
										style="color: red; font-size: 12px"></span><br> <input
										type="radio" name="radio" value="1"><label
										style="width: 90px">人工智能</label> <input type="radio"
										name="radio" value="2"><label style="width: 90px">移动开发</label>
									<input type="radio" name="radio" value="3"><label
										style="width: 90px">后台开发</label> <input type="radio"
										name="radio" value="4"><label style="width: 90px">架构</label>
									<input type="radio" name="radio" value="5"><label
										style="width: 90px">运维</label> <input type="radio"
										name="radio" value="6"><label style="width: 90px">游戏开发</label>
									<input type="radio" name="radio" value="7"><label
										style="width: 90px">云计算/大数据</label> <br> <input
										type="radio" name="radio" value="8"><label
										style="width: 90px">数据库</label> <input type="radio"
										name="radio" value="9"><label style="width: 90px">前端
									</label> <input type="radio" name="radio" value="10"><label
										style="width: 90px">编程语言</label> <input type="radio"
										name="radio" value="11"><label style="width: 90px">研发管理</label>
									<input type="radio" name="radio" value="12"><label
										style="width: 90px">安全</label> <input type="radio"
										name="radio" value="13"><label style="width: 90px">程序人生</label>
									<input type="radio" name="radio" value="14"><label
										style="width: 90px">区块链</label> <input type="text"
										id="article_type" name="article_type" style="display: none">
								</div>
								<br> <label>文章内容</label>&nbsp;&nbsp;<span
									id="article_content_msg" style="color: red; font-size: 12px"></span><br>
								<div id="editor" style="height: 360px">
									<script type="text/javascript" charset="utf-8">
										UE.getEditor('editor');
									</script>
									<input type="text" id="article_content" name="article_content"
										style="display: none">
								</div>
								<br> <br>
								<div style="margin: 0 auto; width: 50%; height: 100%">
									<input type="submit" class="btn btn-default"
										style="width: 180px; background: red; color: white; margin-right: 80px"
										value="发布文章" />
									<button type="reset" class="btn btn-default"
										style="width: 180px; background: red; color: white">放弃发布</button>
								</div>
								<br>
							</div>
						</form>
					</div>
					<div class="col-md-1 column"></div>
				</div>

			</div>
		</div>
		<div class="row clearfix"></div>
	</div>
</body>
</html>