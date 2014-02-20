package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.org.OrgParter;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;

public class DOValueSimplePartLogin extends DOBaseForm  implements DOIView {
	
	
	
	public DOValueSimplePartLogin(){
		super();
	}
	

	public String getHtmlCode(DOIModel aModel) {
		
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}

		DOFormModel property = (DOFormModel) aModel;
		String theValue = property.getValue();
		
		
		OrgParter userParter = OrgParter.getDefaultEmployee();
		
		if(userParter==null){
			return  "&nbsp;";
		}

		DOBO userBO = userParter.getDoBO();

			
		BOInstance 	bi = DOValueResultList.getAInstance(property,userBO, theValue);


		if (bi != null) {
			return bi.getName();
		}
		
		if (theValue == null || "null".equals(theValue)) {
			return "&nbsp;";
		}
		return theValue;
}
}
