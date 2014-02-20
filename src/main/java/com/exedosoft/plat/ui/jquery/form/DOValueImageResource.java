package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOResource;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

public class DOValueImageResource extends DOBaseForm implements DOIView {

	public DOValueImageResource() {
		super();
	}

	public String getHtmlCode(DOIModel aModel) {

		if (isUsingTemplate) {
			return super.getHtmlCode(aModel);
		}

		DOFormModel formModel = (DOFormModel) aModel;

		String areaConfig = formModel.getInputConfig();

		int width = 0;
		int height = 0;
		if (areaConfig != null && !areaConfig.equals("")) {
			String[] configs = areaConfig.split(";");
			if (configs != null && configs.length == 2) {
				if (Integer.parseInt(configs[0]) != 0) {
					width = Integer.parseInt(configs[0]);
				}
				if (Integer.parseInt(configs[1]) != 0) {
					height = Integer.parseInt(configs[1]);
				}
			}
		}

		String value = formModel.getValue();

		if (formModel.getLinkService() != null) {
			BOInstance anInstance = formModel.getLinkService().getInstance();
			if (anInstance != null) {
				if (DOResource.getResourceByID(anInstance.getUid()) != null) {
					if (DOResource.getResourceByID(anInstance.getUid())
							.getCloudPath() != null) {
						value = DOResource.getResourceByID(anInstance.getUid())
								.getCloudPath();
					}
				}
			}

		}

		if (value != null) {

			StringBuffer buffer = new StringBuffer();
			buffer.append("<a  class='exedo_link' href='");
			buffer.append(value).append("'>");
			// //////////对picture类型的特殊处理

			buffer.append("<img  id='img").append(formModel.getFullColID())
					.append("' src='").append(value);

			buffer.append("' border='0'");
			if (width != 0) {
				buffer.append(" width='").append(width).append("' ");
			}
			if (height != 0) {
				buffer.append(" height='").append(height).append("' ");
			}
			buffer.append(">");
			buffer.append("</a>");
			return buffer.toString();
		}
		return "&nbsp;";

	}

	/**
	 * 判断是否为图片
	 */

	public static boolean isPic(String fileName) {

		boolean isPic = false;
		fileName = fileName.toLowerCase();
		isPic = fileName.endsWith(".jpg") || fileName.endsWith(".bmp")
				|| fileName.endsWith(".gif") || fileName.endsWith(".png")
				|| fileName.endsWith(".tif") || fileName.endsWith(".pcx");

		return isPic;
	}

}
