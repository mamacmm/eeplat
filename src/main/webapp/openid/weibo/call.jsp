<%@ page contentType="text/html;charset=utf-8" %>
<%@ page language="java" import="weibo4j.*" %>
<%@ page language="java" import="weibo4j.http.*" %>
<%@ page language="java" import="com.eeplat.social.openapi.callback.GlobalConfig" %>


<%
       Oauth oauth = new Oauth();
		response.sendRedirect(oauth.authorize("code"));

%>
