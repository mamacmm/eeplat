package com.exedosoft.plat.ui.html5.grid;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;

/**
 * @author aa
 */
public class TEchartPie extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TEchartPie.class);

	public TEchartPie() {

		dealTemplatePath("/grid/chart/TEchartPie.ftl");
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		DOGridModel gm = (DOGridModel) doimodel;
		if (gm.getService() == null) {
			return null;
		}
		Map<String, Object> data = super.putData(doimodel);
		
		if(gm.getService()!=null){
			String note = gm.getService().getNote();
			String keyCol = "name";
			String valueCol = "value";
			if (note != null && note.indexOf(",") != -1) {
				String[] cols = note.split(",");
				keyCol = cols[0];
				valueCol = cols[1];
			}

			List<BOInstance> list = gm.getService().invokeSelect();
			StringBuffer nameValues = new StringBuffer("[");
			
			StringBuffer names = new StringBuffer("[");
			
			for(Iterator<BOInstance> it = list.iterator(); it.hasNext();){
				BOInstance anBI = it.next();
				nameValues.append("{ name:'")
				.append(anBI.getValue(keyCol))
				.append("',  value: '")
				.append(anBI.getValue(valueCol))
				.append("'}");
				
				names.append("'").append(anBI.getValue(keyCol)).append("'");
				
				if(it.hasNext()){
					nameValues.append(",");
					names.append(",");
				}
			}
			nameValues.append("]");
			names.append("]");
			data.put("nameValues", nameValues.toString());
			data.put("names", names.toString());
			
		}
		
		
		if (gm.getContainerPane() != null) {
			data.put("pmlName", gm.getContainerPane().getName());
		}
		return data;
	}


}
