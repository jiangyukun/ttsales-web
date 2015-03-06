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
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/jqueryMobile/jquery.mobile-1.4.3.min.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reserve.css?v=v1.0.9_20150005"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/humane/humane/custom.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jqueryMobile/jquery.mobile-1.4.3.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/sas/jquery.sas.js?v=v1.0.9_20150005"></script>

<style type="text/css">


body{margin:0; padding:0;}
*{ margin:0; padding:0; list-style:none;}
img{ border:0;}
.nav{ width:100%; margin:0 auto; clear:both; overflow:hidden;background:#F1F1F1;}
.sasTabs-container{ width:100%; margin:0 auto; clear:both; overflow:hidden;background:#F1F1F1;}
.sasTabs-container ul li{ height:35px; line-height:35px; float:left; padding:10px 0px; margin:0px 0px 0px 0px;position:relative;text-align: center}
.sasTabs-container ul li a{ color:#666; font-family:'Microsoft Yahei'; text-decoration:none;}
.sasTabs-ui-active span{ display:block; position:absolute; width:100%; height:4px; background:#1FAEFF; top:49px; }
.sasTabs-ui-active { color:red; font-family:'Microsoft Yahei'; text-decoration:none;}
.sasTabs-ui-active { color:red; font-family:'Microsoft Yahei'; text-decoration:none;}
</style>


<script type="text/javascript">


$(function(){
	//alert($("#tabs").sas);
	$("#tabs").sasTabs({
		onSelect:function(e,title,index){
			//alert(title);
		},
		onUnSelect:function(e,title,index){
			//alert(title + index);
		},
		onSwipeleft:function(e){
			//alert("left");
		},
		onSwiperight:function(e){
			//alert("right");
		}
	});
});


</script>

</head>

<body id="main">
	<div data-role="page" id="home">
	<div id="tabs" class="sasTabs-container" >
		<span></span>
		<ul>
			<li>
			
				<a href='#'>待接待</a>
			
			</li>
			<li><a href='#'>已到店</a></li>
			<li><a href='#'>全部</a></li>
		</ul>
	</div>
	</div>
</body>
</html>

