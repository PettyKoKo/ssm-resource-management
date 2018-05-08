$(function() {
	$('#account').focus().blur(checkAccount);
	$('#password').blur(checkPassword);
	$('#repassword').blur(checkrePassword);
	$('#username').blur(checkName);
	$('#phone').blur(checkPhone);
	$('#email').blur(checkEmail);
	$('#position').blur(checkPosition);
});

function checkAccount() {
	var account = $('#account').val();
	var is_Exist = false;
	if (account == null || account == "") {
		// 提示错误
		$('#account-msg').html("账号不能为空");
		return false;
	}
	var reg = /^\w{6,26}$/;
	if (!reg.test(account)) {
		$('#account-msg').html("输入6-26个字母或数字或下划线");
		return false;
	}
	var data = "user_account=" + account;
	// 在这里需要发出一个异步请求，去查询用户名是否可用
	// 1、创建ajax对象
	var xhr = new XMLHttpRequest();
	// 2、监听ajax对象的状态

	xhr.onreadystatechange = function(event) {
		// 每当ajax对象的异步请求进行一步，这个函数就会被调用一次
		/**
		 * 整个ajax的请求与响应有4个阶段，每个阶段都对应着一个状态码，如下： 1、正在发送请求，即send方法开始调用
		 * 2、请求发送完毕，即send方法执行完毕（请求没问题） 3、正在解析响应内容 4、响应内容解析完毕（也就说响应成功）
		 */
		if (xhr.readyState == 4) {
			// 说明响应成功
			if (xhr.status == 200) {
				// 说明响应内容正确
				// 对响应内容的处理要在这里进行
				var result = xhr.responseText;
				if (result == "fail") {
					// 说明该用户名已经存在，不可用
					$('#account-msg').html("对不起，当前账号已存在");
				} else {
					$('#account-msg').empty();
					is_Exist = true;
				}
			}
		}
	}
	// 3、建立链接
	xhr.open("post", "checkUser_Account", false);
	/**
	 * 参数1：请求方法（post或get） 参数2：请求的接口 参数3：是否为异步请求
	 */

	// 4、设置请求头
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded'); // 固定写成这样
	// 5、发送数据
	xhr.send(data); // 对于post请求，数据要在send里面通过参数的形式传过去

	if (is_Exist == false)
		return false;

	$('#account-msg').empty();
	return true;

}

function checkName() {
	var name = $('#username').val();
	if (name == null || name == "") {
		// 提示错误
		$('#username-msg').html("姓名不能为空");
		return false;
	}
	$('#username-msg').empty();
	return true;
}

function checkPosition() {
	var position = $('#position').val();
	if (position == null || position == "") {
		// 提示错误
		$('#position-msg').html("职位不能为空");
		return false;
	}

	$('#position-msg').empty();
	return true;
}

function checkPassword() {
	var password = $('#password').val();
	if (password == null || password == "") {
		// 提示错误
		$('#password-msg').html("密码不能为空");
		return false;
	}
	var reg = /^\w{6,26}$/;
	if (!reg.test(password)) {
		$('#password-msg').html("输入6-26个字母或数字或下划线");
		return false;
	}
	$('#password-msg').empty();
	return true;
}

function checkrePassword() {
	var password = $('#repassword').val();
	if (password == null || password == "") {
		// 提示错误
		$('#repassword-msg').html("请再次输入密码");
		return false;
	}
	var reg = $('#password').val();
	if (password != reg) {
		$('#repassword-msg').html("输入密码不一致");
		return false;
	}
	$('#repassword-msg').empty();
	return true;
}

function checkPhone() {
	var phone = $('#phone').val();

	if (phone == null || phone == "") {
		// 提示错误
		$('#phone-msg').html("手机号不能为空");
		return false;
	}
	var reg = /^1[3|5|7|8]\d{9}$/;
	if (!reg.test(phone)) {
		$('#phone-msg').html("请输入正确的手机号码");
		return false;
	}

	$('#phone-msg').empty();
	return true;

}

function checkEmail() {
	var email = $('#email').val();
	if (email == null || email == "") {
		// 提示错误
		$('#email-msg').html("邮箱号不能为空");
		return false;
	}
	var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
	if (!reg.test(email)) {
		$('#email-msg').html("邮箱号不符合规则");
		return false;
	}
	$('#email-msg').empty();
	return true;

}

// 注册按钮响应事件
window.onload = function() {

	document.querySelector('[type=submit]').onclick = function(e) {
		if (!(checkAccount() && checkPassword() && checkrePassword()
				&& checkName() && checkPhone() && checkEmail() && checkPosition())) {
			// 阻止提交
			e.preventDefault()
		}
	}

}