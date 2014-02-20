package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOValueScript extends DOBaseForm {
	
	public DOValueScript(){
		super();
	}

	@Override
	public String getHtmlCode(DOIModel aModel) {
		
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}

		
		DOFormModel fm = (DOFormModel) aModel;
		
		String value = fm.getCalScriptValue();
		if (value != null && !value.trim().equals("")) {

			return value;
		} else {
			return "&nbsp;";
		}
	}

}
