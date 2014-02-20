package com.exedosoft.plat.action.customize.tools;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

/**
 * 
 * 
 * 做增加，不做修改和删除
 * 
 * 可以对增加做扫描
 * 
 * @author anolesoft
 * 
 */

public class DOLogoChangeData extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String excute() {

		BOInstance retI = new BOInstance();
		///login logo
		DOService findLoginService = DOService.getService("do_resource_find_logo_login");
		BOInstance loginInstance = findLoginService.getInstance();
		if(loginInstance!=null && loginInstance.getValue("resourcepath")!=null){
		    retI.putValue("loginimage",loginInstance.getValue("resourcepath"));
		}
		///main logo
		DOService findMainService = DOService.getService("do_resource_find_logo_main");
		BOInstance mainInstance = findMainService.getInstance();
		if(mainInstance!=null && mainInstance.getValue("resourcepath")!=null){
		    retI.putValue("resourcepath",mainInstance.getValue("resourcepath"));
		}

		DOGlobals.getInstance().getRuleContext().setInstance(retI);
	
		// TODO Auto-generated method stub
		return DEFAULT_FORWARD;
	}


}

//var retI = new BOInstance();
/////login logo
//var findLoginService = DOService.getService('do_resource_find_logo_login');
//var loginInstance = findLoginService.getInstance();
//log.info("loginInstance::" + loginInstance);
//if(loginInstance!=null && loginInstance.getValue("resourcepath")!=null){
//  retI.putValue("loginimage",loginInstance.getValue("resourcepath"));
//}
/////main logo
//var findMainService = DOService.getService('do_resource_find_logo_main');
//var mainInstance = findMainService.getInstance();
//log.info("mainInstance::" + mainInstance);
//if(mainInstance!=null && mainInstance.getValue("resourcepath")!=null){
//  retI.putValue("resourcepath",mainInstance.getValue("resourcepath"));
//}
//
//DOGlobals.getInstance().getRuleContext().setInstance(retI);