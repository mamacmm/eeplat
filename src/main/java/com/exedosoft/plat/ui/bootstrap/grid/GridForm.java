package com.exedosoft.plat.ui.bootstrap.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

public class GridForm extends DOViewTemplate {

	public GridForm() {
		dealTemplatePath("/grid/GridForm.ftl");

	}

	public Map<String, Object> putData(DOIModel doimodel) {

		List list = new ArrayList();

		DOGridModel gm = (DOGridModel) doimodel;
		if (gm.getService() != null) {
			list = gm.getService().invokeSelect();
		}

		Map<String, Object> data = super.putData(doimodel);

		// /判断是否为弹出窗口的情况
		data.put("isdialog", false);
		DOPaneModel pm = gm.getContainerPane();
		if (pm != null) {
			List children = pm.retrieveChildren();
			if (children == null || children.size() == 0) {
				try {
					String eeplatdialog = DOGlobals.getInstance()
							.getSessoinContext().getFormInstance()
							.getValue("eeplatdialog");
					System.out.println("Dialog::" + eeplatdialog);
					if ("true".equals(eeplatdialog)) {
						data.put("isdialog", true);
					}
				} catch (Exception e) {
				}
			}
		}

		data.put("model", doimodel);
		if (list.size() > 0) {
			BOInstance ins = (BOInstance) list.get(0);
			data.put("data", ins);
		}
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		return data;
	}

}
