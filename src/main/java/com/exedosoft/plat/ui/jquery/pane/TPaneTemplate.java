package com.exedosoft.plat.ui.jquery.pane;

import java.util.List;
import java.util.Map;

import com.exedosoft.plat.SSOController;
import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.WebStyle;
import com.exedosoft.plat.bo.DOApplication;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

public class TPaneTemplate extends DOViewTemplate {

	public Map<String, Object> putData(DOIModel doimodel) {

		DOPaneModel pm = (DOPaneModel) doimodel;
		
		/**
		 * 处理多套风格不一致的情况
		 */
		dealWebStyle(pm);

		Map<String, Object> data = super.putData(doimodel);

		StringBuffer sbItems = new StringBuffer();
		List children = pm.retrieveChildren();
		boolean isMobile = false;

		try {
			if (SSOController.isMobile()) {
				isMobile = true;
				data.put("app_index", "/" + DOGlobals.URL + "/" + pm.getCategory().getPakage()
						.getApplication().getName()
						+ "_mobile_pane.pml");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (isMobile && children != null && children.size() > 0) {
			DOBasePaneView.genePaneContext(sbItems,
					(DOPaneModel) children.get(0));
			// /直接把第一个作为总面板的替代
			data.put("model", children.get(0));
			String firstName = ((DOPaneModel) children.get(0)).getName();
			java.util.Iterator it = children.iterator();
			it.next();
			if (it.hasNext()) {
				StringBuffer footer = new StringBuffer();
				int i = 0;
				for (; it.hasNext();) {
					DOPaneModel onePm = (DOPaneModel) it.next();
					if (!(i == 0 && (firstName.endsWith("_Condition") || firstName.endsWith("_Criteria")) && onePm
							.getName().endsWith("_Result"))) {
						footer.append("<li><a href='/")
								.append(DOGlobals.URL)
								.append("/")
								.append(onePm.getName())
								.append(".pml' data-theme='b' data-icon='forward'>")
								.append(onePm.getTitle()).append("</a></li>\n");
					}
					i++;
				}
				if (footer.length() > 0) {
					data.put("footer", footer.toString());
				}
			}
			// ////需要追加，后面面板的链接
			// ///第一个很容易添加，如果多个，需要找parent 然后再查找兄弟

		} else {
			String firstName = pm.getName();

			DOPaneModel parent = pm.getParent();
			if (isMobile && parent != null) {
				StringBuffer footer = new StringBuffer();
				children = parent.retrieveChildren();
				int i = 0;
				int cur = children.size();
				String dataIcon = "forward";
				for (java.util.Iterator it = children.iterator(); it.hasNext();) {
					DOPaneModel onePm = (DOPaneModel) it.next();
					if (onePm.equals(pm)) {
						cur = i;
					}
					if (i < cur) {
						dataIcon = "back";
					} else if (i > cur) {
						dataIcon = "forward";
					}
					if (cur != i
							&& (!(i == (cur + 1)
									&& (firstName.endsWith("_Condition") || firstName.endsWith("_Criteria") )&& onePm
									.getName().endsWith("_Result")))) {
						footer.append("<li><a href='/").append(DOGlobals.URL)
								.append("/").append(onePm.getName())
								.append(".pml' data-theme='b' data-icon='")
								.append(dataIcon).append("'>")
								.append(onePm.getTitle()).append("</a></li>\n");
					}
					i++;
				}
				if (footer.length() > 0) {
					data.put("footer", footer.toString());
				}
			}
			DOBasePaneView.genePaneContext(sbItems, pm);
		}
		
		if(DOGlobals.getInstance().getSessoinContext().getUser()!=null){
			data.put("user", DOGlobals.getInstance().getSessoinContext().getUser().getMap());
		}
		data.put("items_html", sbItems.toString());

		return data;
	}

	/**
	 * 为了处理多套风格不一致的情况.
	 * @param paneModel
	 */
	private void dealWebStyle(DOPaneModel paneModel){

		SessionContext us = DOGlobals.getInstance().getSessoinContext();
		// /////////用户级别的界面风格
		if (us.getUserWebStyle() == null) {
			DOApplication theApplication = paneModel.getCategory()
					.getPakage().getApplication();
			if (theApplication != null
					&& theApplication.getWebStylePath() != null) {
				String webStylePath = theApplication
						.getWebStylePath();
				// ///系统层面的界面风格
				System.out.println("webStylePath:::::::::::" + webStylePath);
				us.setWebStylePath(webStylePath);
			}
		}

	}
	
}
