package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.StringUtil;

public class DOValueHtmlXss extends DOBaseForm  implements DOIView {
	
	public DOValueHtmlXss(){
		super();
	}


	public String getHtmlCode(DOIModel aModel) {
		
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}

		DOFormModel fm = (DOFormModel) aModel;
		if (fm.getValue() != null && !fm.getValue().trim().equals("")) {
			StringBuffer sb=new StringBuffer();
			String inputConfig = fm.getInputConfig();
			String width = "100%";
			String height = "600px";
			if (inputConfig != null && !inputConfig.equals("")) {
				String[] configs = inputConfig.split(";");
				if (configs != null && configs.length == 2) {
					if (Integer.parseInt(configs[0]) != 0) {
						width =configs[0];
					}
					if (Integer.parseInt(configs[1]) != 0) {
						height =configs[1];
					}
				}
			}
			sb.append("<div style=").append("\"WIDTH:")
			.append(width);
			sb.append("; HEIGHT: ").append(height)
			.append("; BACKGROUND-COLOR: #FFFFFF; overflow-y:auto; SCROLLBAR-FACE-COLOR: #c2d3fc; SCROLLBAR-HIGHLIGHT-COLOR: #c2d3fc; SCROLLBAR-SHADOW-COLOR: BLACK; SCROLLBAR-3DLIGHT-COLOR: #c2d3fc; SCROLLBAR-ARROW-COLOR:#000000; SCROLLBAR-TRACK-COLOR: FFFFFF; SCROLLBAR-DARKSHADOW-COLOR: EAECEC\"").append(">").append(StringUtil.unFilterXss(fm.getValue())).append("</div>");
			return sb.toString();
		} else {
			return "&nbsp;";
		}
	}

}
