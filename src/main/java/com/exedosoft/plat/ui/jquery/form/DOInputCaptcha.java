package com.exedosoft.plat.ui.jquery.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.DOGlobals;


public class DOInputCaptcha extends DOBaseForm {
	
	
	public DOInputCaptcha(){
		
		dealTemplatePath("/form/DOInputCaptcha.ftl");		
	}
	
	

	public Map<String, Object> putData(DOIModel doimodel) {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", doimodel);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		if ("en".equals(DOGlobals.getValue("lang.local"))) {
			data.put("langlocal", "en");
		} else {
			data.put("langlocal", "ch");
		}
		DOFormModel fm = (DOFormModel) doimodel;
	

		data.put("imageurl", DOGlobals.PRE_FULL_FOLDER + "image.jsp");
	
		return data;
	}

//	@Override
//	public String getHtmlCode(DOIModel aModel) {
//	
//		if(isUsingTemplate){
//			return super.getHtmlCode(aModel);
//		}
//
//		DOFormModel fm = (DOFormModel) aModel;
//		StringBuffer buffer = getInputTextStr(fm);
//		
//
//		return buffer.toString();
//	}
//
//	 StringBuffer getInputTextStr(DOFormModel fm) {
//		StringBuffer buffer = new StringBuffer();
//
//		///onkeypress='if(event.keyCode==13||event.which==13){return false;}'  加上这个可以阻止上文所说的内容
//		
//		buffer
//				.append(
//						"<input  name='randcode' id='").append(
//						fm.getFullColID()).append("'");
//		buffer.append(" title='").append(fm.getL10n().trim()).append("'");
//		
//		fm.setIsNull(DOFormModel.NULL_NO);
//		
//
//		buffer.append(this.appendValidateConfig(fm));
//
//		buffer.append("\"    class='input' style='height:22px; width:80px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;' />");
//
//
//		buffer.append("&nbsp;<img src='/")
//		.append(DOGlobals.URL)
//		.append("/web/default/image.jsp'  height='25px' style='cursor:pointer' onclick=\"this.src='/")
//		.append(DOGlobals.URL)
//		.append("/web/default/image.jsp? + Math.random()'\"   border=0 />");
//		
//		
//		if (fm.isNotNull()) {
//			buffer.append("&nbsp;<font color='red'>*</font>");
//		}
//		return buffer;
//	}
	
	public static void main(String[] args){
		
		DOFormModel fm = DOFormModel.getFormModelByID("636f5ca21e864c18835150a787d8c1bc");
		System.out.println("Is not null:::" + fm.isNotNull());

	}

}
