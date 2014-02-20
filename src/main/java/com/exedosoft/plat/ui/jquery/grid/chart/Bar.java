package com.exedosoft.plat.ui.jquery.grid.chart;

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
public class Bar extends DOViewTemplate {

	private static Log log = LogFactory.getLog(Bar.class);

	public Bar() {

		dealTemplatePath("/grid/chart/Bar.ftl");
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		DOGridModel gm = (DOGridModel) doimodel;
		if (gm.getService() == null) {
			return null;
		}
		Map<String, Object> data = super.putData(doimodel);

		if (gm.getService() != null) {
			List<BOInstance> list = gm.getService().invokeSelect();

			StringBuffer str 
			= new StringBuffer("<graph caption='")
			.append(gm.getCaption())//Open Positions by Functional Area
			.append("' xAxisName='")
			.append(gm.getBottomTitle())//Open Positions
			.append("' yAxisName='")
			.append(gm.getHeadTitle()) //Functional Area
			.append("' yAxisMaxValue='5'  formatNumber='0' formatNumberScale='0' >");
			for(BOInstance anIns:list){
//			    System.out.println("anIns::" + anIns);
			    str.append("<set name='")
			    .append( anIns.getValue("functionalArea") )
			    .append( "' value='" )
			    .append( anIns.getValue("areanum"))
			    .append("' color='D64646' />");
			}
			str.append( "</graph>" );
			data.put("gXml",str);
		}

		if (gm.getContainerPane() != null) {
			data.put("pmlName", gm.getContainerPane().getName());
		}
		return data;
	}
}
