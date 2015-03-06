<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${pageContext.request.contextPath}/resources/template_admin/assets/fonts/font.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/data-tables/DT_bootstrap.css"/>

<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/select2/select2-metronic.css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="${pageContext.request.contextPath}/resources/template_admin/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/template_admin/assets/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/template_admin/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/template_admin/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/template_admin/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${pageContext.request.contextPath}/resources/template_admin/assets/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES --> 
<link href="${pageContext.request.contextPath}/resources/sas/css/main.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" href="favicon.ico"/>
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/respond.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/data-tables/jquery.dataTables.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/data-tables/DT_bootstrap.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/easyui/jquery.easyui.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/gritter/js/jquery.gritter.js"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/template_admin/assets/plugins/select2/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/scripts/core/app.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS --> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/jquery.base64.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/jquery.md5.js"></script>
<script src="${pageContext.request.contextPath}/resources/sas/jquery.sas.js" type="text/javascript"></script>
<!-- END JAVASCRIPTS -->
<script>
 $(function(){
	//初始化
   App.init(); 
  });
</script>
