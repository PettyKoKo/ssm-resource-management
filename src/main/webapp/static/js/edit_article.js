function check_Article_title() {
	var article_title = $('#article_title').val();
	if (article_title == null || article_title == "") {
		$('#article_title_msg').html("文章标题不能为空");
		return false;
	}

	$('#article_title_msg').empty();
	return true;

}


function check_Article_content() {
	// alert(UE.getEditor('editor').getContent());
	if (UE.getEditor('editor').getContentTxt() == null
			|| UE.getEditor('editor').getContentTxt() == "") {
		$('#article_content_msg').html("文章内容不能为空");
		return false;
	}
	//alert("Hello Test");
	document.getElementById("article_type").value = $('#sel_articleType option:selected').text();
	//alert(document.getElementById("article_type").value);
	document.getElementById("article_content").value = UE.getEditor('editor').getContent();
	$('#article_content_msg').empty();
	return true;
}

// 文章发布按钮响应事件
window.onload = function() {
	document.querySelector('[type=submit]').onclick = function(e) {
		if (!(check_Article_title() && check_Article_content())) {
			// 阻止提交
			e.preventDefault();
		}
	}
}