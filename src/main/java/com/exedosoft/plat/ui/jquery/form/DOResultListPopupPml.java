package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOBOProperty;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.Escape;
import com.exedosoft.plat.util.StringUtil;

public class DOResultListPopupPml extends DOBaseForm {

	public DOResultListPopupPml() {
		super();
	}

	public String getHtmlCode(DOIModel iModel) {

		if (isUsingTemplate) {
			return super.getHtmlCode(iModel);
		}

		DOFormModel property = (DOFormModel) iModel;

		return getPopupForm(property);
	}

	protected int max_pagesize = 50;

	protected boolean default_data = false;

	/**
	 * 获取动态列表形式的Select Form
	 * 
	 * @param property
	 *            TODO
	 * @param db
	 * @return
	 */
	String getPopupForm(DOFormModel fm) {

		/**
		 * 可变动态下拉列表， 根据连接的FORMMODEL，一般静态staticlist 确定使用的服务
		 */
		boolean isDyn = false;

		if (fm.getLinkForms() != null && !fm.getLinkForms().isEmpty()
				&& fm.getInputConfig() != null) {
			isDyn = true;
		}

		if (fm.getLinkService() == null && !isDyn) {
			return "&nbsp;";
		}

		StringBuffer buffer = new StringBuffer();

		String theValue = fm.getValue();

		BOInstance data = null;

		if (fm.getL10n().equals("连接内容")) {

			System.out.println("isDyn:::::::::::" + isDyn);
			System.out.println("连接内容:::::::::::" + fm.getLinkForms());
			System.out.println("fm.getInputConfig():::::::::::"
					+ fm.getInputConfig());

		}

		if (theValue != null && !"".equals(theValue.trim())) {

			DOBO corrBO = fm.getLinkBO();

			if (corrBO == null && fm.getLinkService() != null) {
				corrBO = fm.getLinkService().getBo();
			}

			/**
			 * 可变动态下拉列表， 根据连接的FORMMODEL，一般静态staticlist 确定使用的服务
			 */
			if (isDyn) {
				DOFormModel linkFm = (DOFormModel) fm.getLinkForms().get(0);
				String theLinkValue = fm.getData()
						.getValue(linkFm.getColName());

				if (theLinkValue != null) {

					List list = StringUtil.getStaticList(fm.getInputConfig());

					for (Iterator it = list.iterator(); it.hasNext();) {
						String[] halfs = (String[]) it.next();

						if ((theLinkValue != null && theLinkValue
								.equals(halfs[0]))) {
							DOService theCorrService = DOService
									.getService(halfs[1]);
							if (theCorrService != null) {
								corrBO = theCorrService.getBo();
							}
							break;
						}
					}
				}
				data = DOValueResultList.getAInstance(null, corrBO, theValue);
			} else {
				data = DOValueResultList.getAInstance(fm, corrBO, theValue);
			}

		}

		// if (default_data && data == null && fm.getLinkService() != null) {
		// data = fm.getLinkService().getBo().getCorrInstance();
		// if (data != null) {
		// theValue = data.getUid();
		// }
		// }

		buffer.append("	<input type='hidden' class='resultlistpopup'  name='")
				.append(fm.getColName()).append("' id='")
				.append(fm.getFullColID()).append("' serviceName='")

				.append(fm.getLinkService().getName()).append("' ");

		buffer.append(" title='").append(fm.getL10n().trim()).append("'");

		if (theValue != null) {

			buffer.append(" value='").append(theValue).append("'");
		}

		appendHtmlJs(buffer, fm, false);

		buffer.append("/>");

		buffer.append(
				"<input class='resultlistpopupshow' type='text' style='border:#B3B3B3 1px solid;margin-top:1px'   ")
				.append(" onkeydown=\"keyEvent(this, event)\"")
				.append(" onblur=\"myValidate(this)\"")
				.append(" onclick=\"this.style.borderColor='#406B9B'\" onmouseover=\"this.style.borderColor='#99E300'\" onmouseout=\"this.style.borderColor='#A1BCA3'\" name='")
				.append(fm.getFullColID()).append("_show'  ");

		buffer.append(this.appendValidateConfig(fm));

		
		
		if (fm.getOnChangeJs() != null && !"".equals(fm.getOnChangeJs().trim())) {
			buffer.append(" changejs='")
					.append(Escape.unescape(fm.getOnChangeJs())).append("' ");
		} else {
			if ("autosetvalues".equalsIgnoreCase(fm.getInputConfig())) {

				if (fm.getLinkService().getBo() != null) {
				
					StringBuffer setForms = new StringBuffer("");
					List<DOBOProperty> listProperty = fm.getLinkService()
							.getBo().retrieveProperties();
					List<DOFormModel> listForm = fm.getGridModel()
							.getAllGridFormLinks();
					for (DOBOProperty property : listProperty) {
						for (DOFormModel aForm : listForm) {
							if(property.getColName().equals(aForm.getColName())){
								setForms.append(aForm.getColName())
									.append(",");
							}
						}
					}
					int  subsize = setForms.length();
					if(setForms.indexOf(",")!=-1){
						subsize = subsize - 1;
					}
					String formNames = setForms.substring(0, subsize);
					buffer.append(" changejs=autoPutFormValues('")
					.append( fm.getLinkService().getBo()
							.getDSeleByIdService().getName()).append("','")
					.append(formNames)
					.append("','")
					.append(fm.getLinkService().getBo().getName())
					.append("')");
					
				}
			}
		}

		buffer.append(getDecoration(fm));

		if (data != null) {
			buffer.append(" value='").append(data.getName()).append("'");
		}


		if (data != null) {
			buffer.append(" title='").append(data.getName()).append("'");
		} else {
			buffer.append(" title='").append(fm.getL10n()).append("'");
		}

		if (isReadOnly(fm)) {
			buffer.append(" readonly='readonly' ");

		}

		buffer.append(" size='").append(getInputSize(fm)).append("' ");

		/**
		 * 可变动态下拉列表， 根据连接的FORMMODEL，一般静态staticlist 确定使用的服务
		 */
		if (isDyn) {
			DOFormModel linkFm = (DOFormModel) fm.getLinkForms().get(0);
			buffer.append("linkformid='").append(linkFm.getFullColID())
					.append("' inputconfig='").append(fm.getInputConfig())
					.append("' ");
		}

		buffer.append("/>");

		// 若有连接面板，则可弹出面板
		if (fm.getLinkPaneModel() != null) {
			// 下拉列表
			buffer.append("<IMG  class='popupimg1' onclick=\"invokePopup(this")
					.append(",'");
			if ("allform".equals( fm.getInputConstraint() )) {
				buffer.append(fm.getTargetForms());
			}
			else if ("onlyvalue".equals( fm.getInputConstraint() )) {
				buffer.append("onlyvalue");
			}
			buffer.append("','");
			buffer.append(fm.getLinkService().getBo().getValueCol())
					.append("',1,").append(max_pagesize);

			if (fm.getInputConstraint() != null) {
				buffer.append(",'").append(fm.getInputConstraint()).append("'");
			}
			buffer.append(")\"  src='").append(DOGlobals.PRE_FULL_FOLDER)
					.append("images/darraw.gif' align=absMiddle ");
			buffer.append("/>");
			// 连接面板
			buffer.append("<IMG  class='popupimg2' onclick=\"");
			getInvokePmlJs(fm, buffer);
			buffer.append("\"  src='").append(DOGlobals.PRE_FULL_FOLDER)
					.append("images/darraw2.gif' align=absMiddle ");

			buffer.append("/>");

		} else {
			buffer.append("<IMG   class='popupimg' onclick=\"invokePopup(this")
					.append(",'");
			if ("allform".equals( fm.getInputConstraint() )) {
				buffer.append(fm.getTargetForms());
			}
			else if ("onlyvalue".equals( fm.getInputConstraint() )) {
				buffer.append("onlyvalue");
			}
			buffer.append("','");
			buffer.append(fm.getLinkService().getBo().getValueCol())
					.append("',1,").append(max_pagesize);

			///清除界面上的相关定义的UI的值。
			if (fm.getInputConstraint() != null) {
				buffer.append(",'").append(fm.getInputConstraint()).append("'");
			}
			buffer.append(")\"  src='").append(DOGlobals.PRE_FULL_FOLDER)
					.append("images/darraw.gif' align=absMiddle ");
			buffer.append("/>");

		}

		if (fm.getNote() != null && !"".equals(fm.getNote())) {
			buffer.append(fm.getNote());
		}

		if (fm.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}

		return buffer.toString();

	}

