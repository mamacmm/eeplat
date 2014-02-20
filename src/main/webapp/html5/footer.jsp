<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.*" %>
<%@ page import="com.exedosoft.plat.util.*" %>
<%@ page import="com.exedosoft.plat.bo.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.exedosoft.plat.ui.html5.util.*" %>

<%
	SessionContext context = (SessionContext) session.getAttribute("userInfo");
	BOInstance usr = context.getUser();
	if(null == session.getAttribute("userInfo") || usr == null){
		response.sendRedirect(request.getContextPath() + "/web/html5/logoff.jsp");
	}
	List<BOInstance> usrs = DOService.getService("kids_person_browse").invokeSelect(usr.getUid());
%>
<div class='html5-footer-content'>
	<div class='html5-footer-left html5-middle txtcenter'>
		<img class='h80' src='web/html5/img/icons/icon.png' />
	</div>
	<div class='html5-footer-center html5-middle'>
		<div class='txtcenter font18px fontbold'>知了树幼儿园</div></div>
	<div class='html5-footer-right html5-middle txtcenter'>
		<img class='h80 cursorpt' src='web/html5/img/theme/green/green_main_setup.png' />
	</div>
</div>