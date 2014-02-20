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

<%
	/////只要工程的数目大于2，则直接进入登陆页面
	List list = DOApplication.getApplications();
	if(list.size() > 1){
		response.sendRedirect("../../../web/default/");
		return;
	}

 %>

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
			    <h1 id="logo" ><img src="../images/logo_300X90.jpg" alt="EEPlat" width="300" height="90"></h1>
			    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Original, agile & proven</p>
		    </div>
				<div class="row">
				<div class="span8">
					<form action="" id="contact-form" class="form-horizontal">
					  <fieldset>
					    <legend>欢迎使用EEPlat。您可以先看看介绍文档或视频，或者只需简单地填写下面的表格，开始使用这个世界上最具扩展性、最强大的信息系统在线开发平台。
						
						<br/>在下面你可以创建第一个工程。
						</legend>
						
					    <div class="control-group">
					      <label class="control-label" for="name">工程名称</label>
					      <div class="controls">
					        <input type="text" class="input-xlarge" name="name" id="name">
					      </div>
					    </div>
					    <div class="control-group">
					      <label class="control-label" for="username">用户名</label>
					      <div class="controls">
					        <input type="text" class="input-xlarge" name="username" id="username">
					      </div>
					    </div>
					    <div class="control-group">
					      <label class="control-label" for="password">密码</label>
					      <div class="controls">
					        <input type="password" class="input-xlarge" name="password" id="password">
					      </div>
					    </div>
					    <div class="control-group">
					      <label class="control-label" for="password2">再次输入</label>
					      <div class="controls">
					        <input type="password" class="input-xlarge" name="password2" id="password2">
					      </div>
					    </div>
              <div class="form-actions">
		            <button type="submit" id="createprjbtn" class="btn btn-primary btn-large">创建工程</button>
        			</div>
					  </fieldset>
					</form>
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

		<script src="script.js"></script>

  </body>
</html>
