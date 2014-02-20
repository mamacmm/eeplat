package com.exedosoft.plat.ui.jquery.form;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOParameter;
import com.exedosoft.plat.bo.DOParameterCheck;
import com.exedosoft.plat.template.HtmlTemplateGenerator;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.Escape;

/**
 * 
 * Form的类型有： c_form_button c_form_date c_form_input c_form_list c_form_other
 * c_form_pane c_form_service c_form_suite c_form_value
 * 
 * 针对Mobile优化，特殊的 控制器名称： form.DOInputRadio form.DOInputCheckBoxList
 */
public abstract class DOBaseForm extends DOViewTemplate implements DOIView {

	protected boolean isUsingTemplate = false;

	public DOBaseForm() {
		super();
		judgeUsingTemplate();
	}

	public void judgeUsingTemplate() {
		
		
		String prefix = DOGlobals.getValue("jslib.template.path");
		if (prefix == null) {
			prefix = "";
		}
		File aFile = new File(HtmlTemplateGenerator.getTemplatePath()  +  this.templateFile);
			
		if (aFile.exists()) {
			isUsingTemplate = true;
		}

	}

	protected boolean isReadOnly(DOFormModel property) {

		return property.isFormModelReadOnly();
	}

	protected int getInputSize(DOFormModel property) {

		return getInputSize(property, 25);
	}

	protected int getInputSize(DOFormModel property, int defaultSize) {

		try {
			if (property.getInputConfig() != null
					&& !"".equals(property.getInputConfig())) {
				if (Integer.parseInt(property.getInputConfig()) != 0) {
					defaultSize = Integer.parseInt(property.getInputConfig());
				}
			}
		} catch (NumberFormatException ex) {
			return defaultSize;

		}

		return defaultSize;

	}

	protected String getDecoration(DOFormModel property) {

		// List decorations = DOUIDecoration.getEvent4FormModel(property);

		StringBuffer buffer = new StringBuffer();
		
		if(property.getCssClass()!=null){
			buffer.append(" class='")
			.append(property.getCssClass())
			.append("' ");
		}
		
		// for (Iterator it = decorations.iterator(); it.hasNext();) {
		//
		// DOUIDecoration dec = (DOUIDecoration) it.next();
		// buffer.append(" ").append(dec.getDecoPoint());
		// }

		// if (property.getOnBlurJs() == null ||
		// "".equals(property.getOnBlurJs().trim())) {
		// String validconfig = property.getExedojoType();
		// if (validconfig != null && !"".equals(validconfig.trim())) {
		// if (validconfig.indexOf(";") != -1) {
		// String typeAndLength[] = validconfig.split(";");
		// if (typeAndLength[0] != null) {// //Email,Integer,RealNumber
		// validconfig = typeAndLength[0];
		// }
		// }
		// if (validconfig != null && !"".equals(validconfig.trim())) {
		// buffer.append(" onblur=\"validateCheck('")
		// .append(validconfig)
		// .append("',this)\"");
		// }
		// }
		// } else {
		// buffer.append(" onblur='").append(property.getOnBlurJs()).append(
		// "' ");
		// }
		return buffer.toString();

	}

	protected static void appendHtmlJs(StringBuffer buffer, DOFormModel fm) {
		appendHtmlJs(buffer, fm, true);
	}

	protected static void appendHtmlJs(StringBuffer buffer, DOFormModel fm,
			boolean isChange) {
		// /////////插入用户自定义的onclick事件
		if (fm.getDoClickJs() != null && !"".equals(fm.getDoClickJs().trim())) {
			buffer.append("  onclick='").append(fm.getDoClickJs()).append("'");
		}
		// /////////插入用户自定义的validJs事件
		if (fm.getOnBlurJs() != null && !"".equals(fm.getOnBlurJs().trim())) {
			buffer.append(" onblur='").append(fm.getOnBlurJs()).append("' ");
		}

		if (isChange) {
			if (fm.getOnChangeJs() != null
					&& !"".equals(fm.getOnChangeJs().trim())) {
				// oninput
				// buffer.append(" onDOMAttrModified='").append(fm.getOnChangeJs())
				// .append("' ");
				// buffer.append(" onpropertychange='").append(fm.getOnChangeJs())
				// .append("' ");
				buffer.append(" onchange='")
						.append(Escape.unescape(fm.getOnChangeJs()))
						.append("' oninput='")
						.append(Escape.unescape(fm.getOnChangeJs()))
						.append("' onpropertychange='")
						.append(Escape.unescape(fm.getOnChangeJs()))
						.append("'");
			}
		}

		if (fm.getEscapeOnFocusJs() != null
				&& !"".equals(Escape.unescape(fm.getEscapeOnFocusJs().trim()))) {
			buffer.append(" onfocus='")
					.append(Escape.unescape(fm.getEscapeOnFocusJs()))
					.append("'");
		}

		if (fm.getEscapeOnBlurJs() != null
				&& !"".equals(Escape.unescape(fm.getEscapeOnBlurJs().trim()))) {
			buffer.append(" onblur='")
					.append(Escape.unescape(fm.getEscapeOnBlurJs()))
					.append("'");
		}

		if (fm.getOnFocusJs() != null && !"".equals(fm.getOnFocusJs().trim())) {
			buffer.append(" onfocus='").append(fm.getOnFocusJs()).append("' ");
		}

	}

