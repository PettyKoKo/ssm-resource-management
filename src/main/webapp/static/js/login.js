$(function(){
	$('#account').blur(checkAccount);
	$('#password').blur(checkPassword);
});

function checkAccount(){
	var account = $('#account').val();
	var is_Exist = false;
	var data = "user_account=" + account;
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(event) {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var result = xhr.responseText;
				if (result == "success") {
					$('#account-msg').html("对不起，当前账号不存在");
				} else {
					$('#account-msg').empty();
					is_Exist = true;
				}
			}
		}
	}
	xhr.open("post", "checkUser_Account", false);
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded'); // 固定写成这样
	xhr.send(data); 
	if (is_Exist == false)
		return false;
	$('#account-msg').empty();
	return true;
}

function checkPassword(){
	var account = $('#account').val();
	var password = $('#password').val();
	var is_Exist = false;
	var data = "account=" + account+"&password="+password;
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(event) {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var result = xhr.responseText;
				if (result == "PasswordError") {
					$('#password-msg').html("输入密码不正确");
				} else {
					$('#password-msg').empty();
					is_Exist = true;
				}
			}
		}
	}
	xhr.open("post", "checkPassword", false);
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded'); // 固定写成这样
	xhr.send(data); 
	if (is_Exist == false)
		return false;
	$('#password-msg').empty();
	return true;
}

// 注册按钮响应事件
 window.onload = function(){
	document.querySelector('[type=submit]').onclick = function(e){
		if (!(checkAccount()&&checkPassword())) {
			// 阻止提交
			e.preventDefault()
			}
		}

}
