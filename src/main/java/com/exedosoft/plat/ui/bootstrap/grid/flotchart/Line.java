package com.exedosoft.plat.ui.bootstrap.grid.flotchart;

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
public class Line extends DOViewTemplate {

	private static Log log = LogFactory.getLog(Line.class);

	public Line() {

		dealTemplatePath("/grid/chart/Line.ftl");
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
			StringBuffer buffer = new StringBuffer("[");
			
			int i  = 1;
		    String bakValue = "10";
			for(Iterator<BOInstance> it = list.iterator(); it.hasNext();){
				BOInstance anBI = it.next();
				buffer.append("['")
				.append(i * 10)
				.append("', '")
				.append(anBI.getValue(valueCol))
				.append("']");

				
				bakValue = anBI.getValue(valueCol);

				if(it.hasNext()){
					buffer.append(",");
				}
				i ++; 
			}
			buffer.append("]");
			data.put("jsonData", buffer.toString());
		}
		
		
		if (gm.getContainerPane() != null) {
			data.put("pmlName", gm.getContainerPane().getName());
		}
		return data;
	}


}
