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


<link href="<%=request.getContextPath()%>/web/jui/assets/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=request.getContextPath()%>/web/jui/assets/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=request.getContextPath()%>/web/jui/assets/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=request.getContextPath()%>/web/jui/assets/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/jui/assets/css/main.css" type="text/css" />
<%=DOResource.getAllCssLink()%>
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



<script src="<%=request.getContextPath()%>/web/jui/assets/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/web/jui/assets/js/jquery.cookie.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/validate/jquery.validate.js" ></script>

<script src="<%=request.getContextPath()%>/web/jui/assets/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/web/jui/assets/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/web/jui/assets/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/web/jui/assets/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- ECharts -->
<script src="<%=request.getContextPath()%>/web/bootstrap/assets/js/echarts/esl.js"></script>

<script src="<%=request.getContextPath()%>/web/jui/assets/js/dwz.min.js" type="text/javascript"></script>

		<% if ("en".equals(DOGlobals.getValue("lang.local"))){ %>	
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/main/lang_en.js"  ></script>
		<% }else{ %>
		<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/main/lang_zh.js"  ></script>
		<% }%>
		
		
			
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/combox/selects_static.js" ></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/combox/selects.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"  src="<%=request.getContextPath()%>/web/jui/assets/js/main.js"></script>
<script type="text/javascript"  src="<%=request.getContextPath()%>/web/jui/assets/js/platAjax.js"></script>



<script type="text/javascript">
$(function(){
	DWZ.init("<%=request.getContextPath()%>/web/jui/assets/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"web/jui/assets/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
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

	<body scroll="no">
		<div id="layout">
			<%=paneModelContent%>   
		</div>
		<div id="footer">Copyright © 2013 <a href="http://www.eeplat.com" target="_blank">北京云荷素科技有限公司</a></div>
	</body>
</html>
