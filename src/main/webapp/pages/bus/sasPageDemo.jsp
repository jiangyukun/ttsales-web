<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>预约</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<base href="${pageContext.request.contextPath}/">
<link type="text/css" rel="stylesheet" href="resources/sas/page/css/component.css?v=v1.0.9_20150005" />
<script type="text/javascript" src="resources/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="resources/sas/page/js/modernizr.custom.js?v=v1.0.9_20150005"></script>
<script type="text/javascript" src="resources/sas/jquery.sas.min.js?v=v1.0.9_20150005"></script>
<script type="text/javascript" src="resources/sas/page/js/jquery.sas.page.js?v=v1.0.9_20150005"></script>

<style type="text/css">

</style>


<script type="text/javascript">

function next(page){
	/* alert(page); */
	$.sas.common.toPage(page);
};

function pre(){
	
}
/* 
$(function(){
	//$("[data-role='page']").sasPage();
	//$("[data-role='page']").eq( 0 ).addClass( 'pt-page-current' );
}); */


</script>

</head>

<body id="main">
	<div id="page1" data-role="page" data-options = "{inpage:'moveToLeftEasing ontop',outpage:'moveFromRight'}"  data-css="a:'yyy',b''"class="">
		<div data-role="header">
			<h2>header</h2><input type="button" value="下一页" onclick="next('#page2');">
		</div>
		<div data-role="content">
			content
		</div>
		<div data-role="footer"><h2>footer</h2></div>
	</div>
	<div id="page2" data-role="page" data-options = "{inPage:'scaleUp',outpage:'moveToBottom ontop'}" ><h1>页面切换效果2</h1><input type="button" value="上一页" onclick="next('#page3');"><input type="button" value="下一页"></div>
	<div id="page3" data-role="page" data-options = "{inPage:'moveFromRight ontop',outPage:'fade'}"><h1>页面切换效果3</h1><input type="button" value="上一页" onclick="next('#page4');"><input type="button" value="下一页"></div>
	<div id="page4" data-role="page" data-options = "{inPage:'rotatePullBottom delay180',outPage:'rotatePushTop'}"><h1>页面切换效果4</h1><input type="button" value="上一页" onclick="next('#page5');"><input type="button" value="下一页"></div>
	<div id="page5" data-role="page" data-options = "{inPage:'flipInLeft delay500',outPage:'rotateRightSideFirst'}"><h1>页面切换效果5</h1><input type="button" value="上一页" onclick="next('#page6');"><input type="button" value="下一页"></div>
	<div id="page6" data-role="page" data-options = "{inPage:'moveFromTop',outPage:'rotatePushBottom'}"><h1>页面切换效果6</h1><input type="button" value="上一页" onclick="next('#page1');"></div>
</body>
</html>

