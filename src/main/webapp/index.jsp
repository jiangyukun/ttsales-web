<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<jsp:include page="/pages/common/resources.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/resources/template_admin/assets/css/pages/login-soft.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>

<!-- <script src="assets/scripts/custom/login-soft.js" type="text/javascript"></script> -->
<script type="text/javascript">
$(function(){ 
	$.backstretch([
			        $.sas.common.getRootPath()+"/resources/template_admin/assets/img/bg/1.jpg",
			        $.sas.common.getRootPath()+"/resources/template_admin/assets/img/bg/2.jpg",
			        $.sas.common.getRootPath()+"/resources/template_admin/assets/img/bg/3.jpg",
			        $.sas.common.getRootPath()+"/resources/template_admin/assets/img/bg/4.jpg"
			        ], {
			          fade: 1000, 
			          duration: 8000
			    });
 }); 
function login(){
 	if($('#username').val()==''){
		$.sas.util.showMsg("请输入用户名！"); 
		return;
	}
	if($('#password').val()==''){
		$.sas.util.showMsg("请输入密码！"); 
		return;
	}
   $.ajax({ 
		async:false,
	 	type : "POST",
		url : $.sas.common.getRootPath()+"/admin/login.do",
		data:{
			"username":$('#username').val(),
			"password":$.md5($('#password').val())
		},
		success : function(data, textStatus, jqXHR) {
			if(data.code=="-2"){
				$.sas.util.showMsg("没有该用户！"); 
					return;
			}else if(data.code=="-1"){
				$.sas.util.showMsg("密码错误！"); 
				return;
			}else{
				window.location.href=$.sas.common.getRootPath()+"/pages/sys/main.jsp";
			}
		},
	dataType : "json"  
});   
}
</script>
</head>
<body class="login">
	<div class="logo">
		<a href="#"> <img src="${pageContext.request.contextPath}/resources/sas/img/logo.png" alt="" style="width:60px"/></a>
	</div>
	<div class="content">     
 			<h3 class="form-title">用户登录</h3>
			<div id="msg_dlg" class="alert alert-danger display-hide">
				<button class="close" data-close="alert"></button>
				<span id="msg"></span>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">用户名</label>
				<div class="input-icon">
					<i class="fa fa-user"></i> <input
						class="form-control placeholder-no-fix" type="text" id="username"
					autocomplete="off" placeholder="用户名" name="username" />
				</div>
			</div> 
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">密码</label>
				<div class="input-icon">
					<i class="fa fa-lock"></i> <input id="password"
						class="form-control placeholder-no-fix" type="password"
						autocomplete="off" placeholder="密码" name="password" />
				</div>
			</div>
			<div class="form-actions">
				<button  onclick="login();" class="btn blue pull-right">
					登录 <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
			 
 	</div>
	<div class="copyright">2014 &copy; 微车.</div>
</body>

</html>