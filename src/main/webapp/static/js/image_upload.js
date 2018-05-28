//上传按钮响应事件
window.onload = function() {
	document.querySelector('[type=submit]').onclick = function(e) {
		var oInput = document.getElementById('upload');
		if (oInput.value == '' || oInput.value == null) {
			$('#image-msg').html("请选择图片");
			e.preventDefault();
		}
	}
}