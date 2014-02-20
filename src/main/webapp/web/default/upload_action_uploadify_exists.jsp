<%@page import="java.sql.Struct"%><%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@ page import="com.exedosoft.plat.util.Escape"%>
<%@ page import="com.exedosoft.plat.util.StringUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.File"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>

<%
	SessionContext context = (SessionContext) session
	.getAttribute("userInfo");
	if (null == session.getAttribute("userInfo")
	|| context.getUser() == null) {
	response.sendRedirect(request.getContextPath()
		+ "/web/default/logoff.jsp");
	}
    String filename = request.getParameter("filename");
	File aFile = new File( DOGlobals.UPLOAD_TEMP + filename);
	if(aFile.exists()){
    	out.println("1");
	}else{
		out.println("0");
	}
%>