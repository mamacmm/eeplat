package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOSectionLabel extends DOBaseForm {
	
	public DOSectionLabel(){
		super();
	}


	public String getHtmlCode(DOIModel aModel) {
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}

		DOFormModel fm = (DOFormModel) aModel;

		StringBuffer buffer = new StringBuffer();
		String note = fm.getNote();
		if (note == null) {
			note = fm.getL10n();
		}

		buffer.append("<span style='font-weight:bold'>");
		buffer.append(note);
		buffer.append("</span>");

		// ////////缺省 style  
		if (fm.getStyle() == null) {
			fm.setStyle("height:25px;background-color:#BDEBEE");
		}
	
		if(fm.getNameColspan()==null){
			fm.setNameColspan(0);
			fm.setValueColspan(2);
		}

		return buffer.toString();
	}

}