	private void getInvokePmlJs(DOFormModel fm, StringBuffer buffer) {

		if (fm.getLinkPaneModel() != null) {
			buffer.append("invokePopupPml(this,'").append(fm.getFullColID());
			buffer.append("','").append(fm.getLinkPaneModel().getName())
					.append("'");
			if (fm.getLinkPaneModel().getPaneWidth() != null) {
				buffer.append(",'")
						.append(fm.getLinkPaneModel().getPaneWidth())
						.append("'");
			} else {
				buffer.append(",''");
			}
			if (fm.getLinkPaneModel().getPaneHeight() != null) {
				buffer.append(",'")
						.append(fm.getLinkPaneModel().getPaneHeight())
						.append("'");
			} else {
				buffer.append(",''");
			}

		}
		if (fm.getLinkPaneModel().getTitle() != null) {
			buffer.append(",'").append(fm.getLinkPaneModel().getTitle())
					.append("'");
		} else {
			buffer.append(",''");
		}
		if (fm.getGridModel() != null) {
			buffer.append(",'a").append(fm.getGridModel().getObjUid())
					.append("'");
		} else {
			buffer.append(",''");
		}
		if (fm.getTargetPaneModel() != null) {
			buffer.append(",'").append(fm.getTargetPaneModel().getName())
					.append("'");
		} else {
			buffer.append(",''");
		}
		buffer.append(")");

	}

}
