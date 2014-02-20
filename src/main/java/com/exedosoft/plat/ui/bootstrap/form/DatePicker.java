package com.exedosoft.plat.ui.bootstrap.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.jquery.form.DOBaseForm;
import com.exedosoft.plat.util.DOGlobals;

/**
  * ACE 自带的控件 
 * @author IBM
 * 
 */
public class DatePicker extends DOBaseForm {

	@Override
	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;

		StringBuffer buffer = new StringBuffer();

		buffer.append("<div class='row-fluid input-append date'> <input type='text' class='date-picker'").append(" id='")
				.append(property.getFullColID()).append("' name='").append(
						property.getFullColName()).append("'");

		buffer.append(this.appendValidateConfig(property));

		buffer.append(getDecoration(property));

		buffer.append(" title='").append(property.getL10n()).append("'");
		
		if (property.getInputConstraint() != null) {
			buffer.append(property.getInputConstraint());
		} else {
			if ("en".equals(DOGlobals.getValue("lang.local"))) {
				buffer.append(" data-date-format='dd/mm/yyyy' ");
				
			}else{
				buffer.append(" data-date-format='yyyy-mm-dd' ");
			}
		}

		if (property.getValue() != null) {
			String dataValue = property.getValue();
//			if (!isValidDate(dataValue)) {
//				if (dataValue.indexOf(" ") != -1) {
//					dataValue = dataValue.split(" ")[0];
//				}
//			}
			buffer.append(" value='").append(dataValue).append("'");
		}
		


		buffer.append("/>");
		
		buffer.append(" <span class='add-on'><i class='icon-calendar'></i></span></div>");

		if (property.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}
		
		buffer.append("<script> 	$('#")
		.append(property.getFullColID())
		.append("').datepicker(); </script>");

		return buffer.toString();
	}



	public static void main(String[] args) {

	
	}

}
