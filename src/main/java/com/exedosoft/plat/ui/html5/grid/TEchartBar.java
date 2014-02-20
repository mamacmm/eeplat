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
public class TEchartBar extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TEchartBar.class);

	public TEchartBar() {

		dealTemplatePath("/grid/chart/TEchartBar.ftl");
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
			StringBuffer values = new StringBuffer("[");
			
			StringBuffer names = new StringBuffer("[");
			
			for(Iterator<BOInstance> it = list.iterator(); it.hasNext();){
				BOInstance anBI = it.next();
				
				values.append("'").append(anBI.getValue(valueCol)).append("'");
				names.append("'").append(anBI.getValue(keyCol)).append("'");
				
				if(it.hasNext()){
					values.append(",");
					names.append(",");
				}
			}
			values.append("]");
			names.append("]");
			data.put("values", values.toString());
			data.put("names", names.toString());
			
		}
		
		
		if (gm.getContainerPane() != null) {
			data.put("pmlName", gm.getContainerPane().getName());
		}
		return data;
	}

}
