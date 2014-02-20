<!DOCTYPE html">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext" %>
<%@ page import="com.exedosoft.plat.util.DOGlobals" %>
<%@ page import="com.exedosoft.plat.bo.DOService" %>
<%@ page import="com.exedosoft.plat.bo.BOInstance" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	String paneModelContent = (String) request.getAttribute("paneModelContent");

	SessionContext context = (SessionContext)session.getAttribute("userInfo");
	if(null==session.getAttribute("userInfo") ||  context.getUser()==null){
		response.sendRedirect(request.getContextPath() + "/web/default/logoff.jsp");
	}
	System.out.println("paneModelContent:::::" + paneModelContent);
%>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
		<meta name=”apple-mobile-web-app-capable” content=”yes” />  <!-- 离线应用的另一个技巧       -->
		<meta name=”apple-mobile-web-app-status-bar-style” content=black” />  <!-- 隐藏状态栏          -->
		<meta content="black" name="apple-mobile-web-app-status-bar-style" /> <!--指定的iphone中safari顶端的状态条的样式     -->    
		<meta content="telephone=no" name="format-detection" />       <!-- 告诉设备忽略将页面中的数字识别为电话号码       -->
		<meta name="Author" content="Mr.ZL" />
		<title>EEPlat应用</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/web/html5/js/validate/style.css" type="text/css"/>
		<link rel="stylesheet/less" href="<%=request.getContextPath()%>/web/html5/css/animate.css" />
		<link rel="stylesheet/less" href="<%=request.getContextPath()%>/web/html5/css/common.css" />
		<link rel="stylesheet/less" href="<%=request.getContextPath()%>/web/html5/css/html5less.css" />
		<link id='application_theme' rel="stylesheet/less" href="<%=request.getContextPath()%>/web/html5/css/html5theme_green.css" />
		<link href="<%=request.getContextPath()%>/web/html5/css/bootstrap.min.css" rel="stylesheet" />
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	    <![endif]-->
	    
	    <script src="<%=request.getContextPath()%>/web/html5/js/jquery-1.10.2.min.js"></script>
	    
	    <script src="<%=request.getContextPath()%>/web/html5/js/echarts-plain-original-map.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/web/html5/js/validate/jquery.metadata.js" ></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/html5/js/validate/jquery.validate.min.js" ></script>
		<script type="text/javascript">
			  var globalURL = "/<%=DOGlobals.URL%>/";
		</script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/html5/js/my.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/html5/js/platAjax.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/html5/js/lang.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/html5/js/html5main.js"></script>
	</head>
	<body class='html5-hidden'>
		<%=paneModelContent%>
		<div id='loadpage'></div>
	    <script src="<%=request.getContextPath()%>/web/html5/js/bootstrap.min.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/web/html5/js/less-1.3.1.min.js"></script>
	</body>
</html>
