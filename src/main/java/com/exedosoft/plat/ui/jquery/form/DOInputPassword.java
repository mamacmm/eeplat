package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

public class DOInputPassword extends DOBaseForm {
	
	public DOInputPassword(){
		super();
	}

	public String getHtmlCode(DOIModel property) {
		
		if(isUsingTemplate){
			return super.getHtmlCode(property);
		}
//		 dojo.widget.byId('mianpeihaoma').isEmpty =
		// this.checked;dojo.widget.byId('mianpeihaoma').updateClass('Empty');

		DOFormModel fm = (DOFormModel) property;

		StringBuffer buffer = new StringBuffer();

		buffer.append("<input type='password' name='").append(fm.getFullColName())
				.append("' id='").append(fm.getFullColName()).append("'");

		// ////增加装饰
		buffer.append(getDecoration(fm));

		// ///////end 增加装饰
		buffer.append(" title='").append(fm.getL10n().trim()).append("'");

		// buffer.append(" dojoType='");
		// if (fm.getExedojoType() != null
		// && !"".equals(fm.getExedojoType().trim())) {
		// buffer.append(fm.getExedojoType()).append("'");
		// } else {
		// buffer.append("ValidationTextBox'");
		// }
		//		
		//		
		//	
		//
		// if (fm.getIsNull() != null
		// && !fm.getIsNull().booleanValue()) {
		// buffer.append(" required='true' ");
		// }
		if (fm.getValue() != null) {
			buffer.append(" value='").append(fm.getValue()).append("'");
		}
		if (isReadOnly(fm)) {
			buffer.append(" readonly='readonly' ");

		}
		
		buffer.append(this.appendValidateConfig(fm));
		
	
		

		buffer.append(" size=\"").append(getInputSize(fm)).append("\"/>");

		if (fm.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}
		if (fm.getNote() != null && !"".equals(fm.getNote())) {
			buffer.append(fm.getNote());
		}

		return buffer.toString();
	}

}
