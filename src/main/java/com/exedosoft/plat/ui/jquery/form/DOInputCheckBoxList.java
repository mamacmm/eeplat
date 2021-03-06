package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.StringUtil;

public class DOInputCheckBoxList extends DOStaticList {

	public DOInputCheckBoxList() {
		super();
	}

	public String getHtmlCode(DOIModel iModel) {
		
		
		if(isUsingTemplate){
			return super.getHtmlCode(iModel);
		}


		DOFormModel property = (DOFormModel) iModel;

		StringBuffer buffer = new StringBuffer();

		if (property.getLinkService() != null) {
			for (Iterator it = property.getLinkService().invokeSelect()
					.iterator(); it.hasNext();) {

				BOInstance instance = (BOInstance) it.next();

				buffer.append("<label class='checkbox inline'>");
				buffer.append("<input name=\"").append(
						property.getFullColName());

				buffer.append("\" value=\"").append(instance.getUid());

				buffer.append("\"  type=\"checkbox\"");

				buffer.append(getDecoration(property));
				
				buffer.append(this.appendValidateConfig(property));

				if (DOStaticList.isChecked(instance.getUid(), property
						.getValue())) {
					buffer.append(" checked ");
				}
				 if (isReadOnly(property)) {
					 buffer.append(" DISABLED  ");
				 }
				buffer.append("/>");
				buffer.append(instance.getName())
								.append("&nbsp;&nbsp");
				buffer.append("</label>");
				//buffer.append(instance.getName());

			}
		} else if(property.getInputConfig()!=null){

			List list = StringUtil.getStaticList(property.getInputConfig());
			for (Iterator it = list.iterator(); it.hasNext();) {
				String[] half = (String[]) it.next();
				buffer.append("<label class='checkbox inline'>");
				buffer.append("<input name=\"").append(
						property.getFullColName());

				buffer.append("\" value=\"").append(half[0]);

				buffer.append("\"  type=\"checkbox\"");
				buffer.append(getDecoration(property));
				
				buffer.append(this.appendValidateConfig(property));


				if (DOStaticList.isChecked(half[0], property.getValue())) {
					buffer.append(" checked ");
				}
				 if (isReadOnly(property)) {
				 buffer.append(" disabled ");
				 }
				buffer.append("/>");
				buffer.append(half[1])
				.append("&nbsp;&nbsp");
				buffer.append("</label>");
			}
		}
		
		if (property.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}
		
		if (property.getNote() != null && !"".equals(property.getNote())) {
			buffer.append(property.getNote());
		}
		
		return buffer.toString();

	}

}
