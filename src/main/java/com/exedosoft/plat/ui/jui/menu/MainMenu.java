package com.exedosoft.plat.ui.jui.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.exedosoft.plat.bo.DOApplication;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.ui.jquery.menu.DOBaseMenu;

public class MainMenu extends DOBaseMenu {
	



	public String getHtmlCode(DOIModel aModel) {

		DOMenuModel rootMenu = (DOMenuModel) aModel;
		StringBuffer buffer = new StringBuffer();
		buffer.append("     <div id='leftside'> \n");
		buffer.append("     	<div id='sidebar_s'>\n");
		buffer.append("     		<div class='collapse'>\n");
		buffer.append("     			<div class='toggleCollapse'><div></div></div>\n");
		buffer.append("     		</div>\n");
		buffer.append("     	</div>\n");
		buffer.append("     	<div id='sidebar'>\n");
		buffer.append("     		<div class='toggleCollapse'><h2>"+rootMenu.getL10n()+"</h2><div>收缩</div></div>\n");
		

		buffer.append("     		<div class='accordion' fillSpace='sidebar'>\n");
	
		
		

		
		List rootChildMenus = rootMenu.retrieveChildren();
		
		/**
		 * 对default菜单的映射，如果第一层菜单的名字和应用的名称相同，则直接把该菜单下面的自菜单直接放入第一层，而该菜单不再显示。
		 * 
		 * 或者只有一层菜单
		 */
//		try {
//			if(rootChildMenus!=null && rootChildMenus.size() >0 ){
//				DOMenuModel aChildMenu = (DOMenuModel)rootChildMenus.get(0);
//				if((aChildMenu.getName().equals(rootMenu.getCategory().getPakage().getApplication().getName()))
//						|| (rootChildMenus.size() < 3)){
//					rootChildMenus.remove(aChildMenu);
//					rootChildMenus.addAll(0,aChildMenu.retrieveChildren());
//				}
//			}
//		} catch (Exception e) {
//
//		}
//		
		

		for (Iterator it = rootChildMenus.iterator(); it.hasNext();) {
			DOMenuModel aMenu = (DOMenuModel) it.next();

			buffer.append("<div class='accordionHeader'>\n");
			buffer.append("<h2><span>Folder</span>"+aMenu.getL10n()+"</h2>\n");
			buffer.append("</div>\n");
			
			if (aMenu.retrieveChildren() != null && !aMenu.retrieveChildren().isEmpty()) {
				buffer.append("<div class='accordionContent'>\n");
				buffer.append("<ul class='tree treeFolder'>\n");
				for (Iterator it2 = aMenu.retrieveChildren().iterator(); it2.hasNext();) {
					DOMenuModel aChildMenu = (DOMenuModel) it2.next();
//					buffer.append("<li><a href='tabsPage.html' target='navTab'>"+aChildMenu.getL10n()+"</a>\n");
					
					buffer.append("<li> ");
					buffer.append(" <a target='navTab' href=\"");
					if(aChildMenu.getLinkPane()!=null){
						buffer.append(aChildMenu.getLinkPane().getCorrHref());
					}
					buffer.append("\" rel='"+aChildMenu.getL10n()+"' fresh='false' >").append(aChildMenu.getL10n()).append("</a>");

					
					if (aChildMenu.retrieveChildren() != null && !aChildMenu.retrieveChildren().isEmpty()) {
						buffer.append("<ul>\n");
						for (Iterator itChild = aChildMenu.retrieveChildren().iterator(); itChild.hasNext();) {
							DOMenuModel aChildMenu2 = (DOMenuModel) itChild.next();
//							buffer.append("<li><a href='main.html' target='navTab' rel='main'>"+aChildMenu2.getL10n()+"</a></li>\n");
				
							buffer.append("<li> ");
							buffer.append(" <a target='navTab'  href=\"");
							if(aChildMenu2.getLinkPane()!=null){
								buffer.append(aChildMenu2.getLinkPane().getCorrHref());
							}
							buffer.append("\" rel='"+aChildMenu2.getL10n()+"' fresh='false' >").append(aChildMenu2.getL10n()).append("</a></li>");
						
						}
						buffer.append("</ul>\n");
					}
					buffer.append("</li>\n");
				}
				buffer.append("</ul>\n");
				buffer.append("</div>\n");
			}
		}
			
		
		
		buffer.append("     	</div>\n");
		buffer.append("     </div>\n");
		buffer.append(" </div>\n");

		System.out.println("buffer::::::::::"+buffer.toString());
//			
//
//			buffer.append("<a href='#' onclick=\"");
//			if (aMenu.getLinkPane() != null
//					|| aMenu.getLinkService() != null) {
//					buffer.append("activeNavLi(this),");
//					this.appendLink(buffer, aMenu, aMenu.getEchoJs());
//			} 
//			
//			if (aMenu.retrieveChildren() != null && !aMenu.retrieveChildren().isEmpty()) {
//				buffer.append("\" class='dropdown-toggle' ");
//			}else{
//				buffer.append("\"  ");
//			}
//
//			buffer.append("><i  class='")
//			.append(this.getIcon(aMenu))
//			.append("'></i><span  class='menu-text'>")
//					.append(aMenu.getL10n()).append("</span>");
//
//			if (aMenu.retrieveChildren() != null  && !aMenu.retrieveChildren().isEmpty()) {
//				buffer.append(" <b class='arrow icon-angle-down'></b> ");
//			}
//
//			buffer.append("</a>\n");
//
//			// //submenu
//			if (aMenu.retrieveChildren() != null && !aMenu.retrieveChildren().isEmpty()) {
//				buffer.append("<ul class='submenu'>");
//
//				for (Iterator itChild = aMenu.retrieveChildren().iterator(); itChild
//						.hasNext();) {
//					DOMenuModel aChildMenu = (DOMenuModel) itChild.next();
//					buffer.append("	<li><a href='#' onclick=\"");
//					if (aChildMenu.getLinkPane() != null
//							|| aChildMenu.getLinkService() != null) {
//						buffer.append("javascript:activeNavLi(this,true),");
//						this.appendLink(buffer, aChildMenu,
//								aChildMenu.getEchoJs());
//					}
//					buffer.append("\">");
//
//					buffer.append("<i  class='icon-list'></i>").append(
//							aChildMenu.getL10n());
//					buffer.append("</a></li>\n");
//				}
//				buffer.append("</ul>");
//			}
//
//			buffer.append("</li>");
//		}
//		buffer.append("</ul>");
//
//		buffer.append("         <div class='sidebar-collapse' id='sidebar-collapse'>\n");
//		buffer.append("           <i class='icon-double-angle-left' data-icon1='icon-double-angle-left' data-icon2='icon-double-angle-right'></i>\n");
//		buffer.append("         </div>\n ");
//		
//
//	    buffer.append("   <script type='text/javascript'> \n ");
//		buffer.append(" try{ace.settings.check('sidebar' , 'collapsed')}catch(e){} \n");
//	    buffer.append(" </script> \n");
//		buffer.append("      </div>\n");
//
		return buffer.toString();
	}


	
	public static void main(String[] args){

		
	}
}