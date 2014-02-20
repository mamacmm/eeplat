<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@ page import="com.exedosoft.plat.bo.DOService"%>
<%@ page import="com.exedosoft.plat.bo.BOInstance"%>
<%@ page import="com.exedosoft.plat.bo.DOResource"%>
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
		paneModelTitle   = "Welcome to use EEPlat!";
	}

	if (paneModelContent == null) {
		paneModelContent = "Welcome to use EEPlat!";
	}

%>
	<head>
	<title><%= I18n.instance().get(paneModelTitle)%></title>
	<script language="javascript">
		globalURL = "/<%=DOGlobals.URL%>/";
	</script>

	<meta name="description" content="overview &amp; stats" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	
	<link rel="icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" /> 
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" /> 


		<!--basic styles-->
		<link href="<%=request.getContextPath()%>/web/bootstrap/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link href="<%=request.getContextPath()%>/web/bootstrap/assets/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/web/bootstrap/assets/css/font-awesome.min.css" />

		<link href="<%=request.getContextPath()%>/web/bootstrap/assets/css/jquery-ui-1.10.3.full.min.css" rel="stylesheet" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="<%=request.getContextPath()%>/web/bootstrap/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!--page specific plugin styles-->

		<!--fonts-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/web/bootstrap/assets/css/ace-fonts.css" />

		<!--ace styles-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/web/bootstrap/assets/css/ace.min.css"/>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/web/bootstrap/assets/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/web/bootstrap/assets/css/ace-skins.min.css" />
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/xtree2.css" type="text/css"/>
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/web/bootstrap/assets/css/chosen.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/web/bootstrap/assets/css/datepicker.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/web/bootstrap/assets/css/bootstrap-timepicker.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/web/bootstrap/assets/css/daterangepicker.css" />
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/pagination/pagination.css" type="text/css"/>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/validate/style.css" type="text/css"/>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/web/bootstrap/assets/css/main.css" />
		<%=DOResource.getAllCssLink()%>
		
		
		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->
		
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


		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/ace-extra.min.js"></script>
		<!--inline styles if any-->
		
		
			<!--basic scripts  javascript需要放到前面，因为面板内部要执行的javascript-->

		<!--[if !IE]>-->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=request.getContextPath()%>/web/bootstrap/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>
		<!--<![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='<%=request.getContextPath()%>/web/bootstrap/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='<%=request.getContextPath()%>/web/bootstrap/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
	
	<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/bootstrap.min.js"></script>
	

		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/jquery-ui-1.10.3.full.min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/jquery.slimscroll.min.js"></script>
		<!-- Plot -->		 
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/flot/jquery.flot.min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/flot/jquery.flot.pie.min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/flot/jquery.flot.resize.min.js"></script>

		<!-- ECharts -->
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/echarts/esl.js"></script>

		
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/fuelux/fuelux.spinner.min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/date-time/moment.min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/date-time/bootstrap-timepicker.min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/date-time/daterangepicker.min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/bootstrap-colorpicker.min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/jquery.knob.min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/jquery.autosize-min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/jquery.maskedinput.min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/jquery.html5_upload.js"></script>
	
		<!--ace scripts-->

		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/ace-elements.min.js"></script>
		<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/ace.min.js"></script>
		
		
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/toolbar/toolbar.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/pagination/jquery.pagination.js" ></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/fileuploader/jquery.uploadify.min.js" ></script>	
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/fileuploader/swfobject.js" ></script>	
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/treetable/jquery.treeTable.min.js" ></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/facebook/jquery.elastic.js" ></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/validate/jquery.validate.js" ></script>		
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/treev2/xtree2.js" ></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/treev2/xloadtree2.js" ></script> 

	    <script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/combox/selects.js" ></script>	
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/combox/selects_static.js" ></script>	


		<script type="text/javascript" src="<%=request.getContextPath() %>/plugin/FCKeditor/fckeditor.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/plugin/kindeditor/kindeditor-min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/plugin/kindeditor/lang/zh_CN.js"></script>
		
		<% if ("en".equals(DOGlobals.getValue("lang.local"))){ %>	
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/main/lang_en.js"  ></script>
		<% }else{ %>
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/main/lang_zh.js"  ></script>
		<% }%>
		
		<script type="text/javascript"  src="<%=request.getContextPath()%>/web/bootstrap/assets/js/main.js"></script>
		<script type="text/javascript"  src="<%=request.getContextPath()%>/web/bootstrap/assets/js/platAjax.js"></script>
		<%=DOResource.getAllJavascriptLink()%>
		

		<script>
		
	    require.config({
	        paths:{ 
	            echarts:'web/bootstrap/assets/js/echarts/echarts'
	        }
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

	</head>

	<body>
		
		
			
	  <%=paneModelContent%>   
	
		<div id='dmLayer'   style="position: fixed;background-color:white;border: 1px solid #ccc;display:none;z-index:100001;overflow:auto;"></div>

		
	</body>
</html>
