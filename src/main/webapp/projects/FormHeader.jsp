<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.bo.DOApplication"%>
<%@ page import="com.exedosoft.plat.bo.BOInstance"%>
<%@ page import="com.exedosoft.plat.login.LoginMain"%>
<%@ page import="com.exedosoft.plat.DAOUtil"%>
<%@ page import="com.exedosoft.plat.util.I18n"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.exedosoft.plat.bo.org.OrgParterValue"%>
<%@ page import="com.exedosoft.plat.bo.DOResource"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%	
	String default_app_uid = "";  

	SessionContext exedoContext = (SessionContext) session
			.getAttribute("userInfo");
	if (exedoContext != null && exedoContext.getUser() != null) {
		default_app_uid = exedoContext.getUser().getValue("default_app_uid");
	}
	System.out.println("exedoContext.getUser():::" + exedoContext.getUser());

	
	

	String userName = "";
	String company = "";

	if (exedoContext != null && exedoContext.getUser() != null) {
		
		userName = exedoContext.getUser().getName();
		List parters = (List)exedoContext.getUser().getObjectValue(LoginMain.ALLAUTH);
		if(parters!=null){
			for(Iterator it = parters.iterator() ; it.hasNext();){
				OrgParterValue p = (OrgParterValue)it.next();
				if(p.getParter()!=null && p.getParter().isRole()){
					company = company + " " + I18n.instance().get(p.getName());  
				}
			}
			
		}
	}
	
	
	if("".equals(company) ||  "a".equals(userName)){
		company = I18n.instance().get("Developer");
	}
	
	List apps =  DOApplication.getApplications();
	
  	String url = 	DOResource.getSpecialPath(DOResource.TYPE_LOGO_SMALL);
  	if(url==null){
	 	 url = request.getContextPath() + "/web/default/images/logo_small_l.png";
 	 }

%>
<style> 
  .welcome {
	   color:#3C3C3C;
	   left:188px;
	   position:absolute;
	   top:17px;
  }
  .inner {
	   color:#3C3C3C;
  }
  .control{
  	color:#929393;
  	text-decoration:none;
  	position:absolute;
  	right:10px;
  	top:15px;
  	cursor:pointer;
  	line-height:167%;
  }
  .selectt{
  	width:134px;
  	display:inline;
  	color:black;
    font-weight:bold;
  }
</style>
<script language="javascript">

	var theUrl = globalURL;
	if(globalURL == '/web/default/'){
		  theUrl = "/";
	}
  function logOff(){
	  if(confirm('<%=I18n.instance().get("Confirm Exit")%>')){
	  	window.location.href=theUrl + "web/default/logoff.jsp"
	  }
  }

  function helpme(){
   		window.open('http://code.google.com/p/eeplat');
  }

  function setup(){
		window.open(theUrl + "console.pml?isApp=true");
  }

  function changeApp(obj){
	  
	  if(obj!=null && obj.value!=null && obj.value!='f' && obj.value!='a'){
		  var datas = obj.value.split(";");
			var url = globalURL + "/servicecontroller?dataBus=setUserContext&contextKey=default_app_uid&contextValue=" +datas[0];
			$.get(url,function(data){window.location = datas[1];});
	  }else if(obj!=null && obj.value=='a'){
		  loadPml({'pml':'appshare','target':'_opener_tab','title':'<%=I18n.instance().get("从AppShare安装")%>'});
      }
  }
</script>
<div>
	<div algin="left" id='logoPic' style='z-index:1; height: 0; left: 0px; position: absolute; top: 1px; width: 0;'><img name='logoImg' height="40" src='<%=url%>' border='0'></div>
	
	 <div class="control">
	    <span class="inner">
			<%=I18n.instance().get("Welcome")%>，<%=userName%> ！
		</span>[ <%=company%> ]
		&nbsp;&nbsp;
		<select class="selectt" onchange="changeApp(this)">
		   <%for(Iterator it  = apps.iterator(); it.hasNext();) { DOApplication doa = (DOApplication)it.next(); 
				   
		   %>
		   <option value="<%=doa.getObjUid()%>;<%=doa.getOuterLinkPaneStr()%>" <% if(doa.getObjUid().equals(default_app_uid)) { %>selected<%}%>><%=doa.getL10n()%></option>
			<%} %>
			
		    <option value="f">--------------</option>
		    <option value="a"><%=I18n.instance().get("从AppShare安装")%></option>
		   </select>
		&nbsp;&nbsp;
		
		<a onclick="loadPml({'pml':'PM_do_org_user_update_password','target':'_opener','title':'Reset Password'})" ><%=I18n.instance().get("Reset Password")%></a>
		 |
		 <a onclick="helpme();" ><%=I18n.instance().get("Help")%></a>  | <a onclick="logOff()" ><%=I18n.instance().get("Logout")%></a>
	</div>
</div>