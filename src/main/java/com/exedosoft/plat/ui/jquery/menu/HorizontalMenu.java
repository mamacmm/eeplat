package com.exedosoft.plat.ui.jquery.menu;

import java.util.Iterator;

import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.util.DOGlobals;

public class HorizontalMenu extends DOBaseMenu {

	public String getHtmlCode(DOIModel aModel) {

		DOMenuModel rootMenu = (DOMenuModel) aModel;
		StringBuffer buffer = new StringBuffer();
		buffer.append("<ul>");

		for (Iterator it = rootMenu.retrieveChildren().iterator(); it.hasNext();) {
			DOMenuModel aMenu = (DOMenuModel) it.next();

			buffer.append("<li><em><a href=\"");
			if(aMenu.getMenuType() != null && aMenu.getMenuType()
					.intValue() == DOMenuModel.MENUTYPE_LINK){
				buffer.append(aMenu.getNote());
			}
			else if (aMenu.getLinkPane() != null
					|| aMenu.getLinkService() != null
					) {
				buffer.append("javascript:");
				this.appendLink(buffer, aMenu, aMenu.getEchoJs());
			} else {
				buffer.append("#");
			}
			buffer.append("\"><span>").append(aMenu.getL10n()).append("&nbsp;");
			if (aMenu.retrieveChildren() != null && aMenu.retrieveChildren().size() > 0) {
				buffer.append("<img src='")
						.append(DOGlobals.PRE_FULL_FOLDER)
						.append("simplelayout/images/zonebar-downarrow.png' alt='dropdown' /></em> ");
			}
			buffer.append("</span></a>");

			if (aMenu.retrieveChildren() != null) {
				buffer.append("<ul>");

				for (Iterator itChild = aMenu.retrieveChildren().iterator(); itChild
						.hasNext();) {
					DOMenuModel aChildMenu = (DOMenuModel) itChild.next();
					buffer.append("	<li><a href=\"");
					if (aChildMenu.getLinkPane() != null
							|| aChildMenu.getLinkService() != null) {
						buffer.append("javascript:");
						this.appendLink(buffer, aChildMenu,
								aChildMenu.getEchoJs());
						buffer.append("\">");
					}
					buffer.append(aChildMenu.getL10n()).append("</a><li></n>");
				}

				buffer.append("</ul>");
			}
			buffer.append("</li>");
		}
		buffer.append("</ul>");
		return buffer.toString();
	}
}
