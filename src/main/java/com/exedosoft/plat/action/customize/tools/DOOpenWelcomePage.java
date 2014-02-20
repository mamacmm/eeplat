package com.exedosoft.plat.action.customize.tools;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOApplication;

/**
 * 
 * make simple login
 * 
 */

public class DOOpenWelcomePage extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String excute() {

		DOApplication.useWelcomePage();

		return DEFAULT_FORWARD;
	}

}
