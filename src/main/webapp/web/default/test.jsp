<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setDateHeader("Expires", 0);
%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>

<%

Enumeration e = session.getAttributeNames();
for(;e.hasMoreElements();){
	out.println(e.nextElement());
}


%>
