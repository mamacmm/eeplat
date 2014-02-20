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
	usr = usrs.get(0);
	String strGender = usr.getValue("gender");
	String strPersonType = usr.getValue("persontype");
	String strUsrName = usr.getName();
	String strTeacherType = usr.getValue("teachertype");
	if(strGender == null){
		strGender = "1";
	}
	if(strGender.equals("2")){
		strGender = "woman";
	}else{
		strGender = "man";
	}
	
	if(strPersonType.equals("2")){
		strPersonType = "teacher";
	}else{
		strPersonType = "family";
	}
	
%>
<div class='html5-header-content'>
	<div class='html5-header-left html5-middle txtcenter'>
		<img class='h80' src='web/html5/img/icons/<%=Util.getTx(strGender, strPersonType) %>' />
	</div>
	<div class='html5-header-center html5-middle'>
		<div>
			<span class='fontbold'><%=strUsrName %>&#32;<%=Util.getRole(strPersonType, strTeacherType) %></span> 
			
				<% if(strPersonType.equals("teacher")){
					%>
					<br />
					<span class='small'>
						管理班级：大一班
					</span>
					<%
				} else if(strPersonType.equals("family")){
					%>
						
					<%
				}else{
					%>
						
					<%
				}%>
		</div>
	</div>
	<div class='html5-header-right html5-middle'></div>
</div>