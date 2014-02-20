<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>

<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%

  request.getSession().removeAttribute("userInfo");
  if(!request.getSession().isNew()){
	  request.getSession().invalidate();
  }

 
%>

<script>
<% if (DOGlobals.getValue("logoff.url")!=null && !"".equals(DOGlobals.getValue("logoff.url"))) { %>
	  window.top.location="<%=request.getContextPath()%>/<%=DOGlobals.getValue("logoff.url")%>/"  ;
 <% } else { %>
	 window.top.location="index.jsp"  ;
 <% }%>	  
</script>