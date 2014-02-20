package com.exedosoft.plat.action;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOFormTarget;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;

/**
 * 
 * 编辑数据增删改
 * 
 */

public class CoreSaveEditMultiAction extends DOAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7581994809740062108L;

	private static Log log = LogFactory.getLog(CoreSaveEditMultiAction.class);

	public CoreSaveEditMultiAction() {
	}

	/**
	 * Save 的情况，所以Parameter 取值时不考虑auto_condition（查询） 的情况
	 */

	public String excute() {

		log.info("Enter CoreSaveEditAction::::::::::::::::::::::::::");

		BOInstance uiForm = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance();
		String formUid = uiForm.getValue("formUid");
		System.out.println("FormID::" + formUid);

		JSONObject joAdd = null;
		JSONObject joModi = null;
		JSONObject joMMLink = null;
		JSONObject joMMLinkRemove = null;
		try {
			String addStr = uiForm.getValue("addStr");
			if (addStr != null) {
				joAdd = new JSONObject(addStr);
			}

			String modiStr = uiForm.getValue("modiStr");
			if (modiStr != null) {
				joModi = new JSONObject(modiStr);
			}

			String mmLinkStr = uiForm.getValue("mmLinkStr");
			if (mmLinkStr != null) {
				joMMLink = new JSONObject(mmLinkStr);
			}
			
			String mmRemoveLinkStr = uiForm.getValue("mmRemoveLinkStr");
			if (mmRemoveLinkStr != null) {
				joMMLinkRemove = new JSONObject(mmRemoveLinkStr);
			}		
			
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DOFormModel aFm = DOFormModel.getFormModelByID(formUid);

		if (aFm == null) {
			setEchoValue(I18n.instance().get("未配置FormModel"));
			return NO_FORWARD;
		}

		DOService mainService = aFm.getLinkService();

		Transaction t = mainService.getBo().getDataBase().getTransaction();
		t.begin();

		if (mainService == null) {// updateService
			setEchoValue(I18n.instance().get("必须定义两个服务，新增的服务和修改的服务!"));
			return NO_FORWARD;
		}

		try {
			mainService.invokeUpdate();
			List<DOFormModel> linkForms = aFm.getLinkForms();
			for (DOFormModel linkFM : linkForms) {
				List<DOFormTarget> list = linkFM.getTargetGridModels();
				if (list.size() == 1) {

					// /////////处理一个表格
					String dealGridModelUid = list.get(0)
							.getTargetGridModelUid();
//////////////////add
					if (joAdd != null) {
						JSONArray joGrid = joAdd.getJSONArray(dealGridModelUid);
						if (joGrid != null) {
							dealAddGrid(joGrid, linkFM);
						}
					}
///////////////////modify
					if (joModi != null) {
						JSONObject joGrid = joModi
								.getJSONObject(dealGridModelUid);
						if (joGrid != null) {
							dealModiGrid(joGrid, linkFM);
						}
					}
/////////////////link add					
					if (joMMLink != null) {
						JSONArray joGrid = joMMLink
								.getJSONArray(dealGridModelUid);
						if (joMMLink != null) {
							dealRelationGrid(joGrid, linkFM, uiForm, dealGridModelUid,false);
						}
					}
//////////////////link remove					
					if (joMMLinkRemove != null) {
						JSONArray joGrid = joMMLinkRemove
								.getJSONArray(dealGridModelUid);
						if (joMMLink != null) {
							dealRelationGrid(joGrid, linkFM, uiForm, dealGridModelUid,true);
						}
					}

				}
			}
		} catch (Exception ex1) {
			t.rollback();
			ex1.printStackTrace();
			this.setEchoValue(ex1.getMessage());
			return NO_FORWARD;
		} finally {

			try {
				t.end();
			} catch (Exception ex1) {
				ex1.printStackTrace();
				this.setEchoValue(ex1.getMessage());
				return NO_FORWARD;
			}
		}
		return DEFAULT_FORWARD;
	}

	// /////////////////////处理新增
	public void dealAddGrid(JSONArray joGrid, DOFormModel aFm)
			throws ExedoException, JSONException {

		DOService insertService = aFm.getLinkService();
		for (int itTr = 0; itTr < joGrid.length(); itTr++) {
			JSONArray joTr = joGrid.getJSONArray(itTr);
			BOInstance aParaBI = putJSONArrayToBI(joTr);
			System.out.println("ParaBO:::" + aParaBI);
			insertService.invokeAll(aParaBI);
		}
	}
	

	// /////////////////////处理修改
	public void dealModiGrid(JSONObject joGrid, DOFormModel aFm)
			throws ExedoException, JSONException {

		DOService updateService = aFm.getSecondService();

		for (Iterator itTr = joGrid.keys(); itTr.hasNext();) {
			String oneTrKey = (String) itTr.next();
			JSONArray joTr = joGrid.getJSONArray(oneTrKey);
			BOInstance aParaBI = putJSONArrayToBI(joTr);
			System.out.println("ParaBI::" + aParaBI);
			aParaBI.setUid(oneTrKey);
			///当前业务对象
			updateService.getBo().refreshContext(oneTrKey);
			updateService.invokeAll(aParaBI);

		}

	}
	
	
	
	// / 处理多多关系的中间表新增
	public void dealRelationGrid(JSONArray joGrid, DOFormModel aFm,
			BOInstance uiForm,String dealGridModelUid,boolean isRemove) throws SQLException, ExedoException,
			JSONException {

		if(aFm.getLinkService()==null){
			return ;
		}
		
		DOGridModel dealGrid = DOGridModel.getGridModelByID(dealGridModelUid);
		DOBO dealBO = null;
		if(dealGrid!=null && dealGrid.getService()!=null){
			dealBO = dealGrid.getService().getBo();			
		}
		
		DOService dealService = aFm.getLinkService();
		if(isRemove && aFm.getSecondService()!=null){
			dealService = aFm.getSecondService(); 
		}
		
		for (int i = 0; i < joGrid.length(); i++) {
			String aSelectValue = joGrid.getString(i);
			System.out.println("Select Value:::" + aSelectValue);
			uiForm.putValue("childinstance", aSelectValue);
			if(dealBO!=null){
				dealBO.refreshContext(aSelectValue);
			}
			dealService.invokeAll(uiForm);
		}

		return;
	}

	
	

	public BOInstance putJSONArrayToBI(JSONArray joTr) throws JSONException {
		BOInstance aParaBI = new BOInstance();
		for (int i = 0; i < joTr.length(); i++) {
			JSONObject keyValue = joTr.getJSONObject(i);
			String aKey = keyValue.getString("name");
			String aValue = keyValue.getString("value");
			aParaBI.putValue(aKey, aValue);
		}
		return aParaBI;
	}

}

