function check_comment_content() {
	var comment_content = $('#comment_content').val();
	if (comment_content == null || comment_content == "") {
		$('#comment_content_msg').html("评论内容不能为空");
		return false;
	}
	$('#comment_content_msg').empty();
	return true;
}

function reply(str){
	alert(str);
	document.getElementById("comment_content").focus();
	document.getElementById("reviewer_account").value=str;
	
}

// 评论发布按钮响应事件
window.onload = function() {
	document.querySelector('[type=submit]').onclick = function(e) {
		if (!(check_comment_content())) {
			// 阻止提交
			e.preventDefault();
		}
	}
}