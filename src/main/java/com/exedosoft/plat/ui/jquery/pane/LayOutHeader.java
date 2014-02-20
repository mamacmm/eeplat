package com.exedosoft.plat.ui.jquery.pane;

import java.util.Map;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.bo.DOResource;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;


/**
 *	头面板的样式控制器
 */
public class LayOutHeader extends TPaneTemplate {

	public LayOutHeader() {
		
		dealTemplatePath(  "/panel/LayOutHeader.ftl");
		
		
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {

		Map data = super.putData(doimodel);
		DOPaneModel paneModel = (DOPaneModel)doimodel;
		
	   String url = 	DOResource.getSpecialPath(DOResource.TYPE_LOGO_SMALL);
		
		if(url!=null){
			data.put("logoheader", url);
		}
		return data;
	}
	

}