	protected static void appendDOAjaxJs(StringBuffer buffer, DOFormModel fm) {
		// /////////插入用户自定义的onclick事件
		if (fm.getDoClickJs() != null && !"".equals(fm.getDoClickJs().trim())) {
			buffer.append(",doAjax.doClickJs='")
					.append(fm.getEscapeDOClickJs()).append("'");
		}
		// /////////插入用户自定义的validJs事件
		if (fm.getValidJs() != null && !"".equals(fm.getValidJs().trim())) {
			buffer.append(",doAjax.validJs='").append(fm.getValidJs())
					.append("'");
		}

	}

	/**
	 * 获得验证类型
	 * 
	 * 20deae72
	 * 
	 * @param fm
	 * @return
	 */
	private String getValidType(DOFormModel fm) {

		if (fm.getExedojoType() != null
				&& !"".equals(fm.getExedojoType().trim())) {
			if (fm.getExedojoType().indexOf(";") != -1) {
				String typeAndLength[] = fm.getExedojoType().split(";");
				if (typeAndLength[0] != null
						&& !typeAndLength[0].trim().equals("")) {// //Email,Integer,RealNumber
					return typeAndLength[0];

				}
			} else {
				return fm.getExedojoType();
			}
		}
		return "";
	}

	private String getValidExtension(DOFormModel fm) {

		/**
		 * 为了兼容老版本，使用uitype 作为校验的扩展
		 */
		if (fm.getUiType() != null) {
			return fm.getUiType().replaceAll(":", "=");
		}

		/***
		 * 兼容老版本的最大值
		 */
		// if (fm.getExedojoType() != null
		// && !"".equals(fm.getExedojoType().trim())) {
		// if (fm.getExedojoType().indexOf(";") != -1) {
		//
		// String typeAndLength[] = fm.getExedojoType().split(";");
		// if (typeAndLength.length > 1) {
		// return "max:" + typeAndLength[1];
		// }
		// }
		// }
		return "";
	}

//	protected String appendValidateConfig(DOFormModel fm, boolean isSelect) {
//
//		StringBuffer buffer = new StringBuffer();
//
//		// buffer.append(" msglabel='").append(fm.getL10n()).append("' ");
//
//		// if (fm.isNotNull()) {
//		// buffer.append(" exedo_notnull='NotNull'");
//		//
//		// }
//
//
//		if (fm.isNotNull()) {
//			buffer.append("required:true");
//		} 
//
//		if (!isSelect) {
//			String validType = getValidType(fm);
//			if (validType != null && !validType.equals("")) {
//
//				if(buffer.length() > 0){
//					buffer.append(",");
//				}
//				
//				buffer.append(validType).append(":true");
//			}
//		}
//
//		// //remote check 后台检测
//		DOParameter checkPara = fm.getCheckParameter();
//		if (checkPara != null) {
//			if(buffer.length() > 0){
//				buffer.append(",");
//			}
//
//			buffer.append("remote:'web/default/checkParameter.jsp?parauid=")
//					.append(checkPara.getObjUid()).append("',");
//
//			List checks = checkPara.getChecks();
//			StringBuffer errorMsg = new StringBuffer();
//			if (checks != null && checks.size() > 0) {
//				for (Iterator it = checks.iterator(); it.hasNext();) {
//					DOParameterCheck dopCheck = (DOParameterCheck) it.next();
//					errorMsg.append(dopCheck.getEchoMessage());
//					if (it.hasNext()) {
//						errorMsg.append(";");
//					}
//				}
//			}
//			buffer.append("messages:{remote:'").append(errorMsg).append("'}");
//		}
//
//		// /扩展验证
//		String validExtension = getValidExtension(fm);
//		if (validExtension != null && !validExtension.equals("")) {
//			if(buffer.length() > 0){
//				buffer.append(",");
//			}
//			buffer.append(getValidExtension(fm));
//		}
//		
//		
//		
//		if(buffer.length() > 0){
//			buffer.insert(0, "class=\"{");
//			buffer.append("} \" ");
//		}
//		
//	//	if(fm.get)
//
//		/**
//		 * * 客户端验证配置，分为３部分，以;隔开 １，类型：Integer RealNumber email Text Others 2, 长度
//		 * ３, 其他 equalTo:'#field'
//		 * 
//		 */
//
//		// if (fm.getExedojoType() != null
//		// && !"".equals(fm.getExedojoType().trim())) {
//		// if (fm.getExedojoType().indexOf(";") != -1) {
//		//
//		// String typeAndLength[] = fm.getExedojoType().split(";");
//		// if (typeAndLength[0] != null && !typeAndLength[0].equals("")) {//
//		// //Email,Integer,RealNumber
//		// buffer.append(" exedo_validconfig='")
//		// .append(typeAndLength[0]).append("'");
//		// }
//		//
//		// if (typeAndLength.length > 1 && typeAndLength[1] != null) {
//		// buffer.append(" exedo_length='").append(typeAndLength[1])
//		// .append("'");
//		//
//		// buffer.append(" maxlength='").append(typeAndLength[1])
//		// .append("'");
//		// }
//		//
//		// if (typeAndLength.length > 2) {
//		// buffer.append(" exedo_script=\"").append(typeAndLength[2])
//		// .append("\"");
//		// }
//		// } else {
//		// if (fm.getExedojoType() != null) {
//		// buffer.append(" exedo_validconfig='")
//		// .append(fm.getExedojoType()).append("'");
//		// }
//		// buffer.append(" exedo_length='").append("").append("'");
//		// }
//		// }
//		return buffer.toString();
//
//	}
//
//	
//	
	
	
	protected String appendValidateConfig(DOFormModel fm, boolean isSelect) {

		StringBuffer buffer = new StringBuffer();
		
		if(fm.getStyle()!=null){
			
			buffer.append("style='")
			.append(fm.getStyle())
			.append("' ");
		}
		
		
		if (fm.isNotNull()) {
			buffer.append("required=true");
		} 
		if (!isSelect) {
			String validType = getValidType(fm);
			if (validType != null && !validType.equals("")) {
				
				buffer.append(" type='").append(validType).append("'");
			}else{
				buffer.append(" type='text' ");
			}
		}
		
		// //remote check 后台检测
		DOParameter checkPara = fm.getCheckParameter();
		if (checkPara != null) {
			if(buffer.length() > 0){
				buffer.append(",");
			}

			buffer.append(" remote='web/default/checkParameter.jsp?parauid=")
					.append(checkPara.getObjUid()).append("'  ");

			List checks = checkPara.getChecks();
			StringBuffer errorMsg = new StringBuffer();
			if (checks != null && checks.size() > 0) {
				for (Iterator it = checks.iterator(); it.hasNext();) {
					DOParameterCheck dopCheck = (DOParameterCheck) it.next();
					errorMsg.append(dopCheck.getEchoMessage());
					if (it.hasNext()) {
						errorMsg.append(";");
					}
				}
			}
			buffer.append(" message='").append(errorMsg).append("'");
		}
		// /扩展验证
		String validExtension = getValidExtension(fm);
		if (validExtension != null && !validExtension.equals("")) {
			if(buffer.length() > 0){
				buffer.append("  ");
			}
			buffer.append(getValidExtension(fm));
		}
		return buffer.toString();

	}

