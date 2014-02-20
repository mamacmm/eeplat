package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;

import java.math.BigDecimal;

public class DOValueSimpleNumberScale extends DOBaseForm  implements DOIView {
	
	
	public DOValueSimpleNumberScale(){
		super();
	}
	

	public String getHtmlCode(DOIModel aModel) {
		
		
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}

		DOFormModel fm = (DOFormModel) aModel;
		String value = fm.getValue();

		if (value != null && !value.trim().equals("")) {

			try {
				
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
					String mm = new BigDecimal(value).setScale(scale,
							BigDecimal.ROUND_HALF_UP).toString();

					if (fm.getStyle() != null && !"".equals(fm.getStyle())) {
						// return "<span style='"+fm.getStyle()+"'>"+value+"<";;
						StringBuffer sb = new StringBuffer();
						sb.append("<span style='").append(fm.getStyle())
								.append("'>").append(mm).append("</span>");
						return sb.toString();
					}
				

				return value;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return value;
			}
		} else {
			return "&nbsp;";
		}
	}

	public static void main(String[] args) {

		String bd = "55.678";

		String mm = new BigDecimal(bd).setScale(2, BigDecimal.ROUND_HALF_UP)
				.toString();

		System.out.println("MM::" + mm);
	}

}
