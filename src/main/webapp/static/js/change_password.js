function check_oldpassword(){
	var oldpassword=$('#oldpassword').val();
	var is_Exist = false;
	if(oldpassword == null || oldpassword == ""){
		$('#old-msg').html("原密码不能为空");
		return false;
	}
	
	var data = "oldpassword=" + oldpassword;
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
					// 密码不正确
					$('#old-msg').html("原密码不对");
				} else {
					$('#old-msg').empty();
					is_Exist = true;
				}
			}
		}
	}
	// 3、建立链接
	xhr.open("post", "checkUser_password", false);
	/**
	 * 参数1：请求方法（post或get） 参数2：请求的接口 参数3：是否为异步请求
	 */

	// 4、设置请求头
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded'); // 固定写成这样
	// 5、发送数据
	xhr.send(data); // 对于post请求，数据要在send里面通过参数的形式传过去

	if (is_Exist == false)
		return false;

	$('#old-msg').empty();
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

//保存按钮响应事件
window.onload = function() {

	document.querySelector('[type=submit]').onclick = function(e) {
		if (!(check_oldpassword() && checkPassword() && checkrePassword())) {
			// 阻止提交
			e.preventDefault()
		}
	}

}