package com.exedosoft.plat.ui.jquery.pane;

import java.util.HashMap;
import java.util.Map;

import com.exedosoft.plat.bo.DOResource;
import com.exedosoft.plat.template.HtmlTemplateGenerator;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIViewTemplate;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;

public class TDBPaneTemplate implements DOIViewTemplate {

	public String getHtmlCode(DOIModel doimodel) {

		String s = "";
		try {
			DOPaneModel pm = (DOPaneModel) doimodel;

			Map<String, Object> data = this.putData(doimodel);

			s = HtmlTemplateGenerator.getContentFromDBTemplate(pm
					.getController().getTemplate(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		
		
		 String logourl = 	DOResource.getSpecialPath(DOResource.TYPE_LOGO_LOGIN);
		  if(logourl==null){
			  logourl = "web/default/images/logo.png";
		  }
		  data.put("logourl", logourl);
		  
		  
		  try {
			data.put("tenantName", DOGlobals.getInstance().getServletContext().getRequest().getAttribute("tenantName") );
			
			System.out.println("Jeeee::" + DOGlobals.getInstance().getServletContext().getRequest().getAttribute("tenantName"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		return data;
	}

}
