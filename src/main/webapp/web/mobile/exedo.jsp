<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@page import="com.exedosoft.plat.bo.DOService"%>
<%@ page import="com.exedosoft.plat.bo.BOInstance"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>

<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			String paneModelContent = (String) request
			.getAttribute("paneModelContent");

			SessionContext context = (SessionContext)session.getAttribute("userInfo");
			if(null==session.getAttribute("userInfo") ||  context.getUser()==null){
			 response.sendRedirect(request.getContextPath() + "/web/default/logoff.jsp");
			}
			
			System.out.println("paneModelContent:::::" + paneModelContent);
%>

<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, minimum-scale=1, maximum-scale=1" />
<title>EEPlat应用</title>

<link rel="stylesheet"  href="<%=request.getContextPath()%>/web/mobile/css/jquery.mobile.css" />
<link rel="stylesheet"  href="<%=request.getContextPath()%>/web/mobile/css/openid.css" />
<link rel="stylesheet"  href="<%=request.getContextPath()%>/web/mobile/css/jquery.mobile.datebox.min.css" />
<link rel="stylesheet"  href="<%=request.getContextPath()%>/web/mobile/css/jqpagination.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/web/mobile/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/mobile/js/jquery.mobile.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/mobile/js/jquery.mobile.datebox.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/mobile/js/jquery.mobile.datebox.i18n.zh-CN.utf8.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/mobile/js/jquery.jqpagination.min.js" ></script>

<!-- 
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/map/ui/jquery.ui.map.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/map/ui/jquery.ui.map.services.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/map/ui/jquery.ui.map.extensions.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/map/ui/modernizr.min.js" ></script>

<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&libraries=places"></script> 

 -->
 
<script language="javascript">
		  globalURL = "/<%=DOGlobals.URL%>/";

</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/web/mobile/js/platAjax.js"></script>
</head>
<body>
	<%=paneModelContent%>
</body>
</html>
