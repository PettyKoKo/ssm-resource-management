function check_resource_name(){
	var resource_name = $('#resource_name').val();
	if(resource_name==null || resource_name==""){
		$('#resource_name_msg').html("资源名称不能为空");
		return false;
	}
	
	$('#resource_name_msg').empty();
	return true;
	
}

function check_resource_type(){
	var resource_type = $('#resource_type').val();
	if(resource_type==null || resource_type==""){
		$('#resource_type_msg').html("资源名称不能为空");
		return false;
	}
	$('#resource_type_msg').empty();
	return true;
}

function check_resource_describe(){
	var resource_describe = $('#resource_describe').val();
	if(resource_describe==null || resource_describe==""){
		$('#resource_describe_msg').html("资源名称不能为空");
		return false;
	}
	$('#resource_describe_msg').empty();
	return true;
}

function check_upload(){
	var upload = $('#upload').val();
	if(upload==null || upload==""){
		$('#upload_msg').html("资源名称不能为空");
		return false;
	}
	$('#upload_msg').empty();
	return true;
}

//上传按钮响应事件
window.onload = function() {
	document.querySelector('[type=submit]').onclick = function(e) {
		if (!(check_resource_name() && check_resource_type() && check_resource_describe() && check_upload())) {
			// 阻止提交
			e.preventDefault();
		}
	}
}