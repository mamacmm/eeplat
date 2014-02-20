package com.exedosoft.plat.ui.jquery.pane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;



/**
 *	左侧菜单样式的控制器
 */
public class MainPage extends TPaneTemplate {

	public MainPage() {
		dealTemplatePath(  "/panel/MainPage.ftl");
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {

		DOPaneModel pm = (DOPaneModel) doimodel;
		
		Map<String, Object> data = super.putData(doimodel);
		data.put("model", pm);
			
//		StringBuffer sbItems = new StringBuffer();
//		DOBasePaneView.genePaneContext(sbItems, pm);
//		data.put("items_html", sbItems.toString());
		List child = pm.retrieveChildren();
		String resourceUrl = "";
		if(child!=null && child.size()>0){
			DOPaneModel aPM = (DOPaneModel)child.get(0);
			resourceUrl = aPM.getName() + ".pml";
		} else
		if(pm.getResource()!=null){
			resourceUrl = pm.getResource().getResourcePath();
		}
		data.put("resourceUrl", resourceUrl);
		return data;
	}

}
