package com.exedosoft.plat.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.util.I18n;


public class DOMultipleToFormBatch extends DOAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4798265805984034747L;
	private static Log log = LogFactory.getLog(DOMultipleToFormBatch.class);

	public DOMultipleToFormBatch() {
	}

	public String excute()  {

		if (this.service==null || this.service.getTempSql() == null) {
			setEchoValue(I18n.instance().get("未配置SQL 语句"));
		}

		
		List<String> multiple= SessionContext.getInstance().getMultiple(this.service.getBo());
		
		if(multiple==null || multiple.size()==0){
			setEchoValue(I18n.instance().get("没有数据"));
		}
		
		Transaction t =  this.service.getBo().getDataBase().getTransaction();
		
		t.begin();
		
		try {
			for(String one : multiple){
				Map aMap = new HashMap();
				aMap.put("oneofmultiple", one);
				this.service.invokeUpdate(aMap);			
			}
		} catch (Exception e) {
			t.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.end();
		
		return DEFAULT_FORWARD;
	}

}
