package com.exedosoft.plat.template;

import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOFormModel;

import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class Data2Json implements TemplateMethodModelEx {

	public Object exec(List paras) throws TemplateModelException {
		StringModel p1 = (StringModel) paras.get(0);
		BOInstance data = null;
		if (p1 != null) {
			data = (BOInstance) p1.getWrappedObject();
			return data.toJSONString();
		}else{
			return "{}";
		}
	}
}
