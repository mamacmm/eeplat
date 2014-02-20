package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOInputSelect extends DOBaseForm {
	
	public DOInputSelect(){
		super();
	}

	public String getHtmlCode(DOIModel aModel) {
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}

		DOFormModel property = (DOFormModel) aModel;
		StringBuffer buffer = new StringBuffer();

		buffer.append("<input name=\"").append(property.getFullColName())
				.append("text\" type=\"text\"");
//		if (property.getValue() != null
//				&& !property.getValue().trim().equals("")) {
//			buffer.append(" value=\"").append(property.getValue()).append("\"");
		
		
//		}
		
		buffer.append(this.appendValidateConfig(property));
		

		buffer.append("/>");
		property.setOperationLabel("&nbsp;选择");
		buffer.append(DOValueService.stardardOnlyPane(property, null));

		buffer.append("<input name=\"").append(property.getFullColName())
				.append("\" type=\"hidden\"");
		if (property.getValue() != null
				&& !property.getValue().trim().equals("")) {
			buffer.append(" value=\"").append(property.getValue()).append("\"");
		}
		buffer.append("/>");
		
		if (property.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}
		
		if (property.getNote() != null && !"".equals(property.getNote())) {
			buffer.append(property.getNote());
		}

		return buffer.toString();
	}

}
