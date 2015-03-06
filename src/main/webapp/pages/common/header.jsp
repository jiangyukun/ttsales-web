<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- BEGIN HEADER --> 
<div class="header navbar navbar-fixed-top mega-menu">

	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="header-inner">
	
		<!-- BEGIN LOGO -->
		<a class="navbar-brand " href="${pageContext.request.contextPath}/index.jsp">
			<img src="${pageContext.request.contextPath}/resources/sas/img/logo.png" alt="logo" class="img-responsive my-login-img"/>
		</a> 
		<!-- END LOGO -->  
 
		<!-- BEGIN HORIZANTAL MENU -->
		<div class="hor-menu hidden-sm hidden-xs">
			<ul class="nav navbar-nav" id="menu"> 
							
			</ul>
		</div>
		<!-- END HORIZANTAL MENU -->
  
		<!-- BEGIN TOP NAVIGATION MENU -->
		<ul class="nav navbar-nav pull-right">
			 
			
			<!-- BEGIN USER LOGIN DROPDOWN -->
			<li class="dropdown user">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"  style="margin-top:5px;">
 					<span class="username">
 						欢迎您，${sessionScope.LOGIN_USER.name} 
 					</span>
					<i class="fa fa-angle-down"></i>
				</a>
				<ul class="dropdown-menu">
					<li>
						<a href="#" onclick="Main.showEditPwdDlg();">
							<i class="fa fa-key"></i> 修改密码
						</a>
					</li>
						<li>
						<a href="#" onclick="Main.logout();">
							<i class="fa fa-sign-out"></i> 安全退出
						</a>
					</li>
				</ul>
			</li>
			<!-- END USER LOGIN DROPDOWN -->
  		</ul>
		<!-- END TOP NAVIGATION MENU -->
 	</div>
	<!-- END TOP NAVIGATION BAR -->
</div>

<!--editPwd dlg-->
<div id="editPwdDlg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
	<div class="modal-dialog my-check-doctor-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">修改密码</h4>
			</div>
			<div class="modal-body">
				<div id="editPwdMsg" class="alert alert-danger" style="display: none;">        
					<button class="close" data-close="alert"></button>
					<div><!--提示内容--></div>  
				</div>
				<form class="form-horizontal" role="form" id="editPwd_form"> 
					<div class="form-body"> 
						<div class="form-group">
							<label  class="col-md-3 control-label">原密码</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock"></i></span>
									<input type="password" class="form-control" placeholder="输入原密码" name="mobile" id="old_password">
								</div>   
							</div>
						</div> 
						<div class="form-group">
							<label  class="col-md-3 control-label">新密码</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock"></i></span>
									<input type="password" class="form-control" placeholder="输入新密码" name="mobile" id="new_password">
								</div>   
							</div>
						</div>  
						<div class="form-group">
							<label  class="col-md-3 control-label">确认密码</label>
							<div class="col-md-7">   
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock"></i></span>
									<input type="password" class="form-control" placeholder="输入确认密码" name="password" id="confirm_password">
								</div>
							</div>
						</div>
					</div>
				</form> 
			</div>
			<div class="modal-footer">
				<button class="btn green my-modal-footer" onclick="Main.editPassword();">确定</button>
				<button class="btn default" data-dismiss="modal" aria-hidden="true">关闭</button>
			</div>
		</div>
	</div>
</div>
<!-- END HEADER -->
