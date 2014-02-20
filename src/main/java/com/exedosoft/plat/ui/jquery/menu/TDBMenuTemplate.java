package com.exedosoft.plat.ui.jquery.menu;

import java.util.Map;

import com.exedosoft.plat.template.HtmlTemplateGenerator;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.ui.DOViewTemplate;

public class TDBMenuTemplate  extends DOViewTemplate {

	public String getHtmlCode(DOIModel doimodel) {

		String s = "";
		try {
			DOMenuModel mm = (DOMenuModel) doimodel;

			Map<String, Object> data = this.putData(doimodel);

			s = HtmlTemplateGenerator.getContentFromDBTemplate(mm.getController().getTemplate(),
					data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}


}
