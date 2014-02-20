package com.exedosoft.plat.ui.jquery.pane;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ui.DOBaseView;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOMenuLinks;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOTreeModel;
import com.exedosoft.plat.util.DOGlobals;

public abstract class DOBasePaneView extends DOBaseView {

	private static Log log = LogFactory.getLog(DOBasePaneView.class);

	/**
	 * @param buffer
	 * @param pm
	 */
	protected void appendAttrs(StringBuffer buffer, DOPaneModel pm) {

		if (pm.getL10n() != null) {
			buffer.append(" label='").append(pm.getL10n()).append("' ");
		}

		if (pm.getLayoutAlign() != null) {
			buffer.append("  layoutAlign='").append(pm.getLayoutAlign())
					.append("'");
		}
		if (pm.getAlign() != null) {
			buffer.append("  align='").append(pm.getAlign()).append("'");
		}

		if (pm.getController().getStyle() != null) {
			buffer.append(" style='").append(pm.getController().getStyle())
					.append("' ");
		}
		if (pm.getController().getStandbyAttrs() != null) {
			buffer.append(" ").append(pm.getController().getStandbyAttrs())
					.append(" ");
		}
	}

	public static void genePaneContext(StringBuffer buffer, DOPaneModel pm) {

		// log.info("Enter DOBasePaneView=======================");
		// log.info("LinkType:::" + pm.getLinkType());
		// log.info("LinkUID::::" + pm.getLinkUID());

		// buffer.append("<script>TANGER_RemoveNode();</script>");
		List children = pm.retrieveChildren();
		if (children != null && children.size() > 0) {
			// log.info("The Enter PaneModel'Name::::::::" + pm.getName());
			// log.info("The Enter PaneModel'childrens:::" +
			// pm.retrieveChildren());

			if (children.size() > 1
					&& pm.getLayOutType() != null
					&& pm.getLayOutType().intValue() == DOPaneModel.LAYOUT_HORIZONTAL) {

				///////////////bootstrap 左右分割
				if ("boostrap"
						.equals(DOGlobals.getValue("jslib.template.path"))) {
					
					String spanSize = "span6";
					
					if(children.size() == 3){
						spanSize = "span4";
					}
					
					if(children.size() == 4){
						spanSize = "span3";
					}
					
					if(children.size() > 4){
						spanSize = "span2";
					}
					
					
					for (Iterator it = children.iterator(); it.hasNext();) {
						DOPaneModel model = (DOPaneModel) it.next();
						if (it.hasNext()) {
							buffer.append(" <div class='").append(spanSize);
							buffer.append("'>");
							buffer.append(model.getHtmlCode());
							buffer.append("</div>");
						}
					}
					
				} else {

					int defaultPercentage = BigDecimal
							.valueOf(100 / children.size()).setScale(0)
							.intValue();

					DOPaneModel rightPaneModel = (DOPaneModel) children
							.get(children.size() - 1);
					int sizerWidth = defaultPercentage;
					if (rightPaneModel.getSizerWidth() != null) {

						sizerWidth = rightPaneModel.getSizerWidth();
					}
					buffer.append(" <div style='float: right;  margin: 0 0 0 -")
							.append(100 - sizerWidth)
							.append("%; width: 100%;height:100%;'> \n   <div style='margin: 0 0 0 ")
							.append(100 - sizerWidth).append("%; height:100%;");

					if (rightPaneModel.getAlign() != null) {
						buffer.append(" align:").append(
								rightPaneModel.getAlign());
					}

					buffer.append("'>");

					buffer.append(rightPaneModel.getHtmlCode());
					buffer.append("</div>\n</div>");

					for (Iterator it = children.iterator(); it.hasNext();) {
						DOPaneModel model = (DOPaneModel) it.next();
						if (it.hasNext()) {
							sizerWidth = defaultPercentage;
							if (model.getSizerWidth() != null) {
								sizerWidth = model.getSizerWidth();
							}

							buffer.append(" <div style='float: left; width: ")
									.append(sizerWidth)
									.append("%;height:100%;");

							if (model.getAlign() != null) {
								buffer.append(" align:").append(
										model.getAlign());
							}

							buffer.append("'>");
							buffer.append(model.getHtmlCode());
							buffer.append("</div>");
						}
					}
				}

			} else {

				for (Iterator it = children.iterator(); it.hasNext();) {
					DOPaneModel model = (DOPaneModel) it.next();
					buffer.append(model.getHtmlCode());
				}
			}
		} else {

			// ////////////////锟饺达拷印锟剿碉拷锟斤拷息

			// List menus = DOMenuLinks.getMenusByPaneModelUid(pm.getObjUid());
			// DOMenuModel showMenu = null;
			//
			// if (menus != null) {
			// for (Iterator it = menus.iterator(); it.hasNext();) {
			// DOMenuModel mm = (DOMenuModel) it.next();
			// mm.setContainerPane(pm);
			// buffer.append(mm.getHtmlCode());
			// }
			// showMenu = DOMenuLinks
			// .getShowMenuByPaneModelUid(pm.getObjUid());
			// if (showMenu != null) {
			// buffer.append(showMenu.getLinkPane().getHtmlCode());
			//
			// }
			// }

			if (pm.getLinkType() != null) {
				switch (pm.getLinkType().intValue()) {

				case DOPaneModel.LINKTYPE_MENU:
					if (pm.getLinkUID() != null
							&& !"".equals(pm.getLinkUID().trim())) {
						try {
							DOMenuModel mm = pm.getMenuModel();
							if (pm.getLinkController() != null) {
								mm.setController(pm.getLinkController());
							}
							mm.setContainerPane(pm);
							buffer.append(mm.getHtmlCode());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					break;

				case DOPaneModel.LINKTYPE_GRIDMODEL:
					if (pm.getLinkUID() != null
							&& !"".equals(pm.getLinkUID().trim())) {
						try {
							DOGridModel gm = pm.getDOGridModel();
							if (pm.getLinkController() != null) {
								gm.setController(pm.getLinkController());
							}
							gm.setContainerPane(pm);
							buffer.append(gm.getHtmlCode());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					break;
				case DOPaneModel.LINKTYPE_TREEMODEL:

					if (pm.getLinkUID() != null
							&& !"".equals(pm.getLinkUID().trim())) {

						try {
							DOTreeModel tm = pm.getTreeModel();
							if (pm.getLinkController() != null) {
								tm.setController(pm.getLinkController());
							}
							tm.setContainerPane(pm);
							if (pm.getDropDownID() != null) {
								tm.setDropDownID(pm.getDropDownID());
							}
							buffer.append(tm.getHtmlCode());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					break;

				}
			}

		}
	}

}