// if (addStr != null) {
// try {
// joAdd = new JSONObject(addStr);
// System.out.println("joAdd::" + joAdd);
// for (Iterator itGrid = joAdd.keys(); itGrid.hasNext();) {
// // /oneGrid
// String oneGrid = (String) itGrid.next();
// System.out.println("oneGrid:::" + oneGrid);
// JSONArray joGrid = joAdd.getJSONArray(oneGrid);
// for (int itTr = 0; itTr < joGrid.length(); itTr++) {
// JSONArray joTr = joGrid.getJSONArray(itTr);
// BOInstance aParaBI = putJSONArrayToBI(joTr);
// System.out.println("ParaBIAdd::" + aParaBI);
// }
//
// }
// } catch (JSONException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
//
// String modiStr = uiForm.getValue("modiStr");
//
// if (modiStr != null) {
// try {
// joModi = new JSONObject(modiStr);
// System.out.println("joModi::" + joModi);
// for (Iterator itGrid = joModi.keys(); itGrid.hasNext();) {
// // /oneGrid
// String oneGrid = (String) itGrid.next();
// System.out.println("oneGrid:::" + oneGrid);
// JSONObject joGrid = joModi.getJSONObject(oneGrid);
// for (Iterator itTr = joGrid.keys(); itTr.hasNext();) {
// String oneTrKey = (String) itTr.next();
// System.out.println("oneTr:::" + oneTrKey);
// JSONArray joTr = joGrid.getJSONArray(oneTrKey);
//
// BOInstance aParaBI = putJSONArrayToBI(joTr);
// aParaBI.setUid(oneTrKey);
//
// System.out.println("ParaBI::" + aParaBI);
// }
//
// }
//
// } catch (JSONException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
//
// String mmLinkStr = uiForm.getValue("mmLinkStr");
//
// if (modiStr != null) {
// try {
// joMMLink = new JSONObject(mmLinkStr);
// System.out.println("joMMLink::" + joMMLink);
//
// for (Iterator itGrid = joMMLink.keys(); itGrid.hasNext();) {
// // /oneGrid
// String oneGrid = (String) itGrid.next();
// System.out.println("oneGrid:::" + oneGrid);
// JSONArray joGrid = joMMLink.getJSONArray(oneGrid);
// for (int i = 0; i < joGrid.length(); i++) {
// String aSelectValue = joGrid.getString(i);
// System.out.println("Select Value:::" + aSelectValue);
// }
// }
// } catch (JSONException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }

