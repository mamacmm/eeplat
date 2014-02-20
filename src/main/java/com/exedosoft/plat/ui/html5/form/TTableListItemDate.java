package com.exedosoft.plat.ui.html5.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

public class TTableListItemDate extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TTableListItemDate.class);

	public TTableListItemDate() {

		dealTemplatePath("/form/TTableListItemDate.ftl");
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		if ("en".equals(DOGlobals.getValue("lang.local"))) {
			data.put("langlocal", "en");
		} else {
			data.put("langlocal", "zh");
		}
		DOFormModel fm = (DOFormModel) doimodel;
		String time = "";
		if (fm.getValue() != null && fm.getRelationProperty() != null) {

			// ////////////////精确到秒 yyyy-MM-dd HH:mm:ss
			if (fm.getInputConfig() != null) {
				time = StringUtil.getDateStr(
						fm.getData().getDateValue(fm.getRelationProperty()),
						fm.getInputConfig());
			} else {

				if ("en".equals(DOGlobals.getValue("lang.local"))) {
					time = StringUtil
							.getDateStr(
									fm.getData().getDateValue(
											fm.getRelationProperty()),
									"MM/dd/yyyy");
				} else {
					time = StringUtil
							.getDateStr(
									fm.getData().getDateValue(
											fm.getRelationProperty()),
									"yyyy-MM-dd");
				}
			}

		} else {
			time = "&nbsp;";
		}
		data.put("time", time);
		return data;
	}
}
