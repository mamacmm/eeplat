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
<title>天子星PaaS平台</title>


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
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/metro/MetroJs.min.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/validate/style.css" type="text/css"/>
 
 <!-- 平台主体及其它集成的css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/xtree2.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/estop/estop.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/nav/exedo_nav.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/redmond/jquery-ui-1.8.16.custom.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/main/main.css" type="text/css" />
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
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/validate/jquery.metadata.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/validate/jquery.validate.js" ></script>


<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/fileuploader/jquery.uploadify.min.js" ></script>	
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
<% if ("en".equals(DOGlobals.getValue("lang.local"))){ %>	
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/main/lang_en.js"  ></script>
<% }else{ %>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/main/lang_zh.js"  ></script>
<% }%>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/main/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/main/platAjax.js"  ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/my.js"  ></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/CodeMirror/codemirror-compressed.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/CodeMirror/complete.js"></script>


	
<script language="javascript">

            
//窗口大小改变的时候，重新给div限制高度
$(window).resize(function(){
   	resscrEvt($(this).height(),$(this).width());
}); 

$(function(){

	////为第一个Tab 绑定事件
	var tabSelector = "#dvTab table[tabId='workbench_container']";
	var tabBtnSelector = tabSelector+" .btn";
	bindTabClickCss(tabSelector);
	bindTabCloseCss(tabBtnSelector);
	bindTabCloseWindow(tabBtnSelector);
	
	
	
	//初始化左右拖动
	  $(".resizeTd").mousedown(function(e){
		  var oldPageX = e.pageX;
		  var old_gLeW = $(".gLe").width();
		  var old_gRiW = $(".gRi").width();
		  $(document).bind('mousemove',function(e){
//////////////不能太小
			  if(e.pageX > 10){
				  $(".gLe").width(old_gLeW + e.pageX - oldPageX);
				  $(".gRi").width(old_gRiW - e.pageX + oldPageX);
				  $(".gFpage").width(old_gLeW + e.pageX - oldPageX-1);
				  resscrEvt();
			  }
			  window.status = e.pageX;	   
		  }).bind('mouseup',function(e){
			  $(document).unbind('mousemove');
			  $(document).unbind('mouseup');	
			  }
		  );

	 });

	  resscrEvt();
});

</script>

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

<body  lang=zh>
<div id='dmLayer'></div>
<input  type="hidden" id="mainStyle" value="<%=mainStyle %>"/> 
<%=paneModelContent%>
</body>
<script>

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
