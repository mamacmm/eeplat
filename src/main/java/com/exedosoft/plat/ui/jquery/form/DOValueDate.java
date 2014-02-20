package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

public class DOValueDate  extends DOBaseForm implements DOIView {
	
	
	public DOValueDate(){
		super();
	}

	public String getHtmlCode(DOIModel aModel) {
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}
		DOFormModel fm = (DOFormModel) aModel;
		if (fm.getValue() != null && fm.getRelationProperty() != null) {
			
			// ////////////////精确到秒 yyyy-MM-dd HH:mm:ss 
			if (fm.getInputConfig() != null) {
				return StringUtil
						.getDateStr(
								fm.getData().getDateValue(
										fm.getRelationProperty()),
								fm.getInputConfig());
			} else {

				if ("en".equals(DOGlobals.getValue("lang.local"))) {
					return StringUtil
							.getDateStr(
									fm.getData().getDateValue(
											fm.getRelationProperty()),
									"MM/dd/yyyy");
				} else {
					return StringUtil
							.getDateStr(
									fm.getData().getDateValue(
											fm.getRelationProperty()),
									"yyyy-MM-dd");
				}
			}

		} else {
			return "&nbsp;";
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
