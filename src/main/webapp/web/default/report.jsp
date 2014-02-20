<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="com.exedosoft.plat.report.jasper.HTMLExport"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.exedosoft.plat.ui.DOGridModel"%>
<%@ page import="com.exedosoft.plat.ui.jquery.grid.report.GridReport"%>
<% 

   String gridModelUid =  request.getParameter("gridModelUid");
   DOGridModel gm = DOGridModel.getGridModelByID(gridModelUid); 
   GridReport.report(gm,request, out);
  %>