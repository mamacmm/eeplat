package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOEmptyLabel extends DOBaseForm {
	
	public DOEmptyLabel(){
		super();
	}

	public String getHtmlCode(DOIModel aModel) {
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}

		DOFormModel fm = (DOFormModel) aModel;
	
		if(fm.getNameColspan()==null){
			fm.setNameColspan(0);
			fm.setValueColspan(2);
		}

		return "&nbsp;";
	}

}
