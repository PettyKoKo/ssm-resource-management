<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>文章管理</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
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
						<div style="height: 50px">
							<label>类别：</label><select id="sel_articleType"
								style="height: 30px; width: 110px">
								<option value="0">全部</option>
								<option value="1">人工智能</option>
								<option value="2">移动开发</option>
								<option value="3">后台开发</option>
								<option value="4">架构</option>
								<option value="5">运维</option>
								<option value="6">游戏开发</option>
								<option value="7">云计算/大数据</option>
								<option value="8">数据库</option>
								<option value="9">前端</option>
								<option value="10">编程语言</option>
								<option value="11">研发管理</option>
								<option value="12">安全</option>
								<option value="13">程序人生</option>
								<option value="14">区块链</option>
							</select>&nbsp;&nbsp;&nbsp;&nbsp; <label>类型：</label><select
								id="sel_publishType" style="height: 30px; width: 80px">
								<option value="0">全部</option>
								<option value="1">原创</option>
								<option value="2">转载</option>
								<option value="3">翻译</option>
							</select>
						</div>
						<div>
							<table class="table">
								<thead>
									<tr>
										<th style="width: 350px">标题</th>
										<th style="width: 50px; text-align: center;">状态</th>
										<th style="width: 50px; text-align: center;">阅读</th>
										<th style="width: 50px; text-align: center;">评论</th>
										<th style="width: 70px; text-align: center;">评论权限</th>
										<th style="width: 170px; text-align: center;">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageInfo.list }" var="article">
										<tr style="text-align: center;">
											<td style="text-align: left"><a
												href="${APP_PATH }/article_details?articleId=${article.articleId}">${article.articleName}</a>&nbsp;&nbsp;(<fmt:formatDate
													value="${article.finishTime }" type="date"
													pattern="yyyy-MM-dd HH:mm:ss" />)</td>
											<td></td>
											<td>${article.tread }</td>
											<td>0</td>
											<td><a href="#">禁止评论</a></td>
											<td><a href="${APP_PATH }/edit_article?articleId=${article.articleId}">编辑</a> | <a
												href="${APP_PATH }/delete_article?article_Id=${article.articleId}" >删除</a>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

					</div>
				</div>
				<div class="row clearfix"></div>
			</div>
			<div class="col-md-4 column"></div>
			<div class="col-md-8 column">
				<ul class="pagination">
					<li><a href="${APP_PATH }/article_management?pn=1">首页</a></li>
					<c:if test="${pageInfo.hasPreviousPage}">
						<li><a href="${APP_PATH }/article_management?pn=${pageInfo.pageNum-1}"
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
						<li><a href="${APP_PATH }/article_management?pn=${pageInfo.pageNum+1}"
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