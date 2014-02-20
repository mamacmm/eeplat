package com.exedosoft.plat.ui.customize.tools;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.ui.jquery.form.DOBaseForm;
import com.exedosoft.plat.util.DOGlobals;

public class DOAppShareLink extends DOBaseForm  implements DOIView {
	
	
	public DOAppShareLink(){
		super();
	}


	public String getHtmlCode(DOIModel aModel) {
		


		DOFormModel property = (DOFormModel) aModel;
		StringBuffer buffer = new StringBuffer();
		// //////////对picture类型的特殊处理


			buffer.append("<a class='exedo_link' target='_opener' href='").append(DOGlobals.getValue("eeplatshare"));
			
			if(property.getInputConfig()!=null){
				buffer.append(property.getInputConfig());
			}
			
			buffer.append(
					"'>").append(property.getL10n()).append("</a>");


		return buffer.toString();

	}

	

}
