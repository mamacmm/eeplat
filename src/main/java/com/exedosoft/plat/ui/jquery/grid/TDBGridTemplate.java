package com.exedosoft.plat.ui.jquery.grid;

import java.util.Map;

import com.exedosoft.plat.template.HtmlTemplateGenerator;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.DOGlobals;

public class TDBGridTemplate extends GridSupportMore {

	public String getHtmlCode(DOIModel doimodel) {
		String s = "";
		try {
			DOGridModel gm = (DOGridModel) doimodel;

			Map<String, Object> data = putData(doimodel);
			
			
			data.put("globals", DOGlobals.globalConfigs);
			
			data.put("form", DOGlobals.getInstance().getSessoinContext().getFormInstance().getMap());
			s = HtmlTemplateGenerator.getContentFromDBTemplate(gm
					.getController().getTemplate(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
