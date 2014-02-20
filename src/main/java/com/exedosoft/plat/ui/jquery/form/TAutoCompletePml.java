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
import com.sun.jndi.ldap.Ber;

/**
 * 
 * @author aa
 * 
 */
public class TAutoCompletePml extends DOValueResultList implements
		DOIViewTemplate {

	private static Log log = LogFactory.getLog(TAutoCompletePml.class);

	public TAutoCompletePml() {
		dealTemplatePath("/form/TAutoCompletePml.ftl");

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
				if (bi != null) {
					data.put("label", bi.getName());
				}
			}
		}

		if (property.getLinkPaneModel() != null) {
			StringBuffer popconfig = new StringBuffer(property.getLinkPaneModel().getName());
			
			popconfig.append(",");
			if (property.getLinkPaneModel().getPaneWidth() != null) {
				popconfig.append( property.getLinkPaneModel().getPaneWidth() );
			}
			
			popconfig.append(",");
			if (property.getLinkPaneModel().getPaneHeight() != null) {
				popconfig.append( property.getLinkPaneModel().getPaneHeight() );
			}
			
			popconfig.append(",");
			if (property.getLinkPaneModel().getTitle() != null) {
				popconfig.append( property.getLinkPaneModel().getTitle() );
			}

			popconfig.append(",");
			if (property.getGridModel() != null) {
				popconfig.append( "a" + property.getGridModel().getObjUid());
			}
			
			popconfig.append(",");
			if (property.getTargetPaneModel() != null) {
				popconfig.append( property.getTargetPaneModel().getName());
			}
			
			data.put("popconfig", popconfig.toString());
		}


		return data;
	}

}
