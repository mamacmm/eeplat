package com.exedosoft.plat.ui.html5.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DOViewTemplate;

public class TInlineGrid extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TInlineGrid.class);

	public TInlineGrid() {

		dealTemplatePath("/form/TInlineGrid.ftl");
	}

}
