package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOValueStaticList extends DOStaticList {
	
	
	
	public DOValueStaticList(){
		super();
	}


	public String getHtmlCode(DOIModel aModel) {
		
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}

		DOFormModel property = (DOFormModel) aModel;
		return getStaticListValue(property);
	}
}
