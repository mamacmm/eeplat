package com.exedosoft.plat.ui.jquery.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.SSOController;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

/**
 * @author aa
 */
public class GridListCheck extends DOViewTemplate {

	private static Log log = LogFactory.getLog(GridListCheck.class);

	public GridListCheck() {
		
		dealTemplatePath("/grid/GridListCheck.ftl");
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		DOGridModel gm = (DOGridModel) doimodel;
		if (gm.getService() == null) {
			return null;
		}
		Map<String, Object> data = super.putData(doimodel);
		
		List<BOInstance>  allData = gm.getService().invokeSelect();
		data.put("data",allData);
		
		if(gm.getSecondService()!=null){
			List selectedList = gm.getSecondService().invokeSelect();
			for(BOInstance aBI : allData){
				if(selectedList.contains(aBI)){
					aBI.putValue("eeplat_childtable_selected", "true");					
				}
			}
		}
		
		if (gm.getContainerPane() != null) {
			data.put("pmlName", gm.getContainerPane().getName());
		}
		String formName =  "a" + gm.getObjUid(); 


		if (gm.getContainerPane() != null
				&& gm.getContainerPane().getParent() != null) {

			// //自动判断条件面板
			List children = gm.getContainerPane().getParent()
					.retrieveChildren();

			if (children != null && children.size() == 2) {
				DOPaneModel conditionPane = (DOPaneModel) children.get(0);
				DOPaneModel resultModel = (DOPaneModel) children.get(1);
				if (conditionPane != null) {
					if (conditionPane.getDOGridModel() != null) {
						formName = "a"
								+ conditionPane.getDOGridModel().getObjUid();
					}
				}

			}
		}
		// //如果配置了查询条件面板（这里的含义是 拥有表单的面板）
		DOPaneModel hpm = null;
		if(gm.getContainerPane()!=null){
			hpm = gm.getContainerPane().getHiddenPane();
		}
		if (hpm != null) {
			if (hpm.getDOGridModel() != null) {
				formName = "a" + hpm.getDOGridModel().getObjUid();
			}
		}
		if(gm.getBottomOutGridFormLinks().size()>0){
			DOFormModel firstFm = (DOFormModel)gm.getBottomOutGridFormLinks().get(0);
			if(firstFm.isHidden()){
				formName = firstFm.getTargetForms();
			}
		}
		data.put("formName",formName);

		return data;
	}

}
