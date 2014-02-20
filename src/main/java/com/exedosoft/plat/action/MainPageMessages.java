package com.exedosoft.plat.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;

public class MainPageMessages {

	public static Map getMessages() {
		
		
		DOService service = DOService.getService("do_wfi_nodeinstance_db");
		List<BOInstance>list = new ArrayList<BOInstance>();
		list = service.invokeSelect();
		Map<String,String> map = new HashMap<String,String>();
		if(list != null && list.size() > 0) {
			String len = list.size()+"";
			map.put("pending", len);
		} else {
			map.put("pending", "0");
		}
	return map;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
