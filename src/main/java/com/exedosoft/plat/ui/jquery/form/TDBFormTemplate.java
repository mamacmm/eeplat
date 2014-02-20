package com.exedosoft.plat.ui.jquery.form;

import java.util.Map;

import com.exedosoft.plat.template.HtmlTemplateGenerator;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;

public class TDBFormTemplate  extends DOViewTemplate {

	public String getHtmlCode(DOIModel doimodel) {

		String s = "";
		try {

			DOFormModel fm = (DOFormModel) doimodel;
			Map<String, Object> data = this.putData(doimodel);
			data.put("data", fm.getData());
			s = HtmlTemplateGenerator.getContentFromDBTemplate(fm.getController().getTemplate(),
					data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}


}
