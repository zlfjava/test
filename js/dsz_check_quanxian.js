var tempQuanXian = [];	//存储权限值

//页面加载时触发
window.onload = function () {
	var getUrl = globalData.server + "permission/checkQuanXian";
	var data = {
		"uid": globalData.getUid(),
		"token": globalData.getToken()
	}
	$.post(getUrl, data, function(res){
		for (var i = 0; i < res.length; i++) {
			tempQuanXian.push(res[i].permissionvalue);
		}
	}, "json");
}