<%@ page pageEncoding="UTF-8" contentType="text/plain; charset=UTF-8"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
%>
<%@ page import="com.exedosoft.plat.util.*"%>
<%@ page import="com.exedosoft.plat.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.exedosoft.plat.bo.*"%>
<%@ page import="org.json.*" %>
<%
	out.clearBuffer();
	String strStyle = request.getParameter("webStyle");
	WebStyle wStyle = WebStyle.getWebStyleByName(strStyle);
	JSONObject json1 = new JSONObject();
	if(wStyle != null){
		DOGlobals.getInstance().getSessoinContext().setUserWebStyle(wStyle.getStyleName());
		DOGlobals.getInstance().getSessoinContext().setWebStylePath(wStyle.getStyleName());
		json1.put("result", "success");
	}else{
		json1.put("result", "fail");
	}
	out.write(json1.toString());
%>