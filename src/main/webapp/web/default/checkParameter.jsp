<%@ page contentType="text/html;charset=UTF-8"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>

<%@page import="com.exedosoft.plat.AOPFactory"%>
<%@page import="com.exedosoft.plat.ServiceController"%>
<%@page import="com.exedosoft.plat.bo.DOParameter"%>
<%@page import="com.exedosoft.plat.bo.DOParameterCheck"%>
<%@page import="com.exedosoft.plat.bo.BOInstance"%>
<% 
  String parauid = request.getParameter("parauid");
  DOParameter thePara = DOParameter.getParameterByUId(parauid);
  BOInstance formBI = ServiceController.getFormInstance(request, false);
  ServiceController.dealDataBusContext(request,formBI);
		
		List checks = thePara.getChecks();
		if (checks != null && checks.size() > 0) {
			System.out.println("Check Parameter's Name::::" + thePara.getName());
			for (Iterator it = checks.iterator(); it.hasNext();) {
				DOParameterCheck dopCheck = (DOParameterCheck) it
						.next();

				  System.out.println("dopCheck::" + dopCheck.getDop().getValue());
				if (dopCheck.getKind() != null
						&& dopCheck.getKind().trim().length() > 0) {
					if (AOPFactory.getDOParameterCheckAOP() != null) {
						if (!AOPFactory.getDOParameterCheckAOP()
								.isAccess(dopCheck)) {
							continue;
						}
					}
				}
				String aCheck =  dopCheck.check(formBI);
				if (aCheck != null  && !aCheck.equals("success") && !aCheck.equals("")) {
					out.println("false");
					return;
				}
		}
	}
	out.println("true");
%>