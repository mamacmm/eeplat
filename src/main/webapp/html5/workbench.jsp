<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.*" %>
<%@ page import="com.exedosoft.plat.util.*" %>
<%@ page import="com.exedosoft.plat.bo.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.json.*" %>
<%@ page import="com.exedosoft.plat.ui.html5.util.*" %>

<%
	SessionContext context = (SessionContext) session.getAttribute("userInfo");
	BOInstance usr = context.getUser();
	if(null == session.getAttribute("userInfo") || usr == null){
		response.sendRedirect(request.getContextPath() + "/web/html5/logoff.jsp");
	}
	
	List<BOInstance> lsbi = DOService.getService("kids_menu_rule_list_noicon").invokeSelect();
	JSONArray jsonarr = new JSONArray();
	JSONObject ljson = new JSONObject();
	ljson.put("uid", "uidkeymessage");
	ljson.put("id", "message");
	ljson.put("icon", "msg");
	ljson.put("linkpage", "PM_kids_message_Main_html5");
	ljson.put("order", 0);
	ljson.put("name", "消息");
	jsonarr.put(ljson);
	if(lsbi.size()>0){
		Iterator<BOInstance> itbi = lsbi.iterator();
		while(itbi.hasNext()){
			BOInstance onemenurule = itbi.next();
			JSONObject json = new JSONObject();
			json.put("uid", onemenurule.getUid());
			json.put("id", onemenurule.getValue("menuid"));
			json.put("icon", onemenurule.getValue("menuicon"));
			json.put("linkpage", onemenurule.getValue("viewpc"));
			json.put("order", onemenurule.getIntValue("ordernum"));
			json.put("name", onemenurule.getValue("menuname"));
			jsonarr.put(json);
		}
	}
%>
<div id='html5-workbench' class='html5-scrollcontent html5-noscrollbar wh100 margintop3px bgpiczhiliaoshu'>
	
</div>
<script type='text/javascript'>
	(function(){
		var strhtml = $.menulist(<%=jsonarr.toString()%>);
		$('#html5-workbench').html(strhtml);
		$('#html5-workbench').scroll(function(){
			//alert('scroll');
		});
		$('#html5-workbench').get(0).scrollTop = 10;
	})();
</script>