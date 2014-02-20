<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setDateHeader("Expires", 0);


%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@ page import="com.exedosoft.plat.util.I18n"%>
<%@ page import="com.exedosoft.plat.bo.DOBO"%>
<%@ page import="com.exedosoft.plat.bo.DOApplication"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html lang="en">
 <head>
<link rel="icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" /> 
<!-- Jquery插件的css -->

<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/validate/style.css" type="text/css"/>
<link href="<%=request.getContextPath()%>/web/bootstrap/assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/web/bootstrap/assets/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/estop/estop.css" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/jquery-plugin/validate/jquery.validate.min.js" ></script>


<script type="text/javascript" 	src="<%=request.getContextPath()%>/web/default/js/main/main.js" ></script>
<% if ("en".equals(DOGlobals.getValue("lang.local"))){ %>	
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/main/lang_en.js"  ></script>
<% }else{ %>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/default/js/main/lang_zh.js"  ></script>
<% }%>
<script language="javascript">
  globalURL = "/<%=DOGlobals.URL%>/";
</script>  

    <meta charset="utf-8">

    <title>EEPlat</title>
    
	<meta name="viewport" content="width=device-width">

  </head>
  <body>
		<div class="container">
		    <div class="page-header">
			    <h1 id="logo" ><img src="web/default/images/logo_300X90.jpg" alt="EEPlat" width="300" height="90"></h1>
			    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Original, agile & proven</p>
		    </div>
				<div class="row">
				<div class="span8">
				
				
					    <h1>欢迎使用EEPlat。</h1>
					   
					   <div class="form-actions">
			            <a href="web/default/"  class="btn btn-primary btn-large">后端配置登录</a>&nbsp;&nbsp;后端配置登陆的用户名/密码，为创建第一个工程时自己设定的用户名/密码。
			            <br/>	   <br/>
			            <a href="projects/index.jsp"  class="btn btn-primary btn-large">业务系统登陆</a>&nbsp;&nbsp;业务系统登陆的用户名/密码，缺省为u/1111，可以登录后修改。
			            <br/>	   <br/>
			            <a href="web/mobile/index.jsp"  class="btn btn-primary btn-large">手机界面登陆</a>&nbsp;&nbsp;和业务系统的用户名/密码相同。
			            <br/>	   <br/>
			            <a   class="btn btn-info btn-large"  href="javascript:closeWelcomePage()"><i class="icon-remove"></i> 关闭本页面</a>&nbsp;&nbsp;关闭本页面后，缺省的首页为"业务系统登陆"页面。
	        		</div>
					   
					   <h3> EEPlat是最具扩展性、最强大的信息系统在线开发平台。EEPlat拥有领先的元模型体系，提供元数据驱动、在线配置的开发模式，可以实现细粒度业务的灵活定制。
					   </h3>
					   
					   <h3>
<br/>EEPlat提供了多租户的管理，实现了多租户数据及逻辑的隔离，解决了多租户应用在功能、界面等方面无法满足不同租户个性化要求等问题。EEPlat可以大幅度得提高生产率，可帮助实现低成本、高质量、易扩展的跨越云计算和传统计算的信息管理系统。
						</h3>



				</div><!-- .span -->
						
					

				<div class="offset1 span3">
					<div class="well">
						<h2>Links</h2>
						<h3>文档</h3>
						<ul>
							<li><a href="http://code.google.com/p/eeplat/wiki/FirstProjcet">第一个工程</a></li>
							<li><a href="http://code.google.com/p/eeplat/wiki/Introduce">平台简介</a></li>
							<li><a href="http://code.google.com/p/eeplat/wiki/ConfigTools">配置界面</a></li>
							<li><a href="http://www.eeplat.com">官方网站</a></li>
						</ul>
						<h3>视频</h3>
						<ul>
							<li><a href="http://v.youku.com/v_playlist/f5709117o1p0.html ">EEPlat培训专辑</a></li>
						</ul>
					</div>
				</div><!-- .span -->
			</div><!-- .row -->

      <hr>

      <footer>
      </footer>


    </div> <!-- .container -->

		<script>
			function closeWelcomePage(){
				
		     	   var paras =  "&callType=uf&contextServiceName=do_org_account_closewelcomepage";
		    	   ////为了防止多次提交,可以加验证码
		     	   $.post(globalURL + "servicecontroller",paras,
		     			function (data){
		     			    window.location= globalURL + "projects/index.jsp";
		     	  });
			}
		</script>

  </body>
</html>
