<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="static/js/jquery-1.12.4.min.js"></script>
<script src="static/js/login.js"></script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column" style="height: 80px">
				<h3 class="text-center"></h3>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-8 column">
				<img alt="140x140" src="static/images/login-banner.png" />
			</div>
			<div class="col-md-4 column">
				<div style="height: 60px">
					<h3>帐号登录</h3>
				</div>
				<form class="form-horizontal" role="form" method="post"
					action="user_login">
					<div class="form-group">
						<div class="col-sm-10">
							<input type="text" class="form-control" id="account"
								name="account" style="width: 300px" placeholder="输入账号" /><span
								style="color: red" id="account-msg" class="error"></span>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-10">
							<input type="password" class="form-control" id="password"
								name="password" style="width: 300px" placeholder="输入密码" /><span
								style="color: red" id="password-msg" class="error"></span>
						</div>
					</div><br><br>
					<div class="form-group">
						<div class="col-sm-10">
							<input type="submit" class="btn btn-default" name="login"
								style="width: 300px; background-color: red; color: white"
								value="登录" />
						</div>

					</div>
					<a href="register">还没有账号，注册一下</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
