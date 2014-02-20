package com.exedosoft.plat.ui.html5.form;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.ui.jquery.form.DOBaseForm;
import com.exedosoft.plat.ui.jquery.form.DOValueResultList;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

public class TTableListItemResultList extends DOBaseForm {

	private static Log log = LogFactory.getLog(TTableListItemResultList.class);

	public TTableListItemResultList() {

		dealTemplatePath("/form/TTableListItemResultList.ftl");
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		if ("en".equals(DOGlobals.getValue("lang.local"))) {
			data.put("langlocal", "en");
		} else {
			data.put("langlocal", "zh");
		}
		DOFormModel fm = (DOFormModel) doimodel;

		/**
		 * 可变动态下拉列表， 根据连接的FORMMODEL，一般静态staticlist 确定使用的服务
		 */
		String html = "";
		boolean isDyn = false;

		if (fm.getLinkForms() != null && !fm.getLinkForms().isEmpty()
				&& fm.getInputConfig() != null) {
			isDyn = true;
		}

		String theValue = fm.getValue();

		if (theValue == null && fm.getData() != null
				&& fm.getRelationProperty() != null) {
			theValue = fm.getData().getValue(
					fm.getRelationProperty().getColName());
		}

		if (theValue != null && !"".equals(theValue.trim())) {

			DOBO corrBO = fm.getLinkBO();

			if (corrBO == null && fm.getLinkService() != null) {
				corrBO = fm.getLinkService().getBo();
			}
			if (theValue.indexOf(";") == -1) {
				BOInstance<?> bi = null;

				/**
				 * 可变动态下拉列表， 根据连接的FORMMODEL，一般静态staticlist 确定使用的服务
				 */
				if (isDyn) {
					DOFormModel linkFm = (DOFormModel) fm.getLinkForms().get(0);
					String theLinkValue = fm.getData().getValue(
							linkFm.getColName());

					if (theLinkValue != null) {

						List list = StringUtil.getStaticList(fm
								.getInputConfig());

						for (Iterator it = list.iterator(); it.hasNext();) {
							String[] halfs = (String[]) it.next();

							if ((theLinkValue != null && theLinkValue
									.equals(halfs[0]))) {
								DOService theCorrService = DOService
										.getService(halfs[1]);
								if (theCorrService != null) {
									corrBO = theCorrService.getBo();
								}
								break;
							}
						}
					}
					bi = DOValueResultList.getAInstance(null, corrBO, theValue);
				} else {
					bi = DOValueResultList.getAInstance(fm, corrBO, theValue);
				}

				// /
				// System.out.println("BOINSTANCE++++++++++++++++++++++++++++++++++++++"
				// + bi);

				if (bi != null) {
					// ///////////////////////////////////采用弹出方式
					DOPaneModel theModel = fm.getLinkPaneModel();
					if (theModel != null
							&& theModel.getLinkType() != null
							&& (theModel.getLinkType().intValue() == DOPaneModel.LINKTYPE_TREEMODEL)) {
						theModel = null;
					}
					html = this.getAjaxLink(bi, theModel, "_opener",
							fm.getEscapeDOClickJs());// /property.getContainerPaneName()
				}
			} else {
				String[] strs = theValue.split(";");
				StringBuffer buffer = new StringBuffer();
				for (int i = 0; i < strs.length; i++) {
					String aInsUid = strs[i];
					BOInstance bi = corrBO.getInstance(aInsUid);
					if (bi != null) {
						DOPaneModel theModel = fm.getLinkPaneModel();
						if (theModel != null
								&& theModel.getLinkType() != null
								&& (theModel.getLinkType().intValue() == DOPaneModel.LINKTYPE_TREEMODEL)) {
							theModel = null;
						}
						buffer.append(
								this.getAjaxLink(bi, theModel, "_opener",
										fm.getDoClickJs()))// property.getContainerPaneName()
								.append(";");
					}
				}
				html = buffer.toString();

			}

		}
		if (theValue == null || "null".equals(theValue)) {
			html =  "&nbsp;";
		}
		data.put("html", html);
		return data;
	}
}
