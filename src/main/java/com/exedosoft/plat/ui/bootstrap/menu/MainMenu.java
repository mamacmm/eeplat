package com.exedosoft.plat.ui.bootstrap.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.exedosoft.plat.bo.DOApplication;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.ui.jquery.menu.DOBaseMenu;

public class MainMenu extends DOBaseMenu {
	
	private static List<String> icons = new ArrayList<String>();
	
	static{
		
		icons.add("icon-list");
		icons.add("icon-building");
		icons.add("icon-check-empty");
		icons.add("icon-cogs");
		icons.add("icon-cog");
		icons.add("icon-credit-card");
		icons.add("icon-flag");
		icons.add("icon-info");
		icons.add("icon-leaf");
		icons.add("icon-search");
		
	}
	

	public String getHtmlCode(DOIModel aModel) {

		DOMenuModel rootMenu = (DOMenuModel) aModel;
		StringBuffer buffer = new StringBuffer();

		buffer.append("     <a class='menu-toggler' id='menu-toggler' href='#'> \n");
		buffer.append("         <span class='menu-text'></span>\n");
		buffer.append("      </a>\n");

		buffer.append("<div class='sidebar' id='sidebar'>\n");
		
		buffer.append("      <ul class='nav nav-list'>\n");

//		buffer.append("          <li class='active'> \n");
//		buffer.append("             <a href='#'");
//		
//		DOApplication currentUsedApp = DOApplication.getCurrentUsedApplication();
//		if(currentUsedApp!=null){
//			buffer.append(" onclick=\"activeNavLi(this),loadPml({'pml':'")
//			.append(currentUsedApp.getName())
//			.append("_MainContent','target':'page-content'}) \"");
//						
//		}
//		
//		buffer.append("> \n");
//		buffer.append("             <i class='icon-dashboard'></i> \n");
//		buffer.append("             <span class='menu-text'>Home</span> \n");
//		buffer.append("          </a>\n</li>\n");
//		
		
		List rootChildMenus = rootMenu.retrieveChildren();
		
		/**
		 * 对default菜单的映射，如果第一层菜单的名字和应用的名称相同，则直接把该菜单下面的自菜单直接放入第一层，而该菜单不再显示。
		 * 
		 * 或者只有一层菜单
		 */
		try {
			if(rootChildMenus!=null && rootChildMenus.size() >0 ){
				DOMenuModel aChildMenu = (DOMenuModel)rootChildMenus.get(0);
				if((aChildMenu.getName().equals(rootMenu.getCategory().getPakage().getApplication().getName()))
						|| (rootChildMenus.size() < 3)){
					rootChildMenus.remove(aChildMenu);
					rootChildMenus.addAll(0,aChildMenu.retrieveChildren());
				}
			}
		} catch (Exception e) {

		}
		


		for (Iterator it = rootChildMenus.iterator(); it.hasNext();) {
			DOMenuModel aMenu = (DOMenuModel) it.next();

			buffer.append("<li>\n");

			buffer.append("<a href='#' onclick=\"");
			if (aMenu.getLinkPane() != null
					|| aMenu.getLinkService() != null) {
					buffer.append("activeNavLi(this),");
					this.appendLink(buffer, aMenu, aMenu.getEchoJs());
			} 
			
			if (aMenu.retrieveChildren() != null && !aMenu.retrieveChildren().isEmpty()) {
				buffer.append("\" class='dropdown-toggle' ");
			}else{
				buffer.append("\"  ");
			}

			buffer.append("><i  class='")
			.append(this.getIcon(aMenu))
			.append("'></i><span  class='menu-text'>")
					.append(aMenu.getL10n()).append("</span>");

			if (aMenu.retrieveChildren() != null  && !aMenu.retrieveChildren().isEmpty()) {
				buffer.append(" <b class='arrow icon-angle-down'></b> ");
			}

			buffer.append("</a>\n");

			// //submenu
			if (aMenu.retrieveChildren() != null && !aMenu.retrieveChildren().isEmpty()) {
				buffer.append("<ul class='submenu'>");

				for (Iterator itChild = aMenu.retrieveChildren().iterator(); itChild
						.hasNext();) {
					DOMenuModel aChildMenu = (DOMenuModel) itChild.next();
					buffer.append("	<li><a href='#' onclick=\"");
					if (aChildMenu.getLinkPane() != null
							|| aChildMenu.getLinkService() != null) {
						buffer.append("javascript:activeNavLi(this,true),");
						this.appendLink(buffer, aChildMenu,
								aChildMenu.getEchoJs());
					}
					buffer.append("\">");

					buffer.append("<i  class='icon-list'></i>").append(
							aChildMenu.getL10n());
					buffer.append("</a></li>\n");
				}
				buffer.append("</ul>");
			}

			buffer.append("</li>");
		}
		buffer.append("</ul>");

		buffer.append("         <div class='sidebar-collapse' id='sidebar-collapse'>\n");
		buffer.append("           <i class='icon-double-angle-left' data-icon1='icon-double-angle-left' data-icon2='icon-double-angle-right'></i>\n");
		buffer.append("         </div>\n ");
		

	    buffer.append("   <script type='text/javascript'> \n ");
		buffer.append(" try{ace.settings.check('sidebar' , 'collapsed')}catch(e){} \n");
	    buffer.append(" </script> \n");
		buffer.append("      </div>\n");

		return buffer.toString();
	}

	private String getIcon(DOMenuModel aMenu) {
		// TODO Auto-generated method stub
		if(aMenu.getIcon()!=null && aMenu.getIcon().startsWith("icon-")){
			return aMenu.getIcon();
		}
		try {
			return  icons.get(Integer.valueOf( String.valueOf(Math.abs(aMenu.getName().hashCode())).substring(0,1) ));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "icon-list";
	}
	
    
	
	public static void main(String[] args){
		
		String a = icons.get(Integer.valueOf( String.valueOf("ccccc".hashCode()).substring(0,1) ));
		
		System.out.println("aaaaaaaaaaaaaa::" + a);
		
	}
}