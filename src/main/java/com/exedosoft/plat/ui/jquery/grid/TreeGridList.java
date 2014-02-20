package com.exedosoft.plat.ui.jquery.grid;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOViewTemplate;

/**
 * 
 * @author aa
 * 
 */
public class TreeGridList extends DOViewTemplate {

	private static Log log = LogFactory.getLog(TreeGridList.class);
	
	public TreeGridList(){
		
		dealTemplatePath(  "/grid/TreeGridList.ftl");
	}
	
	
	public Map<String, Object> putData(DOIModel doimodel) {

		DOGridModel gm = (DOGridModel) doimodel;
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", gm);
		data.put("data", getListData(gm));

//		data.put("normalForms", gm.getNormalGridFormLinks());
//		data.put("topForms", gm.getTopOutGridFormLinks());//include hidden forms
//		data.put("bottomForms", gm.getBottomOutGridFormLinks());// include hidden forms 
		
		return data;
	}


	public static List getListData(DOGridModel gridModel) {
		List list = gridModel.getService().invokeSelect();
		Object[] arrays = list.toArray();
		DOService secondService = gridModel.getSecondService();
		int j=0;
		for(int i = 0 ; i < arrays.length ; i++){
			BOInstance bi = (BOInstance)arrays[i];
			int layer = 0;
			j = getListDataHelper(list, secondService, j, i, bi,layer);
		}
		return list;
	}


	private static int getListDataHelper(List list, DOService secondService,
			int j, int i, BOInstance bi,int layer) {
		layer ++;
		if(layer > 10){//最多循环10层
			return j;
		}
		
		if(secondService!=null){
			List children = secondService.invokeSelect(bi.getUid());
			if(children!=null && children.size()>0){
				for(Iterator it = children.iterator();it.hasNext();){
					j++;
					BOInstance aChild = (BOInstance)it.next();
//					System.out.println("i==j==data::" + i + "-----" + j + "----" + aChild);
					aChild.putValue("child_of_class", "child-of-"+bi.getUid());
					list.add(i+j, aChild);
					if(bi.getUid().equals(aChild.getUid())){///自己包含自己，预防死循环
						return j;
					}
					j = getListDataHelper(list,secondService,j,i,aChild,layer);
				}
			}
		}
		return j;
	}



}
