/**
 * 这个会在登录后的每一个页面加载完毕之后触发  
 * 自动请求后端服务器进行Token验证的
 */

$(() => {
	var pathName = window.location.pathname;
	if (pathName.indexOf("/login.html") != -1) {//当访问login。html
		return; //放行
	}
	if (pathName.indexOf("/resetpwd/demo.html") != -1) {
		return; //放行
	}
	if (pathName.indexOf("/resetpwd/forgetPwd2.html") != -1) {
		return; //放行
	}
	if (pathName.indexOf("/resetpwd/forgetPwd3.html") != -1) {
		return; //放行
	}
	if (pathName.indexOf("/resetpwd/forgetPwd4.html") != -1) {
		return; //放行
	}
	//获取当前的Token
	let localToken = globalData.getToken();
	//判断Token 是否 null
 	if (localToken == null) {
 		//还没登录  
 		// 回到登录页面
		window.top.location.href = globalData.client + "login.html"; 
	} else { 
		//如果token不为空,则执行token校验
		$.ajax({
			url: globalData.server + "/testToken",
			data: {
				token: localToken
			},
			type: "get",
			dataType: "text",
			success(result) {
				if (!result) { //token无效					
					window.top.location.href = globalData.client + "login.html"; // 登录页面;
				} else {
					if (pathName.indexOf("/admin/") != -1) {
						return;
					}
				}

			}
		});
	}
});
