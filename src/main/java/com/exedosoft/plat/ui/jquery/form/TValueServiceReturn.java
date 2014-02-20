package com.exedosoft.plat.ui.jquery.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.CacheFactory;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

public class TValueServiceReturn extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TValueServiceReturn.class);

	public TValueServiceReturn() {
		
		dealTemplatePath("/form/TValueServiceReturn.ftl");
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
		BOInstance bi = fm.getData();
		DOService definedService = fm.getLinkService();
		if (definedService != null) {
			String aValue = definedService.getAValue(bi.getMap());
			if (aValue!=null && aValue.indexOf(".") != -1) {
				int scale = 2;
				try {
					String inputConfig = fm.getInputConfig();
					if (inputConfig != null) {
						scale = Integer.parseInt(inputConfig);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (aValue != null) {
					try {
						String mm = new BigDecimal(aValue).setScale(scale,
								BigDecimal.ROUND_HALF_UP).toString();
						data.put("theValue", mm);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						data.put("theValue", aValue);
						e.printStackTrace();
					}

				}
			}else{
				data.put("theValue", aValue);
			}
		}
		return data;
	}

	public static void main(String[] args) {
		CacheFactory.getCacheData().fromSerialObject();
		DOService aService = DOService
				.getService("recr_review_findcount_byjob");
		System.out.println("A Service::" + aService);
		BOInstance anInstance = aService
				.getInstance("4028803b3991e8ac013994427a08006c");
		// System.out.println("An Instance::" + anInstance);

	}

}
