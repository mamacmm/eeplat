package com.exedosoft.plat.ui.html5.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DOViewTemplate;

public class TListViewNews extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TListViewNews.class);

	public TListViewNews() {

		dealTemplatePath("/form/TListViewNews.ftl");
	}

}
