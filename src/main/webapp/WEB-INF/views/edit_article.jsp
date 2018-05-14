<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>文章编辑</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="static/js/jquery-1.12.4.min.js"></script>
<script src="static/ueditor/ueditor.config.js"></script>
<script src="static/ueditor/ueditor.all.js"></script>
<script src="static/ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="static/js/edit_article.js"></script>
</head>

<body>
	<div class="container" style="background-color: #F2F2F2">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">

					<a class="navbar-brand" href="index" style="color: black">首页</a>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="user_articles" style="color: black">我的博客</a></li>
						<li><a href="all_articles" style="color: black">博客</a></li>
						<li><a href="question" style="color: black">问答</a></li>
						<li><a href="activity" style="color: black">活动</a></li>
						<li><a href="resource_upload" style="color: black">上传资源</a></li>
						<li><a href="resource_download" style="color: black">下载资源</a></li>
					</ul>
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" />
						</div>
						<button type="search" class="btn btn-default">搜索</button>
					</form>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="write_article" style="color: black">写博客</a></li>
						<li><a href="about" style="color: black">关于我们</a></li>
						<%
							if (session.getAttribute("account") == null) {
						%>
						<li><a href="login" style="color: black">登录</a></li>
						<%
							} else {
						%>
						<li><a href="#" style="color: red"><%=session.getAttribute("account")%></a>
							<%
								}
							%>
						<li><a href="logout" style="color: black">注销</a></li>
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
								<li><a href="write_article" style="color: black;">发布文章</a></li>
								<li><a href="article_management" style="color: black;">文章管理</a></li>
								<li><a href="comment_management" style="color: black">评论管理</a></li>
								<li><a href="userInfo_management" style="color: black;">个人信息管理</a></li>
							</ul>
						</div>
						</nav>
						<form role="form" method="post" action="update_article">
							<div class="form-group">
								<div style="height: 130px">
									<label>发表类型：</label><input type="text" class="form-control"
										id="publishType" name="publishType" style="width: 120px"
										readonly="readonly" value="${EditArticleInfo.publishType}" /><br>
									<label>文章类型：</label><br> <select id="sel_articleType"
										style="height: 30px; width: 120px">
										<c:forEach var="i" begin="1" end="14">
											<c:choose>
												<c:when test="${List[i-1]==EditArticleInfo.articleType}">
													<option value="${i}" selected>${List[i-1]}</option>
												</c:when>
												<c:otherwise>
													<option value="${i}">${List[i-1]}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
									<input type="text" id="article_type" name="article_type"  style="display:none">
									<input type="text" id="article_Id" name="article_Id"  style="display:none" value="${EditArticleInfo.articleId}">
								</div>
								<br>
								<div>
									<label>文章标题</label> &nbsp;&nbsp;<span id="article_title_msg"
										style="color: red; font-size: 12px"></span><input type="text"
										class="form-control" id="article_title" name="article_title"
										style="width: 400px" value="${EditArticleInfo.articleName }" />
								</div>
								<br> <label>文章内容</label>&nbsp;&nbsp;<span
									id="article_content_msg" style="color: red; font-size: 12px"></span><br>
								<div id="editor" style="height: 360px">
									<script type="text/javascript" charset="utf-8">
										var ue = UE.getEditor('editor');
										ue.addListener("ready",function() {
											// editor准备好之后才可以使用 
											ue.setContent('${EditArticleInfo.articleContent}',false);
									  });
									</script>
									<input type="text" id="article_content" name="article_content"
										style="display: none">
								</div>
								<br> <br>
								<div style="margin: 0 auto; width: 50%; height: 100%">
									<input type="submit" class="btn btn-default"
										style="width: 180px; background: red; color: white; margin-right: 80px"
										value="保存修改" /> <a class="btn btn-default"
										href="${APP_PATH }/edit_article?articleId=${EditArticleInfo.articleId}"
										style="width: 180px; background: red; color: white">取消修改</a>
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