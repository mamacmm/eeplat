package com.exedosoft.plat.ui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.template.HtmlTemplateGenerator;
import com.exedosoft.plat.util.DOGlobals;

public abstract class DOViewTemplate implements DOIViewTemplate {

	protected String templateFile = "";

	public DOViewTemplate() {
		if (this.getClass().getCanonicalName().indexOf("form") != -1) {
			dealTemplatePath("/form/" + this.getClass().getSimpleName()
					+ ".ftl");
		} else if (this.getClass().getCanonicalName().indexOf("grid") != -1) {
			dealTemplatePath("/grid/" + this.getClass().getSimpleName()
					+ ".ftl");
		} else if (this.getClass().getCanonicalName().indexOf("menu") != -1) {
			dealTemplatePath("/menu/" + this.getClass().getSimpleName()
					+ ".ftl");
		} else if (this.getClass().getCanonicalName().indexOf("pane") != -1) {
			dealTemplatePath("/panel/" + this.getClass().getSimpleName()
					+ ".ftl");
		} else if (this.getClass().getCanonicalName().indexOf("tree") != -1) {
			dealTemplatePath("/tree" + this.getClass().getSimpleName() + ".ftl");
		}
	}

	private static Log log = LogFactory.getLog(DOViewTemplate.class);

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
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);

		try {
			if (SessionContext.getInstance().getServletContext() != null) {
				data.put("requestContextPath", SessionContext.getInstance()
						.getServletContext().getRequest().getContextPath());
			} else {
				if (DOGlobals.URL.indexOf("/") != -1) {
					data.put("requestContextPath", "/");
				} else {
					data.put("requestContextPath", DOGlobals.URL);
				}
			}
		} catch (Exception e1) {
			
			e1.printStackTrace();

			if (DOGlobals.URL.indexOf("/") != -1) {
				data.put("requestContextPath", "/");
			} else {
				data.put("requestContextPath", DOGlobals.URL);
			}
		}
		if(data.get("requestContextPath").equals("")){
			data.put("requestContextPath", "/");
		}
		
		if ("en".equals(DOGlobals.getValue("lang.local"))) {
			data.put("langlocal", "en");
		} else {
			data.put("langlocal", "zh");
		}
		return data;
	}

	/**
	 * 只有缺省的jquery 控制器才使用dealTemplatePath这个方法。 其它的控制器要所以用this.template = ，
	 * 否则会多加一遍prefix 造成模板找不到，而采用缺省jquery 的。
	 * 
	 * @param ftlFile
	 */

	protected void dealTemplatePath(String ftlFile) {
		this.templateFile = ftlFile;// getWebStylePath(ftlFile);
	}

	public static String getWebStylePath(String ftlFile) {

		// /先这样,应该可以的.

		// System.out.println("begin:::::::::::::" +
		// System.currentTimeMillis());

		String prefix = "default";

		if (SessionContext.getInstance().getWebStylePath() != null) {
			prefix = SessionContext.getInstance().getWebStylePath();
		} else if (DOGlobals.getValue("jslib.template.path") != null
				&& !"".equals(DOGlobals.getValue("jslib.template.path"))) {
			prefix = DOGlobals.getValue("jslib.template.path");
		} else {
			prefix = "default";
		}

		if (prefix != null && !prefix.trim().equals("")) {

			File aFile = new File(HtmlTemplateGenerator.getTemplatePath()
					+ prefix + ftlFile);
			if (!aFile.exists()) {
				prefix = "default";
			}
		}

		// // System.out.println("end:::::::::::::" +
		// System.currentTimeMillis());

		return prefix + ftlFile;
	}

}
