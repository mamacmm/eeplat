package com.exedosoft.plat.ui.customize.ace;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;

/**
 * @author aa
 */
public class JS extends DOViewTemplate {

	private static Log log = LogFactory.getLog(JS.class);


	public JS() {
		dealTemplatePath(  "/customize/ace/js.ftl" );
	}
	
	public Map<String, Object> putData(DOIModel doimodel) {

		Map<String, Object> data =super.putData(doimodel);
	
		
		String  browseAgent = "notie";
		try {
			browseAgent = DOGlobals.getInstance().getServletContext().getRequest().getHeader("user-agent");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		   //判断user-agent的信息
		   if((browseAgent!=null)&& (browseAgent.indexOf("MSIE"))!=-1) {
			   data.put("browseAgent", "ie");
		   }else{
			   data.put("browseAgent", "notie");
		   }
		
		return data;
	}

	public static void main(String[] args) {

		int i = (int) Math.round( 0.51);
		System.out.println("i:::::::::" + i);
	}

}
