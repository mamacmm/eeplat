package com.exedosoft.plat.ui.jquery.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

public class GridSupportMoreRules extends DOViewTemplate {

	public GridSupportMoreRules() {
		
		dealTemplatePath( "/grid/GridSupportMore.ftl");

	}
	
	
	public Map<String, Object> putData(DOIModel doimodel) {
		
		

		List list = new ArrayList();
		DOService theService = ((DOGridModel)doimodel).getService(); 
		if(theService!=null){
			try {
				theService.fireBeforRules();
				list = theService.invokeSelect();
				theService.fireAfterRules();
			} catch (ExedoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", doimodel);
		if (list.size() > 0) {
			BOInstance ins = (BOInstance) list.get(0);
			data.put("data", ins);
		}
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		return data;
	}

}
