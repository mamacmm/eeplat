package com.exedosoft.plat.ui.bootstrap.pane;

import java.util.Map;

import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.jquery.pane.TPaneTemplate;



/**
 *	左侧菜单样式的控制器
 */
public class MainPage extends TPaneTemplate {

	public MainPage() {
		dealTemplatePath(  "/panel/MainPage.ftl");
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {

		DOPaneModel pm = (DOPaneModel) doimodel;
		
		Map<String, Object> data = super.putData(pm);
		data.put("model", pm);
			

		return data;
	}

}
