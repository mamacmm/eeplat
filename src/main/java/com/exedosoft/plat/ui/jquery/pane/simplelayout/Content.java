package com.exedosoft.plat.ui.jquery.pane.simplelayout;

import java.util.List;
import java.util.Map;

import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.jquery.pane.TPaneTemplate;


public class Content extends TPaneTemplate {

	public Content() {
		dealTemplatePath(  "/panel/simplelayout/Content.ftl" );
	}
	
	
	public Map<String, Object> putData(DOIModel doimodel) {

		DOPaneModel pm = (DOPaneModel) doimodel;
		List children = pm.retrieveChildren();
		if(children!=null && children.size() >0 ){
			return super.putData((DOPaneModel)children.get(0));
		}
		return super.putData(pm);

	}


}
