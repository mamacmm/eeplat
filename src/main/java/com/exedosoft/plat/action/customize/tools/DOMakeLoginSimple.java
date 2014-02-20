package com.exedosoft.plat.action.customize.tools;

import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.bo.org.OrgParter;
import com.exedosoft.plat.login.LoginMain;
import com.exedosoft.plat.util.DOGlobals;

/**
 * 
 * make simple login
 * 
 */

public class DOMakeLoginSimple extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String excute() {

		if (this.actionForm != null && this.actionForm.getValue("name") != null) {
			SessionContext us = new SessionContext();

			DOGlobals.getInstance().getServletContext().getRequest()
					.getSession().setAttribute("userInfo", us);

			LoginMain.makeLogin(this.actionForm, DOGlobals.getInstance()
					.getServletContext().getRequest());
		}

		return DEFAULT_FORWARD;
	}

}
