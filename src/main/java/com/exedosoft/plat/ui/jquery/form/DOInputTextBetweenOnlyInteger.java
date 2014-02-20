package com.exedosoft.plat.ui.jquery.form;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

public class DOInputTextBetweenOnlyInteger extends DOBaseForm {
	
	public DOInputTextBetweenOnlyInteger(){
		super();
	}


	public String getHtmlCode(DOIModel aModel) {
		
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}


		DOFormModel fm = (DOFormModel) aModel;

		StringBuffer buffer = new StringBuffer();

		if("en".equals(DOGlobals.getValue("lang.local"))){
			buffer.append("&nbsp; to &nbsp;");
		}else{
			buffer.append("&nbsp; 至 &nbsp;");
		}

		return buffer.toString();
	}

	/**
	 * @param fm
	 * @param buffer
	 */
	private void getAInputTimeStr(DOFormModel fm, StringBuffer buffer,
			String aNext) {

		buffer.append("<input type='text' name='").append(fm.getFullColName()).append(aNext)
				.append("' id='").append(fm.getFullColName()).append(aNext).append("'");

		buffer.append(getDecoration(fm));


		buffer.append(" title='").append(fm.getL10n().trim()).append("'");

		

		String theValue = fm.getValue();

		DOPaneModel cPaneModel = null;
		if (fm.getGridModel() != null) {
			cPaneModel = fm.getGridModel().getContainerPane();
		}


		if (theValue != null) {

			buffer.append(" value='").append(theValue).append("'");
		}
		if (isReadOnly(fm)) {
			buffer.append(" readonly='readonly' ");

		}

		buffer.append(this.appendValidateConfig(fm));

		buffer.append(" size=\"").append(getInputSize(fm)).append("\"");
		buffer.append("onkeypress=\"var kk=event.keyCode; return kk>=48&&kk<=57\" onpaste=\"return !clipboardData.getData('text').match(/\\D/)\" ondragenter=\"return false\"");
		buffer.append("/>");

		if (fm.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}
		if (fm.getNote() != null && !"".equals(fm.getNote())) {
			buffer.append(fm.getNote());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
