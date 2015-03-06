<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>微车</title>
<jsp:include page="/pages/common/resources.jsp"></jsp:include>
<script type="text/javascript">
//主界面js
var Main = function(){
	var createMenu = function(){ 
		 $.ajax({ 
	 			async:false,
	 	 		type : "POST",
	 			url : $.sas.common.getRootPath()+"/admin/getMenu.do",
				success : function(data, textStatus, jqXHR) {
						var content="<li  style='margin-bottom: 20px;'><div class='sidebar-toggler hidden-xs'></div></li>";
						var menus = eval("("+data+")"); 
						for ( var i = 0; i < menus.length; i++) {
							var href = $.sas.common.getRootPath()+menus[i].menuUrl;
							var li="";
							if(i==0){
								li = "<li class='start'><a class='ajaxify' href='"+href+"'><i class='fa fa-home'></i><span id='menus"+menus[i].menuId+"' class='title'>"+menus[i].menuName+"<span class='selected'></span></span></a></li>";
							}else{
								li = "<li><a class='ajaxify' href='"+href+"'><i class='fa fa-home'></i><span id='menus"+menus[i].menuId+"' class='title'>"+menus[i].menuName+"<span class='selected'></span></span></a></li>";
							}
							content=content+li; 
						}
						//$('#menu').html(content); 
					
						$('#h_menu').html(content); 
						selectMenu(0); 
					},   
					dataType : "text"  
			});
		 
		 

	}; 
	
	var selectMenu = function(index){
	   var menu = $('.ajaxify'); 
	   if(menu.length>0){    
		   menu[index].click();  
	   }
	};  
	
	var editPassword = function(){
		$('#editPwdMsg').attr('style','display:none');
		var old_password = $('#old_password').val();
		var new_password = $('#new_password').val();
		var confirm_password = $('#confirm_password').val(); 
		if(old_password==''){ 
			$('#editPwdMsg').attr('style','');
			$('#editPwdMsg div').html('请输入原密码');
			return ;
		}
		if(new_password==''){ 
			$('#editPwdMsg').attr('style','');
			$('#editPwdMsg div').html('请输入新密码');
			return ;
		}
		if(new_password.length<6){
			$('#editPwdMsg').attr('style','');
			$('#editPwdMsg div').html('请至少输入6位数密码');
			return ;
		}
		if(confirm_password==''){
			$('#editPwdMsg').attr('style','');
			$('#editPwdMsg div').html('请输入确认密码');
			return ;
		}
		if(new_password!=confirm_password){
			$('#editPwdMsg').attr('style','');
			$('#editPwdMsg div').html('新密码与确认密码不一致');
			return ;
		}
		$.ajax({
	 		type : "POST",
			url : $.sas.common.getRootPath()+"/admin/editPassword.do",
			data : {
				"oldPassword" : $.md5(old_password),
				"newPassword" : $.md5(new_password)
			},
			success : function(data, textStatus, jqXHR) {
				var result = eval("("+data+")"); 
				$('#editPwdMsg').attr('style','');
				if(result.msg=='密码修改成功'){
					$('#editPwdDlg').modal('hide'); 
					$.sas.util.showMsg(result.msg); 
				}else{
					$('#editPwdMsg div').html(result.msg);
				}
			},  
			dataType : "text"
		});
	};
	
	var logout = function(){
		$.ajax({
			async:false,
	 		type : "POST",
			url : $.sas.common.getRootPath()+"/admin/logout.do",
			success : function(data, textStatus, jqXHR) {
				var result = eval("("+data+")"); 
				if (result.message=='操作成功') {
					//设置最后一个用户安全退出
 					window.location.href = $.sas.common.getRootPath();
				}else{
					$.sas.util.showMsg(result.message);
				}
			},  
			dataType : "text" 
		});
	};
	
	return {
		init : function(){ 
			createMenu(); 
  		},
 		showEditPwdDlg : function(){
 			$('#editPwd_form')[0].reset();  
 			$('#editPwdMsg').attr('style','display:none');
 			$('#editPwdDlg').modal('show');
 		},
 		editPassword : function(){
 			editPassword();
 		},
 		logout : function(){
 			logout();
 		}
	};
}();    
  
$(function(){ 
	Main.init(); 
});
</script>
</head>
<!-- END HEAD --> 

<!-- BEGIN BODY --> 

<body class="page-header-fixed   page-footer-fixed">

	<jsp:include page="/pages/common/header.jsp"></jsp:include>
	 
	<div class="clearfix"></div>

	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		 <div class="page-sidebar navbar-collapse collapse">
 			<ul id="h_menu" class="page-sidebar-menu " data-auto-scroll="true" data-slide-speed="200">
			</ul>
 		</div>
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<div class="page-content-body">
				
					<!-- HERE WILL BE LOADED AN AJAX CONTENT -->
				</div>
			</div>
			<!-- BEGIN CONTENT -->
		</div>
		<!-- END CONTAINER -->
	</div>
	
	<jsp:include page="/pages/common/footer.jsp"></jsp:include>
</body>

<!-- END BODY -->
</html>