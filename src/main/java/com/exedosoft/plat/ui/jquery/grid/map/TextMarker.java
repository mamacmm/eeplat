package com.exedosoft.plat.ui.jquery.grid.map;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;

/**
 * @author aa
 */
public class TextMarker extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TextMarker.class);

	public TextMarker() {

		dealTemplatePath("/grid/map/TextMarker.ftl");
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		DOGridModel gm = (DOGridModel) doimodel;
		if (gm.getService() == null) {
			return null;
		}
		Map<String, Object> data = super.putData(doimodel);

		if (gm.getService() != null) {
			List<BOInstance> list = gm.getService().invokeSelect();
			JSONArray jsa = new JSONArray();
			if(list!=null){
				for(BOInstance aData : list){
					jsa.put( aData.toJSONObject() );
				}
			}
			try {
				data.put("jsondata",URLEncoder.encode(jsa.toString(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}
}
