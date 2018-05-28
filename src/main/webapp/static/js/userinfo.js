function check_username(){
	var name = $('#username').val();
	if (name == null || name == "") {
		// 提示错误
		$('#username-msg').html("姓名不能为空");
		return false;
	}
	$('#username-msg').empty();
	return true;
}

function check_position(){
	var position = $('#position').val();
	if (position == null || position == "") {
		// 提示错误
		$('#position-msg').html("职位不能为空");
		return false;
	}

	$('#position-msg').empty();
	return true;
}

function check_email(){
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

function check_telephone(){
	var phone = $('#telephone').val();

	if (phone == null || phone == "") {
		// 提示错误
		$('#telephone-msg').html("手机号不能为空");
		return false;
	}
	var reg = /^1[3|5|7|8]\d{9}$/;
	if (!reg.test(phone)) {
		$('#telephone-msg').html("请输入正确的手机号码");
		return false;
	}

	$('#telephone-msg').empty();
	return true;
}


//保存按钮响应事件
window.onload = function() {

	document.querySelector('[type=submit]').onclick = function(e) {
		if (!(check_username() && check_position() && check_email() && check_telephone())) {
			// 阻止提交
			e.preventDefault()
		}
	}

}