// public String dealEditGrid(BOInstance uiForm, DOFormModel aFm,
// Connection con) throws SQLException, ExedoException {
// /*
// * 必须定义两个服务，新增的服务和修改 的服务
// */
// DOService insertService = aFm.getLinkService();
// DOService updateService = aFm.getSecondService();
//
// int batchSize = 0;
//
// // /前置规则
// insertService.fireBeforRules();
// // ////////////////////////////////////新增
// String inputName = uiForm.getValue("inputName");
// if (inputName != null && !inputName.trim().equals("")) {
// String[] inputValues = uiForm.getValueArray(inputName);
// log.info("Current SQL:" + insertService.getTempSql());
// PreparedStatement pstmt = con.prepareStatement(insertService
// .getTempSql());
//
// List<String> newIds = new ArrayList<String>();
// for (int len = 0; len < inputValues.length; len++) {
// int i = 1;
// for (Iterator it = insertService.retrieveParaServiceLinks()
// .iterator(); it.hasNext();) {
// DOParameterService dops = (DOParameterService) it.next();
// DOParameter dop = dops.getDop();
//
// String value = null;
//
// if (dop.getType() != null
// && dop.getType().intValue() == DOParameter.TYPE_FORM
// && dop.getDefaultValue() == null) {// //form类型直接从节目获取
// log.info("Batch Parameter Name:::" + dop.getName());
// String[] valueArray = uiForm.getValueArray(dop
// .getName());
// value = valueArray[len];
// if ("".equals(value)) {
// value = null;
// }
// } else {
// value = dop.getValue();
// }
//
// if (dop.getType() != null
// && dop.getType().intValue() == DOParameter.TYPE_KEY) {
// newIds.add(value);
// }
// value = dops.getAfterPattermValue(value);
// insertService.putStatementAValue(pstmt, i, dops, value);
// i++;
// }
// batchSize++;
// pstmt.addBatch();
// }
// log.info("::batchSize:::" + batchSize);
// pstmt.executeBatch();
//
// // /后置规则
// insertService.fireAfterRules();
//
// // ///多租户的处理
// for (String anID : newIds) {
// insertService.dealMultiTenancy(null, con, anID);
// }
//
// }
// // ////////////////////////////////////end 新增=====================
// // ////////////修改
// // begin==============================================
//
// if (aFm.getGridModel().getService() == null) {
// return "";
// }
//
// DOBO refreshBO = aFm.getGridModel().getService().getBo();
//
// // //前置规则
// if (updateService != null) {
// updateService.fireBeforRules();
// String[] ids = uiForm.getValueArray("id");
// if (ids != null && ids.length > 0) {
//
// PreparedStatement pstmt = con.prepareStatement(updateService
// .getTempSql());
// for (int ii = 0; ii < ids.length; ii++) {
// String idstr = ids[ii];
// System.out.println("one isstr::" + idstr);
// if (idstr != null) {
// String[] vals = idstr.split(";﹕#");
// HashMap<String, String> paras = new HashMap<String, String>();
// String id = "";
// if (vals != null && vals.length > 0) {
// id = vals[0];
// for (int j = 1; j < vals.length; j++) {
// String aKeyValue = vals[j];
// System.out.println("aKeyValue::::::"
// + aKeyValue);
// String[] arrayKV = aKeyValue.split("﹕﹕");
// String aValue = null;
// if (arrayKV.length > 1) {
// aValue = arrayKV[1];
// }
// paras.put(arrayKV[0], aValue);
// }
// }
//
// System.out.println("paras:::::" + paras);
//
// BOInstance oldInstance = refreshBO.refreshContext(id);
// String newKeyValue = null;
// int i = 1;
// for (Iterator it = updateService
// .retrieveParaServiceLinks().iterator(); it
// .hasNext();) {
// DOParameterService dops = (DOParameterService) it
// .next();
// DOParameter dop = dops.getDop();
// String value = null;
// if (dop.getType() != null
// && dop.getType().intValue() == DOParameter.TYPE_FORM
// && dop.getDefaultValue() == null) {
// log.info("批量修改参数的名称:::" + dop.getName());
// value = paras.get(dop.getName());
// if ("".equals(value)) {
// value = null;
// }
// } else {
// value = dop.getValue();
// }
//
// if (dop.getType() != null
// && dop.getType().intValue() == DOParameter.TYPE_KEY) {
// newKeyValue = value;
// }
// value = dops.getAfterPattermValue(value);
// updateService.putStatementAValue(pstmt, i, dops,
// value);
// i++;
// }
// batchSize++;
// if (oldInstance != null) {
// CoreSaveAllAction.logOperation(updateService,
// oldInstance, newKeyValue);
// }
// pstmt.addBatch();
// }
// log.info("批量修改::batchSize:::" + batchSize);
// pstmt.executeBatch();
//
// }
// }
// // /后置规则
// updateService.fireAfterRules();
// }
