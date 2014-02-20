package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.StringUtil;

public class DOTextAreaHelpValues extends DOTextArea {

	public DOTextAreaHelpValues() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getHtmlCode(DOIModel aModel) {
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}

		DOFormModel property = (DOFormModel) aModel;

		StringBuffer buffer = getTextAreaStr(aModel);

		appendHelps(property, buffer);
		return buffer.toString();
	}

	protected static void appendHelps(DOFormModel property, StringBuffer buffer) {
		if (property.getInputConstraint() != null) {

			buffer.append("<br/><span style=\"color:grey\" >Common values:</spans>");
			String[] helps = property.getInputConstraint().split("\\|");
			for (int i = 0; i < helps.length; i++) {
				String aHelp = helps[i];
				buffer.append("<a href='#' onclick=\"$('#")
						.append(property.getFullColID()).append("').val('")
						.append(aHelp).append("')\"  style=\"color:grey\" > ").append(aHelp)
						.append("</a>&nbsp; &nbsp;");
			}

		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
