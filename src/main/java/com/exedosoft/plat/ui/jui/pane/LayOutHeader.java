package com.exedosoft.plat.ui.jui.pane;

import java.util.List;
import java.util.Map;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOApplication;
import com.exedosoft.plat.bo.DOResource;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.jquery.pane.TPaneTemplate;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;

public class LayOutHeader  extends TPaneTemplate {

	public LayOutHeader() {
		dealTemplatePath ( "/panel/LayOutHeader.ftl" );
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {
		
		Map<String, Object> data =  super.putData(doimodel);
		
		BOInstance user =  DOGlobals.getInstance().getSessoinContext().getUser();
		
		String default_app_uid = user.getValue("default_app_uid");
		boolean isDev = false;

		if("1".equals(user.getValue("asrole")) || "2".equals(user.getValue("asrole"))){
			isDev = true;
		}
		
		String title = "Applications";
		
		if(default_app_uid!=null){
			DOApplication defaultApp = DOApplication.getApplicationByID(default_app_uid);
			if(defaultApp!=null){
				title = defaultApp.getName();
			}
		}
			
		
		
		List<DOApplication> apps =  DAOUtil.INSTANCE().select(DOApplication.class,
				"select * from DO_Application  order by session_overDue  desc");
		
		
		StringBuffer buffer = new StringBuffer("<ul class=\"nav\">");
		buffer.append("<li><a>"+I18n.instance().get("Welcome")+"，"+user.getName()+" ！</a></li>");
		
		buffer.append("<li><a><select class=\"selectt\" onchange=\"changeApp(this)\">");
		for(DOApplication app : apps){
			if(!app.isAccess()){
				continue;
			}
			if(app.isDefault() && default_app_uid==null ){
				title = app.getName();
			}


			buffer.append("<option value="+app.getObjUid()+";"+app.getOuterLinkPaneStr()+"\"");
			if(app.getObjUid().equals(default_app_uid)) {
				buffer.append("selected");
			}
			buffer.append(">"+app.getL10n()+"</option>");
		}
		if(isDev){ 
			buffer.append("<option value=\"f\">--------------</option>");
			buffer.append("<option value=\"a\">"+I18n.instance().get("从AppShare安装")+"</option>");
	    }
		buffer.append("</select></a></li>");
		if(isDev){
			buffer.append("<li><a href=\"console.pml?isApp=true\" target=\"_blank\">"+I18n.instance().get("Setup")+"</a></li>");
		}
		buffer.append("<li><a href=\"http://code.google.com/p/eeplat\" target=\"_blank\">"+I18n.instance().get("Help")+"</a></li>");
		buffer.append("<li><a href=\"web/default/logoff.jsp\">"+I18n.instance().get("Logout")+"</a></li>");
		
		buffer.append("</ul>");
//		
//		
//		buffer.append("");
//		buffer.append("  <li class='nav-header'>\n");
//		buffer.append("  <i class='icon-gear'></i>\n");
//		buffer.append(apps.size()).append(" Applications \n");
//		buffer.append(" </li> \n");
//		
//
//
//		
//			buffer.append("<li>\n");
//
//			buffer.append("<a href=\"javascript:changeDefaultApp('")
//			.append(app.getObjUid())
//			.append("'),window.location='")
//				.append(app.getOuterLinkPaneStr())
//				.append("'\">\n");
//			buffer.append("<div class='clearfix'>\n");
//			buffer.append("		<span class='pull-left'> \n");
//			buffer.append("		<i class='btn btn-mini no-hover icon-folder-open'></i> \n");
//			buffer.append(app.getL10n());
//			buffer.append("		</span>\n");
//			buffer.append("		</div>\n");
//			buffer.append("	</a>\n");
//			buffer.append("</li>\n");
//		}
//
//		buffer.append("<li>\n");
//		buffer.append("	<a href=\"javascript:loadPml({'pml':'PM_multi_appshare_listall','target':'page-content'})\"> \n");
//		buffer.append(I18n.instance().get("从AppShare安装"));
//		buffer.append("		<i class='icon-arrow-right'></i> \n");
//		buffer.append("	</a>\n");
//		buffer.append("</li>\n");
//		buffer.append("</ul>\n");


		
		String url = 	DOResource.getSpecialPath(DOResource.TYPE_LOGO_SMALL);
			
			if(url!=null){
				data.put("logoheader", url);
			}
		
		
		data.put("title", title);

		data.put("appdropdown", buffer);
		
		data.put("isdev", isDev);

		
		return data;

	}
}