<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setDateHeader("Expires", 0);
%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@ page import="com.exedosoft.plat.util.I18n"%>
<%@ page import="com.exedosoft.plat.bo.DOBO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html lang="en">
 <head>
<link rel="icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" /> 
<!-- Jquery插件的css -->

<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/validate/style.css" type="text/css"/>
<link href="<%=request.getContextPath()%>/web/bootstrap/assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/web/bootstrap/assets/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/exedo/webv3/css/estop/estop.css" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/jquery-plugin/validate/jquery.validate.min.js" ></script>


<script type="text/javascript" 	src="<%=request.getContextPath()%>/exedo/webv3/js/main/main.js" ></script>
<% if ("en".equals(DOGlobals.getValue("lang.local"))){ %>	
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/main/lang_en.js"  ></script>
<% }else{ %>
<script type="text/javascript" src="<%=request.getContextPath()%>/exedo/webv3/js/main/lang_zh.js"  ></script>
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
					    <legend> Welcome to EEPlat.Please take a look at our introduction documents or videos presentations,or just fill out the form below, and start using the world's most scalable and powerful platform of enterprise applications.
						
						<br/>Create the first project below.
						</legend>
						
					    <div class="control-group">
					      <label class="control-label" for="name">Project Name</label>
					      <div class="controls">
					        <input type="text" class="input-xlarge" name="name" id="name">
					      </div>
					    </div>
					    <div class="control-group">
					      <label class="control-label" for="username">User Name</label>
					      <div class="controls">
					        <input type="text" class="input-xlarge" name="username" id="username">
					      </div>
					    </div>
					    <div class="control-group">
					      <label class="control-label" for="password">Password</label>
					      <div class="controls">
					        <input type="password" class="input-xlarge" name="password" id="password">
					      </div>
					    </div>
					    <div class="control-group">
					      <label class="control-label" for="password2"></label>
					      <div class="controls">
					        <input type="password" class="input-xlarge" name="password2" id="password2">
					      </div>
					    </div>
              <div class="form-actions">
		            <button type="submit" id="createprjbtn" class="btn btn-primary btn-large">Create Project</button>
        			</div>
					  </fieldset>
					</form>
				</div><!-- .span -->
				<div class="offset1 span3">
					<div class="well">
						<h2>Links</h2>
						<h3>Document</h3>
						<ul>
							<li><a href="http://code.google.com/p/eeplat/wiki/FirstProjcet">The First Project</a></li>
							<li><a href="http://code.google.com/p/eeplat/wiki/Introduce">Brief Introduction</a></li>
							<li><a href="http://code.google.com/p/eeplat/wiki/ConfigTools">Configuration Panel</a></li>
							<li><a href="http://www.eeplat.com">Official Website</a></li>
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