	protected String appendValidateConfig(DOFormModel fm) {
		String str = appendValidateConfig(fm, false);
		return str;
	}

	public String getAjaxLink(BOInstance bi, String defaultPaneID,
			String doClickJs) {
		return this.getAjaxLink(null, defaultPaneID, doClickJs);
	}

	public String getAjaxLink(BOInstance bi, DOPaneModel paneModel,
			String defaultOpenPaneID, String doClickJs) {

		if (bi.getBo() == null) {
			return bi.getName();
		}

		// /权限下一步要加上的
		// || !this.bo.getMainPaneModel().isAccess()
		if (bi.getBo().getMainPaneModel() == null) {
			return bi.getName();
		}

		if (paneModel == null) {
			paneModel = bi.getBo().getMainPaneModel();
		}

		if (paneModel == null) {
			return "";
		}

		// javascript:doAjax.actionName=

		String dealBus = "&dataBus=setContext&contextKey="
				+ bi.getBo().getName() + "&contextValue=" + bi.getUid();

		StringBuffer buffer = new StringBuffer(
				"<a href=\"javascript:popupDialog('");
		buffer.append(paneModel.getName()).append("','")
				.append(paneModel.getTitle()).append("','")
				.append(paneModel.getFullCorrHref(bi, null)).append(dealBus);

		if (paneModel.getPaneWidth() != null) {
			buffer.append("','").append(paneModel.getPaneWidth());
		}

		if (paneModel.getPaneHeight() != null) {
			buffer.append("','").append(paneModel.getPaneHeight());
		}

		buffer.append("')\"> ")

		.append(bi.getName()).append("</a>");
		return buffer.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// DOFormModel aFm =
		// DOFormModel.getFormModelByID("4028802328945c580128945c5ef30005");
		// System.out.println("FM:::" + aFm.getController().getHtmlCode(aFm));

//		DOGridModel gm = DOGridModel
//				.getGridModelByName("GM_DO_UI_FormModel_Update");
//		System.out.println("GM:::" + gm.getHtmlCode());
		
		StringBuffer buffer = new StringBuffer("122335");
		buffer.insert(0, "class=\"{");
		buffer.append("}");
		
		System.out.println("bbbbbbbbbb::" + buffer);

	}

}
