package com.exedosoft.plat.ui.jquery.pane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.SSOController;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;

public class Toolbar extends Tab {

	public Toolbar() {
		
		dealTemplatePath(  "/panel/ToolbarPanel.ftl");
	}
	
	@Override
	public Map<String, Object> putData(DOIModel doimodel) {

		DOPaneModel pm = (DOPaneModel) doimodel;
		DOPaneModel conditionPane = pm.getHiddenPane();
		List items = pm.retrieveChildren();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", pm);
		if(conditionPane!=null && conditionPane.getGridModel()!=null)
		data.put("conditionGrid", conditionPane.getGridModel());
		data.put("items", items);
		return data;
	}

}
