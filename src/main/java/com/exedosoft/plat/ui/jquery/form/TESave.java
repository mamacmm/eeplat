package com.exedosoft.plat.ui.jquery.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOFormTarget;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;

/**
 * 
 * @author aa
 * 
 */
public class TESave extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TESave.class);
	
	public TESave(){
		dealTemplatePath("/form/TESave.ftl");
	}
	
	
	public Map<String, Object> putData(DOIModel doimodel) {
		
		Map<String, Object>  data = super.putData(doimodel);
		
		DOFormModel property = (DOFormModel) doimodel;

		List<DOFormTarget> list =  property.getTargetGridModels();
		
		List<String> htmlGridIDs = new ArrayList<String>();
		
		for(DOFormTarget ft : list){
			htmlGridIDs.add(ft.getTargetGridModelUid());
		}
		data.put("htmlGridIDs", htmlGridIDs);
		
		return data;
	}



}
