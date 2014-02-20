<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@ page import="com.exedosoft.plat.bo.DOService"%>
<%@ page import="com.exedosoft.plat.bo.BOInstance"%>
<%@ page import="com.exedosoft.plat.util.I18n"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>

<html>

<%

	String paneModelContent = (String) request
			.getAttribute("paneModelContent");
	String paneModelTitle = (String) request
			.getAttribute("paneModelTitle");
	SessionContext context = (SessionContext) session
			.getAttribute("userInfo");
	if (null == session.getAttribute("userInfo")
			|| context.getUser() == null) {
		response.sendRedirect(request.getContextPath()
		+ "/web/default/logoff.jsp");
	}
	if (paneModelTitle == null) {
		paneModelTitle = "信息管理平台";
	}

	if (paneModelContent == null) {
		paneModelContent = "欢迎使用快速开发平台!";
	}
	
	String mainStyle= "";
	try{

		mainStyle = DOGlobals.getInstance().getSessoinContext().getUser().getValue("style");
	}catch(Exception e){
		
	}
	if(mainStyle=="" || mainStyle==null){
		mainStyle = "_lan";
	}
	System.out.println(mainStyle);
%>
<head>
<title><%= I18n.instance().get(paneModelTitle)%></title>


<script language="javascript">

globalURL = "/<%=DOGlobals.URL%>/";

</script>
<link rel="icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" /> 
<!-- Jquery插件的css -->

<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/toolbar/core.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/toolbar/toolbar.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/fileuploader/uploadify.css"    type="text/css" />  
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/treetable/jquery.treeTable.css" type="text/css" /> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/facebook/jquery.neosmart.fb.wall.css" type="text/css" /> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/facebook/status.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/ztree/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/combox/combox.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/pagination/pagination.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/layout/layout-default-latest.css" type="text/css"/>

 
 <!-- 平台主体及其它集成的css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/xtree2.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/estop/estop.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/nav/exedo_nav.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/redmond/jquery-ui-1.8.16.custom.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/main/main<%=mainStyle%>.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/workbench/workbench_style.css" type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/CodeMirror/codemirror.css"
	type="text/css" />	
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/CodeMirror/default.css"
	type="text/css" />	

 
<!-- 插件的js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/toolbar/toolbar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/combox/selects.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/combox/selects_static.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/combox/combox.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/pagination/jquery.pagination.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/layout/jquery.layout-latest.min.js" ></script>	

<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/fileuploader/jquery.uploadify.v2.1.0.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/fileuploader/swfobject.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/treetable/jquery.treeTable.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/facebook/jquery.elastic.js" ></script>		
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/ztree/jquery.ztree.core-3.1.min.js" ></script>
<!-- 平台主体及其他集成的js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/treev2/xtree2.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/treev2/xloadtree2.js" ></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/FCKeditor/fckeditor.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/FusionChartsFree/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/main/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/main/platAjax.js"  ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/my.js"  ></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/CodeMirror/codemirror-compressed.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/CodeMirror/complete.js"></script>

<% if ("en".equals(DOGlobals.getValue("lang.local"))){ %>	
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/main/lang_en.js"  ></script>
<% }else{ %>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/main/lang_zh.js"  ></script>
<% }%>
	
	


<style type="text/css">
<%
//加载自定义css
DOService aService = DOService.getService("DO_BO_Icon_List_css_valid");
if(aService!=null){
	  List list = aService.invokeSelect();
	  for(Iterator it = list.iterator(); it.hasNext();){
		   BOInstance bi = (BOInstance)it.next();
		   if(bi!=null && bi.getValue("formulaScript")!=null)
		   out.println(bi.getValue("formulaScript"));
	  }
}
%>
</style>

</head>


<BODY>
<div id='dmLayer'></div>
<input  type="hidden" id="mainStyle" value="<%=mainStyle %>"/> 
<DIV class="ui-layout-center">Center
	<P><A href="http://layout.jquery-dev.net/demos.html">Go to the Demos page</A></P>
	<P>* Pane-resizing is disabled because ui.draggable.js is not linked</P>
	<P>* Pane-animation is disabled because ui.effects.js is not linked</P>
</DIV>
<DIV class="ui-layout-north">North</DIV>
<DIV class="ui-layout-south">South</DIV>
<DIV class="ui-layout-east">East</DIV>
<DIV class="ui-layout-west">West</DIV>
</BODY>


<script type="text/javascript">
$(document).ready(function () {
	$('body').layout({ applyDefaultStyles: true });
});

<%
//加载自定义javascript
 aService = DOService.getService("DO_BO_Icon_List_js_valid");
if(aService!=null){
	  List list = aService.invokeSelect();
	  for(Iterator it = list.iterator(); it.hasNext();){
		   BOInstance bi = (BOInstance)it.next();
		   if(bi!=null && bi.getValue("formulaScript")!=null)
		   out.println(bi.getValue("formulaScript"));
	  }
}
%>

var cssEditor;
var jsEditor;
var htmlEditor;
var rhinoEditor;
</script>

</html>
