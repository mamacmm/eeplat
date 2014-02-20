package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOLabel extends DOBaseForm {
	
	public DOLabel(){
		super();
	}



	public String getHtmlCode(DOIModel aModel) {
		
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}


		DOFormModel fm = (DOFormModel) aModel;

		String note = fm.getL10n();

		if (fm.getNote() != null) {
			note = fm.getNote();
		}
		if (fm.getStyle() != null) {
			StringBuffer buffer = new StringBuffer();

			buffer.append("<span style='").append(fm.getStyle()).append("'>");
			buffer.append(note);
			buffer.append("</span>");
			return buffer.toString();
		}

		return note;
	}

}
