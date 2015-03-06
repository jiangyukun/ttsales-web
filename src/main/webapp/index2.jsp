<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/clientSidePagination.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/sas/jquery.sas.js?v=v1.0.9_20150005"></script>

  
<script type="text/javascript"> 
   
</script>
</head>
<body class="easyui-layout">
	 <a  href="${pageContext.request.contextPath}/sys/sync/syncDeptsAndMembers.do" class="easyui-linkbutton"  >同步数据</a>
	
	 
</body>
</html>