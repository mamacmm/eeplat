package com.exedosoft.plat.ui.html5.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DOViewTemplate;

public class THeaderRightService extends DOViewTemplate {

	private static Log log = LogFactory.getLog(THeaderRightService.class);

	public THeaderRightService() {

		dealTemplatePath("/form/THeaderRightServiceUf.ftl");
	}

}
