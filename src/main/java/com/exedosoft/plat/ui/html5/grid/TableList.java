package com.exedosoft.plat.ui.html5.grid;

import java.util.ArrayList;
import java.util.HashMap;
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
public class TableList extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TableList.class);

	public TableList() {

		dealTemplatePath("/grid/Html5TableList.ftl");
	}

	public Map<String, Object> putData(DOIModel doimodel) {
		
		List list = new ArrayList();
		if (((DOGridModel) doimodel).getService() != null) {
			list = ((DOGridModel) doimodel).getService().invokeSelect();
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
