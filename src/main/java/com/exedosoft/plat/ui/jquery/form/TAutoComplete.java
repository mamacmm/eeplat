package com.exedosoft.plat.ui.jquery.form;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.template.HtmlTemplateGenerator;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIViewTemplate;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

/**
 * 在传统模式下不再使用，和jquery以及jquery ui版本匹配太难。
 * 只在bootstrap 模式下使用。
 * @author aa
 * 
 */
public class TAutoComplete extends DOValueResultList implements DOIViewTemplate {

	private static Log log = LogFactory.getLog(TAutoComplete.class);

	public TAutoComplete() {
		dealTemplatePath( "/form/TAutoComplete.ftl");

	}

	public String getHtmlCode(DOIModel doimodel) {

		Map<String, Object> data = this.putData(doimodel);

		String s = "";
		try {
			s = HtmlTemplateGenerator.getContentFromTemplate(this.templateFile,
					data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return s;
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		Map<String, Object> data = new HashMap<String, Object>();
		DOFormModel property = (DOFormModel) doimodel;
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		data.put("validRules", this.appendValidateConfig(property) );

		if (property.getData() != null) {

			String theValue = property.getValue();
			
		

			if (theValue != null) {
				DOBO corrBO = property.getLinkBO();

				if (corrBO == null && property.getLinkService() != null) {
					corrBO = property.getLinkService().getBo();
				}
				BOInstance bi = getAInstance(property, corrBO, theValue);
				if(bi!=null){
					data.put("label", bi.getName());
				}
			}
		}
		return data;
	}

}
