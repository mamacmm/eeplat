package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;

public class DOValueSimplePlain extends DOBaseForm  implements DOIView {
	
	public DOValueSimplePlain(){
		super();
	}
	

	public String getHtmlCode(DOIModel aModel) {
		
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}

		DOFormModel fm = (DOFormModel) aModel;
		if (fm.getValue() != null) {
			
			return fm.getValue();
		} else {
			return "";
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
