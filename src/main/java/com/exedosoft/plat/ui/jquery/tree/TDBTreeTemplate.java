package com.exedosoft.plat.ui.jquery.tree;

import java.util.Map;

import com.exedosoft.plat.template.HtmlTemplateGenerator;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOTreeModel;
import com.exedosoft.plat.ui.DOViewTemplate;

public class TDBTreeTemplate  extends DOViewTemplate  {

	public String getHtmlCode(DOIModel doimodel) {

		String s = "";
		try {
			DOTreeModel tm = (DOTreeModel) doimodel;

			Map<String, Object> data = this.putData(doimodel);

			s = HtmlTemplateGenerator.getContentFromDBTemplate(tm
					.getController().getObjUid(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

}
