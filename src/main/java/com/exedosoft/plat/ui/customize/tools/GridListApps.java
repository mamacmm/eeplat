package com.exedosoft.plat.ui.customize.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.jquery.grid.GridList;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.RestUtil;

/**
 * @author aa
 */
public class GridListApps extends GridList {

	private static Log log = LogFactory.getLog(GridListApps.class);

	public GridListApps() {
		
		dealTemplatePath("/customize/tools/GridListApps.ftl");
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {

		DOGridModel gm = (DOGridModel) doimodel;

		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", gm);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		data.put("webmodule", DOGlobals.URL);
		
		
		BOInstance shareUser = (BOInstance)DOGlobals.getInstance().getSessoinContext().getGlobal().getObjectValue("shareUser");
		
		if(shareUser==null){
			data.put("data", new ArrayList());
			data.put("needlogin",true);
			return data;
		}else{
			data.put("needlogin",false);
		}
		

	

		
		String callSelect =  "callType=sa&contextServiceName=eeplat_appshareinstall_findbyuserid&use_user_id=" +  shareUser.getUid() ;
		List<BOInstance> installedData = getList(callSelect);
		
		/**
		 * Local :: http://127.0.0.1:8080/eeplatshare/servicecontroller
		 */
		callSelect =  "callType=sa&contextServiceName=eeplat_appshare_list_ws";
		List<BOInstance> listData = getList(callSelect);
		
		for(BOInstance aData :listData){
			for(BOInstance install:installedData){
				if(install.getValue("appshare_id").equals(aData.getValue("id"))){
					System.out.println("Installed Data::" + aData);
					aData.putValue("install", "true");
				}
			}
		}
		data.put("data", listData);
		
		
		//	select * from multi_appshareinstall where 	appshare_id  = ? and use_tenant_id = ?

		
//		var ret = true;
//		var tv = DOGlobals.getInstance().getSessoinContext().getTenancyValues();
//		if(tv!=null){
//		if(doinstance.getValue("auth_tenant_id").equals(tv.getTenant().getUid())){
//		  ret = false;
//		}else {
//		   var findExists =   DOService.getService("multi_appshareinstall_findifinstalled");
//		   var paras = new HashMap();
//		paras.put("appshare_id",doinstance.getUid());
//		paras.put("use_tenant_id",tv.getTenant().getUid());
//		 var list = findExists.invokeSelect(paras);
//		 if(list!=null && list.size() > 0){
//		      ret = false;
//		  }
//		 }
//		}
//		ret;
//		
		
		
		return data;
	}

	public List<BOInstance> getList(String callSelect) {
		JSONArray jsonArray = null;
		List<BOInstance> listData = new ArrayList<BOInstance>();
		try {
			StringBuffer buffer = RestUtil.call(callSelect);
		//System.out.println("BUFFER::::" + buffer);
			jsonArray = new JSONArray(buffer.toString());
			System.out.println("jsonArray::::" + jsonArray);
			if(jsonArray!=null){
				
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject jo = jsonArray.getJSONObject(i);
					BOInstance bi = BOInstance.fromJSONObject(jo);
					listData.add(bi);				
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listData;
	}

	
	
}
