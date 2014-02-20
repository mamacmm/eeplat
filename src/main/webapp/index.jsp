<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@ page import="com.exedosoft.plat.bo.DOApplication"%>

<%@ page import="java.util.List"%>

<%
if ("true".equals(DOGlobals.getValue("multi.tenancy"))){
  response.sendRedirect("index/index.jsp");
  return;
}
else{
	List<DOApplication> doas = DOApplication.getApplications();
	if(doas==null || doas.size() == 0){
		 response.sendRedirect("web/default/setup/");
		 return;
	}
	if( doas.size() == 1 && ( ((DOApplication)doas.get(0)).getName().equals("SystemManager") 
			
			|| ((DOApplication)doas.get(0)).getName().equals("EEPlat")
			)){
		 response.sendRedirect("web/default/setup/");
		 return;
	}
	if ("true".equals(DOGlobals.getValue("multi.project"))){
		
		if(doas.size() >= 2){
		  if(DOApplication.isUseWelcomePage()){
			  response.sendRedirect("welcome.jsp");
			  return;
		  }else{
			  response.sendRedirect("projects/index.jsp");
			  return;
		  }
		}
	}
    response.sendRedirect("web/default/");
}

%>