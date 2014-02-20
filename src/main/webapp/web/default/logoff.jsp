<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.login.OnlineManager"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%
	try{
		SessionContext context = (SessionContext) session
				.getAttribute("userInfo");
	//	if (context.getUser() != null) {
	//		OnlineManager.removeUser(context.getUser().getUid());
	//	}
	
		request.getSession().removeAttribute("userInfo");
		if (!request.getSession().isNew()) {
			request.getSession().invalidate();
	
		}
	}catch(Exception e){
		
	}
	//response.sendRedirect("/wh/web/default/");
%>
<script>
<% if (DOGlobals.getValue("logoff.url")!=null && !"".equals(DOGlobals.getValue("logoff.url"))) { %>
	  window.top.location="<%=request.getContextPath()%>/<%=DOGlobals.getValue("logoff.url")%>"  ;
 <% } else if ("true".equals(DOGlobals.getValue("multi.tenancy"))) { %>
 	  window.top.location="<%=request.getContextPath()%>/index/"  ;
 <% } else if ("true".equals(DOGlobals.getValue("multi.project"))) { %>
	  window.top.location="<%=request.getContextPath()%>/projects/"  ;
 <% } else {%>	  
	 window.top.location="<%=request.getContextPath()%>/web/default/"  ;
 <% } %>	  
</script>