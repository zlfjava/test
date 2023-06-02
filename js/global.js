var globalData = {
	server:"http://localhost:9980/",  //后端服务器地址
	client: "http://127.0.0.1:8020/crmweb/",  //前端项目地址
	//把登陆的用户名存入h5的缓存
	setUserInfo: function(uid,username,token,idcard,protectmtel) {
		//localStorage 跨页面的存储空间  
		window.localStorage.setItem("uid", uid);
		window.localStorage.setItem("username", username);
		window.localStorage.setItem("idcard", idcard);
		window.localStorage.setItem("protectmtel", protectmtel);
		window.localStorage.setItem("token", token);
	},
	//从H5缓存中取出当前登录的用户名
	getUid:function(){
		return window.localStorage.getItem("uid");
	},
	getUserName:function(){
		return window.localStorage.getItem("username");
	},
	getToken:function(){
		return window.localStorage.getItem("token");
	},
	getProtectmtel:function(){
		return window.localStorage.getItem("protectmtel");
	},
	getIdcard:function(){
		return window.localStorage.getItem("idcard");
	},
	//退出登录
	loginOut:function(){
		window.localStorage.removeItem("username");
		window.localStorage.removeItem("token");
	}
};
//写入环境
document.write('<link rel="stylesheet" href="'+globalData.client+'js/layui/css/layui.css" type="text/css"/>');
document.write('<link rel="stylesheet" href="'+globalData.client+'css/szc-html.css" type="text/css"/>');
document.write('<link rel="stylesheet" href="'+globalData.client+'css/dsz_check_quanxian.css" type="text/css"/>');
document.write('<script  type="text/javascript" src="'+globalData.client+'js/layui/layui.js"></script>');
document.write('<script type="text/javascript" src="'+globalData.client+'js/jquery.min.js" ></script>');
document.write('<script  type="text/javascript" src="'+globalData.client+'js/loginInterceptor.js"></script>');
document.write('<script  type="text/javascript" src="'+globalData.client+'js/dsz_check_quanxian.js"></script>');
document.write('<link rel="stylesheet" href="'+globalData.client+'css/style.css" type="text/css"/>');
document.write('<script  type="text/javascript" src="'+globalData.client+'js/jquery-1.10.2.js"></script>');
document.write('<script  type="text/javascript" src="'+globalData.client+'js/img_ver.js"></script>');