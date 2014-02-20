package com.exedosoft.plat.ui.jquery.pane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.action.MainPageMessages;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.bo.BOInstance;


/**
 *	左侧菜单样式的控制器
 */
public class MainPageDirect extends TPaneTemplate {

	public MainPageDirect() {
		dealTemplatePath(  "/panel/MainPageDirect.ftl");
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {
		
		DOPaneModel pm = (DOPaneModel) doimodel;
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("model", pm);
		
		Map map = MainPageMessages.getMessages();
	
		if(map != null) {	 
			 String pending = (String)map.get("pending");
			 data.put("pending",pending);
		}
	
		
		
			
//		StringBuffer sbItems = new StringBuffer();
//		DOBasePaneView.genePaneContext(sbItems, pm);
//		data.put("items_html", sbItems.toString());
		List child = pm.retrieveChildren();
		String resourceUrl = "";
		if(child!=null && child.size()>0){
			DOPaneModel aPM = (DOPaneModel)child.get(0);
			resourceUrl = aPM.getName() + ".pml";
		}
		if(pm.getResource()!=null){
			resourceUrl = pm.getResource().getResourcePath();
		}
		data.put("resourceUrl", resourceUrl);
		return data;
	}

}
