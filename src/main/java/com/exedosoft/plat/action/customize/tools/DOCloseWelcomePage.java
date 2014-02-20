package com.exedosoft.plat.action.customize.tools;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOApplication;

/**
 * 
 * make simple login
 * 
 */

public class DOCloseWelcomePage extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String excute() {

		DOApplication.notUseWelcomePage();

		return DEFAULT_FORWARD;
	}

}
