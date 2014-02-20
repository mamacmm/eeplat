package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

public class DOValueDateTime extends DOBaseForm implements DOIView {
	
	
	public DOValueDateTime(){
		super();
	}

	public String getHtmlCode(DOIModel aModel) {
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}
		DOFormModel fm = (DOFormModel) aModel;

		// ////////////////精确到秒 yyyy-MM-dd HH:mm:ss
		if (fm.getInputConfig() != null) {
			return StringUtil.getDateStr(
					fm.getData().getDateValue(fm.getRelationProperty()),
					fm.getInputConfig());
		} else {
			if (fm.getValue() != null && fm.getRelationProperty() != null) {
				if ("en".equals(DOGlobals.getValue("lang.local"))) {
					return StringUtil
							.getDateStr(
									fm.getData().getDateValue(
											fm.getRelationProperty()),
									"MM/dd/yyyy HH:mm");
				} else {
					return StringUtil
							.getDateStr(
									fm.getData().getDateValue(
											fm.getRelationProperty()),
									"yyyy-MM-dd HH:mm");
				}
			} else {
				return "&nbsp;";
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
