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
public class TESaveMulti extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TESaveMulti.class);
	
	public TESaveMulti(){
		dealTemplatePath("/form/TESaveMulti.ftl");
	}
	
	
	public Map<String, Object> putData(DOIModel doimodel) {
		
		Map<String, Object>  data = super.putData(doimodel);
		
		DOFormModel property = (DOFormModel) doimodel;

		List<DOFormTarget> list =  property.getTargetGridModels();
		
		List<String> childIds = new ArrayList<String>();
		
		String mainId = property.getGridModel().getObjUid();
		for(DOFormTarget ft : list){
			childIds.add(ft.getTargetGridModelUid());
			DOGridModel gm = DOGridModel.getGridModelByID(ft.getTargetGridModelUid());
			if(gm.isMainGrid()){
				mainId = gm.getObjUid();
			}
		}
		if(childIds.contains(mainId)){
			childIds.remove(mainId);
		}
		data.put("childIds", childIds);
		data.put("mainId", mainId);

		return data;
	}
	
	public static void main(String[] args){
		
		System.out.println("10个涨停板::" + 1.1*1.1*1.1*1.1*1.1*1.1*1.1*1.1*1.1*1.1);
	}



}
