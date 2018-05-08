$(function() {
	$('#article_title').focus().blur(check_Article_title);
});

function check_SelType() {
	var sel_type = document.getElementById("selType").value;
	if (sel_type == "0") {
		$('#selType_msg').html("请选择你的发表类型");
		return false;
	}

	var str = "";
	if (sel_type == "1") {
		str = "原创";
	} else if (sel_type == "2") {
		str = "转载";
	} else {
		str = "翻译"
	}
	document.getElementById("publishType").value = str;
	alert(document.getElementById("publishType").value);
	$('#selType_msg').empty();
	return true;

}

function check_Article_title() {
	var article_title = $('#article_title').val();
	if (article_title == null || article_title == "") {
		$('#article_title_msg').html("文章标题不能为空");
		return false;
	}

	$('#article_title_msg').empty();
	return true;

}

function check_Article_type() {
	var radios = document.getElementsByName("radio");
	var i = 0;
	var types = new Array("","人工智能","移动开发","后台开发","架构","运维","游戏开发","云计算/大数据","数据库","前端","编程语言","研发管理","安全","程序人生","区块链");
	
	for (i = 0; i < radios.length; i++) {
		if (radios[i].checked == true) {
			alert(types[parseInt(radios[i].value)]);
			document.getElementById("article_type").value=types[parseInt(radios[i].value)];
			
			$('#article_type_msg').empty();
			return true;
		}
	}

	if (i == radios.length) {
		$('#article_type_msg').html("请选择你的文章类型");
		return false;
	}

	$('#article_type_msg').empty();
	return true;
}

function check_Article_content() {
	// alert(UE.getEditor('editor').getContent());
	if (UE.getEditor('editor').getContentTxt() == null
			|| UE.getEditor('editor').getContentTxt() == "") {
		$('#article_content_msg').html("文章内容不能为空");
		return false;
	}
	document.getElementById("article_content").value = UE.getEditor('editor').getContent();
	$('#article_content_msg').empty();
	return true;
}

// 文章发布按钮响应事件
window.onload = function() {
	document.querySelector('[type=submit]').onclick = function(e) {
		if (!(check_SelType() && check_Article_title() && check_Article_type() && check_Article_content())) {
			// 阻止提交
			e.preventDefault();
		}
	}
}