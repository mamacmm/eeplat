package com.exedosoft.plat.gene.jquery;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.CacheFactory;
import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.customize.tools.CopyServiceDeep;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.BaseObject;
import com.exedosoft.plat.bo.BusiPackage;
import com.exedosoft.plat.bo.DOActionConfig;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOBOProperty;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOParameter;
import com.exedosoft.plat.bo.DOParameterService;
import com.exedosoft.plat.bo.DORule;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.bo.DOServiceRule;
import com.exedosoft.plat.ui.DOController;
import com.exedosoft.plat.ui.DOFormLinks;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOFormTarget;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOMenuModel;
import com.exedosoft.plat.ui.DOPaneLinks;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.jquery.form.DOInputText;
import com.exedosoft.plat.ui.jquery.form.DOTextArea;
import com.exedosoft.plat.ui.jquery.form.DOValueDate;
import com.exedosoft.plat.ui.jquery.form.DOValueSimple;
import com.exedosoft.plat.ui.jquery.form.TClose;
import com.exedosoft.plat.ui.jquery.form.TEAdd;
import com.exedosoft.plat.ui.jquery.form.TEDelete;
import com.exedosoft.plat.ui.jquery.form.TESave;
import com.exedosoft.plat.ui.jquery.form.TESaveMulti;
import com.exedosoft.plat.ui.jquery.form.TPaneSelected;
import com.exedosoft.plat.ui.jquery.form.TServiceSelected;
import com.exedosoft.plat.ui.jquery.form.TPane;
import com.exedosoft.plat.ui.jquery.form.TService;
import com.exedosoft.plat.ui.jquery.form.TServiceSelectedUf;
import com.exedosoft.plat.ui.jquery.form.TServiceUf;
import com.exedosoft.plat.ui.jquery.form.my97.DatePicker;
import com.exedosoft.plat.ui.jquery.grid.GridConditionAutoTr;
import com.exedosoft.plat.ui.jquery.grid.GridList;
import com.exedosoft.plat.ui.jquery.grid.GridListEdit;
import com.exedosoft.plat.ui.jquery.grid.GridSupportMore;
import com.exedosoft.plat.ui.jquery.pane.ContentPane;
import com.exedosoft.plat.ui.jquery.pane.ContentPaneScroll;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;
import com.exedosoft.plat.util.id.UUIDHex;

public class GeneUIParentChild {

	private static Log log = LogFactory.getLog(GeneUIParentChild.class);

	private final String SQL_SELECT_SERVICE = "select service.* from do_service service,do_bo bo where service.bouid = bo.objuid  bo.name = ?";

	private DOController gridList = DOController
			.getControllerByName(GridList.class.getName());

	private DOController gridCondition = DOController
			.getControllerByName(GridConditionAutoTr.class.getName());

	private DOController gridSupportMore = DOController
			.getControllerByName(GridSupportMore.class.getName());

	private DOController gridListEdit = DOController
			.getControllerByClassName(GridListEdit.class.getName());

	private DOController formInputText = DOController
			.getControllerByName(DOInputText.class.getName());

	private DOController formTextArea = DOController
			.getControllerByName(DOTextArea.class.getName());

	private DOController formValueSimple = DOController
			.getControllerByName(DOValueSimple.class.getName());

	private DOController formValueDate = DOController
			.getControllerByName(DOValueDate.class.getName());

	private DOController formDateMy97 = DOController
			.getControllerByName(DatePicker.class.getName());

	private DOController contentPane = DOController
			.getControllerByName(ContentPane.class.getName());

	private DOController formServiceUf = DOController
			.getControllerByName(TServiceUf.class.getName());

	private DOController paneOverFlow = DOController
			.getControllerByName(ContentPaneScroll.class.getName());

	private DOController formCloseButton = DOController
			.getControllerByName(TClose.class.getName());

	// private DOController formDelete =
	// DOController.getControllerByName(TDelete.class.getName());

	private DOController formPane = DOController
			.getControllerByName(TPane.class.getName());

	private DOController formItemPane = DOController
			.getControllerByName(TPaneSelected.class.getName());

	private DOController formItemServiceUf = DOController
			.getControllerByName(TServiceSelectedUf.class.getName());

	private DOController formTESave = DOController
			.getControllerByClassName(TESaveMulti.class.getName());

	private DOController formTEDelete = DOController
			.getControllerByClassName(TEDelete.class.getName());

	private DOController formTEAdd = DOController
			.getControllerByClassName(TEAdd.class.getName());

	private String geneATable = "";
	private String childATable = "";
	private String linkCol = "";

	private DOBO parentCategory = null;
	private DOBO childCategory = null;

	public GeneUIParentChild(String parentTable, String childTable,
			String aLinkCol) {
		parentTable = StringUtil.get_Name(parentTable);
		this.geneATable = parentTable;
		this.childATable = childTable;
		this.linkCol = aLinkCol;
		parentCategory = DOBO.getDOBOByName(parentTable);
		childCategory = DOBO.getDOBOByName(childTable);
	}

	public void geneConfig() {

		/**
		 * 新增的相关主子表
		 */
		geneConfigInsert();

		geneConfigModify();

		geneConfigBrowse();

	}

	public void geneConfigInsert() {

		String insert = "新增数据";
		if ("en".equals(DOGlobals.getValue("lang.local"))) {
			insert = "New";
		}
		
		
		String childTitle = "子表数据";
		if ("en".equals(DOGlobals.getValue("lang.local"))) {
			childTitle = "Sub-table data";
		}
		

		// ///geneInsertParent
		try {
			/**
			 * 生成父面板以及表格
			 */
			DOGridModel parentGrid = genePaneAndGrid(parentCategory,
					gridSupportMore, geneATable + "_InsertParent", insert,
					null, null, false);
			/**
			 * 生成子面板以及表格，包含子面板多数据的新增、修改、删除
			 */
			DOGridModel childGrid = genePaneAndGrid(childCategory,
					gridListEdit, childATable + "_InsertChild", childTitle,
					parentGrid, null, false);

			/**
			 * 生成总面板
			 */
			DOPaneModel mainPane = new DOPaneModel();
			mainPane.setCategory(parentCategory);
			mainPane.setL10n("PM_" + geneATable + "_InsertMain");
			mainPane.setName("PM_" + geneATable + "_InsertMain");
			mainPane.setController(contentPane);
			DOService insertPane = DOService.getService("do_ui_panemodel_copy");
			DAOUtil.INSTANCE().store(mainPane, insertPane);

			DOService paneLinkService = DOService
					.getService("DO_UI_PaneLinks_copy");
			DOPaneLinks plinks = new DOPaneLinks();
			plinks.setParentPane(mainPane);
			plinks.setChildPane(parentGrid.getContainerPane());
			plinks.setOrderNum(5);
			DAOUtil.INSTANCE().store(plinks, paneLinkService);

			plinks = new DOPaneLinks();
			plinks.setParentPane(mainPane);
			plinks.setChildPane(childGrid.getContainerPane());
			plinks.setOrderNum(10);
			DAOUtil.INSTANCE().store(plinks, paneLinkService);

			DOGridModel mainGridResult = DOGridModel.getGridModelByName("GM_"
					+ geneATable + "_Result");
			List<DOFormModel> allGridLinks = mainGridResult
					.getAllGridFormLinks();

			for (DOFormModel aFM : allGridLinks) {

				// ////////修改主表的insert链接到主子表新增面板
				if (aFM.getLinkPaneModel() != null
						&& (aFM.getL10n().contains("新增") || aFM.getL10n()
								.toLowerCase().contains("new"))) {

					aFM.setLinkPaneModel(mainPane);
					DAOUtil.INSTANCE().store(aFM);
				}
			}

			/**
			 * 更改删除，加上删除子表的规则。不用重新做链接。
			 */

			DOService mainDelete = geneDeleteServiceAndRule();

		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 生成修改的相关配置
	 */

	public void geneConfigModify() {

		String modify = "修改数据";

		if ("en".equals(DOGlobals.getValue("lang.local"))) {
			modify = "Modify";
		}
		
		String childTitle = "子表数据";
		if ("en".equals(DOGlobals.getValue("lang.local"))) {
			childTitle = "Sub-table data";
		}
		
		// ///geneInsertParent
		try {
			/**
			 * 生成父面板以及表格
			 */

			DOService mainBrowseService = DOService.getService(geneATable
					+ "_browse");
			DOGridModel parentGrid = genePaneAndGrid(parentCategory,
					gridSupportMore, geneATable + "_ModifyParent", modify,
					null, mainBrowseService, false);

			/**
			 * 生成子面板以及表格，包含子面板多数据的新增、修改、删除
			 */
			DOService findByParentService = geneFindByParentService();
			DOGridModel childGrid = genePaneAndGrid(childCategory,
					gridListEdit, childATable + "_ModifyChild", childTitle,
					parentGrid, findByParentService, false);

			/**
			 * 生成总面板
			 */
			DOPaneModel mainPane = new DOPaneModel();
			mainPane.setCategory(parentCategory);
			mainPane.setL10n("PM_" + geneATable + "_ModifyMain");
			mainPane.setName("PM_" + geneATable + "_ModifyMain");
			mainPane.setController(contentPane);
			DOService insertPane = DOService.getService("do_ui_panemodel_copy");
			DAOUtil.INSTANCE().store(mainPane, insertPane);

			DOService paneLinkService = DOService
					.getService("DO_UI_PaneLinks_copy");
			DOPaneLinks plinks = new DOPaneLinks();
			plinks.setParentPane(mainPane);
			plinks.setChildPane(parentGrid.getContainerPane());
			plinks.setOrderNum(5);
			DAOUtil.INSTANCE().store(plinks, paneLinkService);

			plinks = new DOPaneLinks();
			plinks.setParentPane(mainPane);
			plinks.setChildPane(childGrid.getContainerPane());
			plinks.setOrderNum(10);
			DAOUtil.INSTANCE().store(plinks, paneLinkService);

			DOGridModel mainGridResult = DOGridModel.getGridModelByName("GM_"
					+ geneATable + "_Result");
			List<DOFormModel> allGridLinks = mainGridResult
					.getAllGridFormLinks();

			for (DOFormModel aFM : allGridLinks) {

				// ////////修改主表的insert链接到主子表新增面板
				if (aFM.getLinkPaneModel() != null
						&& (aFM.getL10n().contains("修改") || aFM.getL10n()
								.toLowerCase().contains("modi"))) {

					aFM.setLinkPaneModel(mainPane);
					DAOUtil.INSTANCE().store(aFM);
				}
			}

		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 生成修改的相关配置
	 */

	public void geneConfigBrowse() {

		String browse = "查看数据";

		if ("en".equals(DOGlobals.getValue("lang.local"))) {
			browse = "Browse";
		}
		String childTitle = "子表数据";
		if ("en".equals(DOGlobals.getValue("lang.local"))) {
			childTitle = "Sub-table data";
		}
		
		// ///geneInsertParent
		try {
			/**
			 * 生成父面板以及表格
			 */

			DOService mainBrowseService = DOService.getService(geneATable
					+ "_browse");
			DOGridModel parentGrid = genePaneAndGrid(parentCategory,
					gridSupportMore, geneATable + "_BrowseParent", browse,
					null, mainBrowseService, true);

			/**
			 * 生成子面板以及表格，包含子面板多数据的新增、修改、删除
			 */
			DOService findByParentService = geneFindByParentService();
			DOGridModel childGrid = genePaneAndGrid(childCategory,
					gridList, childATable + "_BrowseChild", childTitle,
					parentGrid, findByParentService, true);

			/**
			 * 生成总面板
			 */
			DOPaneModel mainPane = new DOPaneModel();
			mainPane.setCategory(parentCategory);
			mainPane.setL10n("PM_" + geneATable + "_BrowseMain");
			mainPane.setName("PM_" + geneATable + "_BrowseMain");
			mainPane.setController(contentPane);
			DOService insertPane = DOService.getService("do_ui_panemodel_copy");
			DAOUtil.INSTANCE().store(mainPane, insertPane);

			DOService paneLinkService = DOService
					.getService("DO_UI_PaneLinks_copy");
			DOPaneLinks plinks = new DOPaneLinks();
			plinks.setParentPane(mainPane);
			plinks.setChildPane(parentGrid.getContainerPane());
			plinks.setOrderNum(5);
			DAOUtil.INSTANCE().store(plinks, paneLinkService);

			plinks = new DOPaneLinks();
			plinks.setParentPane(mainPane);
			plinks.setChildPane(childGrid.getContainerPane());
			plinks.setOrderNum(10);
			DAOUtil.INSTANCE().store(plinks, paneLinkService);

			DOGridModel mainGridResult = DOGridModel.getGridModelByName("GM_"
					+ geneATable + "_Result");
			List<DOFormModel> allGridLinks = mainGridResult
					.getAllGridFormLinks();

			for (DOFormModel aFM : allGridLinks) {

				// ////////修改主表的insert链接到主子表新增面板
				if (aFM.getLinkPaneModel() != null
						&& (aFM.getL10n().contains("查看") || aFM.getL10n()
								.toLowerCase().contains("brow"))) {

					aFM.setLinkPaneModel(mainPane);
					DAOUtil.INSTANCE().store(aFM);
				}
			}

		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DOService geneFindByParentService() throws ExedoException {
		/**
		 * 修改主表的insert链接到主子表新增面板 修改主表的删除到新增级联删除服务。
		 */

		// ///////////////////////////TEAdd
		DOService findChildByParentService = new DOService();
		findChildByParentService.setName(childATable + "_findbyparent");
		findChildByParentService.setL10n(childATable + "_findbyparent");
		findChildByParentService.setBo(parentCategory);
		findChildByParentService.setType(DOService.TYPE_DELETE);
		findChildByParentService.setMainSql("select * from " + childATable
				+ " where " + linkCol + " =?");
		DAOUtil.INSTANCE().store(findChildByParentService);

		/**
		 * 获取参数
		 */
		DOParameter dopParentCurrent = DOParameter.getParameterByProperty(
				DOBOProperty.getDOBOPropertyByName(geneATable,
						parentCategory.getKeyCol()), DOParameter.TYPE_CURRENT);

		/**
		 * 创建服务和参数的链接
		 */
		DOParameterService dps = new DOParameterService();
		dps.setDop(dopParentCurrent);
		dps.setDos(findChildByParentService);
		dps.setOrderNum(5);
		DAOUtil.INSTANCE().store(dps);

		return findChildByParentService;
	}

	public DOService geneDeleteServiceAndRule() throws ExedoException {
		/**
		 * 修改主表的insert链接到主子表新增面板 修改主表的删除到新增级联删除服务。
		 */

		DOService deleteService = DOService.getService(childATable
				+ "_deletebyparent");
		if (deleteService != null) {
			return deleteService;
		}

		DOService mainDeleteService = DOService.getService(geneATable
				+ "_delete");
		// ///////////////////////////TEAdd
		DOService deleteChildByParentService = new DOService();
		deleteChildByParentService.setName(childATable + "_deletebyparent");
		deleteChildByParentService.setL10n(childATable + "_deletebyparent");
		deleteChildByParentService.setBo(parentCategory);
		deleteChildByParentService.setType(DOService.TYPE_DELETE);
		deleteChildByParentService.setMainSql("delete from " + childATable
				+ " where " + linkCol + " =?");
		DAOUtil.INSTANCE().store(deleteChildByParentService);

		/**
		 * 获取参数
		 */
		DOParameter dopParentCurrent = DOParameter.getParameterByProperty(
				DOBOProperty.getDOBOPropertyByName(geneATable,
						parentCategory.getKeyCol()), DOParameter.TYPE_CURRENT);

		/**
		 * 创建服务和参数的链接
		 */
		DOParameterService dps = new DOParameterService();
		dps.setDop(dopParentCurrent);
		dps.setDos(deleteChildByParentService);
		dps.setOrderNum(5);
		DAOUtil.INSTANCE().store(dps);

		DORule deleteChildByParentServiceRule = this
				.geneServiceToRule(deleteChildByParentService);

		DOServiceRule dsrDelete = new DOServiceRule();
		dsrDelete.setRule(deleteChildByParentServiceRule);
		dsrDelete.setService(mainDeleteService);
		dsrDelete.setExeType(DOServiceRule.EXETYPE_BEFOR);
		dsrDelete.setLinkSalience(5);
		DOService serviceRuleInsert = DOService
				.getService("DO_Service_Rule_copy");

		DAOUtil.INSTANCE().store(dsrDelete, serviceRuleInsert);

		return mainDeleteService;
	}

	/**
	 * @param dao
	 * @param aService
	 * @throws ExedoException
	 * @throws DAOException
	 */
	public DOGridModel genePaneAndGrid(DOBO bo, DOController controller,
			String aName, String title, DOGridModel parentGrid,
			DOService gridService, boolean isBrowse) throws ExedoException {

		Hashtable<String, String> multiL10ns = GeneUICompByTableJquery
				.dealMultiL10ns();

		DOGridModel gridM = new DOGridModel();
		gridM.setCategory(bo);
		gridM.setCaption(title);
		gridM.setName("GM_" + aName);
		gridM.setL10n("GM_" + aName);

		if (parentGrid == null) {
			// ///////main table
			gridM.setGridType(1);

		}

		if (gridService != null) {
			gridM.setService(gridService);
		}

		gridM.setController(controller);
		gridM.setColNum(Integer.valueOf(2));

		DAOUtil.INSTANCE().store(gridM);

		int i = 1;

		// ///服务的属性 原来是aService.retrieveProperties
		for (Iterator itProp = bo.retrieveProperties().iterator(); itProp
				.hasNext();) {

			DOBOProperty prop = (DOBOProperty) itProp.next();
			if (prop.isKeyCol()) {
				continue;
			}

			if (parentGrid != null) {
				if (prop.getColName().equalsIgnoreCase(this.linkCol)) {
					continue;
				}
			}

			DOFormModel formM = new DOFormModel();
			formM.setRelationProperty(prop);

			formM.setL10n(prop.getL10n());

			if (prop.isInt() || prop.isLong()) {
				formM.setExedojoType("digits");
			} else if (prop.isNumberType()) {
				formM.setExedojoType("number");
			} else if (prop.isString()) {
				formM.setUiType("maxlength=" + prop.getDbSize().intValue());
			}

			if (multiL10ns.get(prop.getOldColName()) != null) {
				formM.setL10n(multiL10ns.get(prop.getOldColName()));
			} else {
				formM.setL10n(prop.getL10n());
			}
			formM.setGridModel(gridM);

			formM.setOrderNum(Integer.valueOf(i * 5));
			if (prop.isDateOrTimeType()) {
				if (isBrowse) {
					formM.setController(formValueDate);
				} else {
					formM.setController(formDateMy97);
				}
			} else {

				if (isBrowse) {
					formM.setController(formValueSimple);
				} else {
					if (prop.getDbSize() != null
							&& prop.getDbSize().intValue() > 500) {
						formM.setController(formTextArea);
						formM.setIsNewLine(DOFormModel.NEWLINE_YES);
					} else {
						formM.setController(formInputText);
					}
				}
			}

			DAOUtil.INSTANCE().store(formM);
			i++;
		}

		// /////////////////保存时，保存主子表 。

		if (parentGrid != null && !isBrowse) {
			geneChildEditButton(parentGrid, gridM, i);
			// biNewService.put
		}
		// 对每个Grid赋給一个Pane
		DOPaneModel pane = new DOPaneModel();
		pane.setCategory(bo);
		pane.setName("PM_" + aName);

		// ///下一步考虑 是不是名称采用和Servie 一致
		pane.setTitle(title);
		pane.setL10n("PM_" + aName);
		pane.setLinkType(Integer.valueOf(DOPaneModel.LINKTYPE_GRIDMODEL));
		pane.setLinkUID(gridM.getObjUid());
		pane.setController(contentPane);
		DOService insertPane = DOService.getService("do_ui_panemodel_copy");
		DAOUtil.INSTANCE().store(pane, insertPane);

		gridM.setContainerPane(pane);

		return gridM;
	}

	public void geneChildEditButton(DOGridModel parentGrid, DOGridModel gridM,
			int i) throws ExedoException {
		// /TEAdd
		DOFormModel fTEAdd = new DOFormModel();
		fTEAdd.setGridModel(gridM);
		fTEAdd.setL10n("Add ");
		fTEAdd.setAlign("center");
		fTEAdd.setController(formTEAdd);
		fTEAdd.setOrderNum(Integer.valueOf(i * 5));
		fTEAdd.setIsOutGridAction(DOFormModel.OUTGRID_TOP);
		DAOUtil.INSTANCE().store(fTEAdd);
		i++;
		// /TEDelete
		DOFormModel fTEDelete = new DOFormModel();
		fTEDelete.setIsOutGridAction(DOFormModel.OUTGRID_TOP);
		fTEDelete.setGridModel(gridM);
		fTEDelete.setL10n("Remove");
		fTEDelete.setAlign("center");
		fTEDelete.setInputConstraint("noCloseOpener");

		DOService deleteService = DOService.getService(childCategory.getName()
				+ "_delete");
		fTEDelete.setLinkService(deleteService);
		fTEDelete.setController(formTEDelete);
		fTEDelete.setOrderNum(Integer.valueOf(i * 5));
		DAOUtil.INSTANCE().store(fTEDelete);

		DOService mainParentService = null;

		if (gridM.getName().contains("Insert")) {
			mainParentService = DOService.getService(geneATable + "_insert");
		} else {
			mainParentService = DOService.getService(geneATable + "_update");
		}

		if (parentGrid != null) {

			DOFormModel fTStore = new DOFormModel();
			fTStore.setGridModel(parentGrid);
			fTStore.setIsOutGridAction(DOFormModel.OUTGRID_BOTTOM);

			if ("en".equals(DOGlobals.getValue("lang.local"))) {
				fTStore.setL10n("Save");
			} else {
				fTStore.setL10n("保存");
			}
			fTStore.setAlign("center");

			fTStore.setLinkService(mainParentService);
			fTStore.setController(formTESave);
			fTStore.setOrderNum(Integer.valueOf(i * 5));
			
			DOPaneModel mainPaneResult = DOPaneModel.getPaneModelByName("PM_"
					+ geneATable + "_Result");
			fTStore.setLinkPaneModel(mainPaneResult);
			fTStore.setTargetPaneModel(mainPaneResult);
			
			DAOUtil.INSTANCE().store(fTStore);

			DOFormModel fTStore2 = new DOFormModel();
			fTStore2.setGridModel(parentGrid);
			fTStore2.setIsOutGridAction(DOFormModel.OUTGRID_INLINE);

			if ("en".equals(DOGlobals.getValue("lang.local"))) {
				fTStore2.setL10n("Save:" + this.childATable);
			} else {
				fTStore2.setL10n("保存" + this.childATable);
			}
			fTStore2.setAlign("center");

			DOService childInsertServiceDefault = DOService
					.getService(childATable + "_insert");

			String newInsertServiceName = childATable + "_insert_for_parent";

			DOService childInsertService = DOService
					.getService(newInsertServiceName);
			if (childInsertService == null) {
				childInsertService = copyService(childInsertServiceDefault,
						newInsertServiceName);
			}
			/**
			 * 第一服务是新增
			 */
			fTStore2.setLinkService(childInsertService);
			/**
			 * 第二服务是修改 只有在修改时才有第二服务。
			 */
			DOService childModfiyServiceDefault = DOService
					.getService(childATable + "_update");
			String newModfiyServiceName = childATable + "_update_for_parent";

			DOService childModifyService = DOService
					.getService(newModfiyServiceName);
			if (childModifyService == null) {
				childModifyService = copyService(childModfiyServiceDefault,
						newModfiyServiceName);
			}

			fTStore2.setSecondService(childModifyService);

			fTStore2.setController(formTESave);
			fTStore2.setOrderNum(Integer.valueOf(i * 5));
			DAOUtil.INSTANCE().store(fTStore2);

			/**
			 * fTStore2 GridTarget 目标表格
			 */
			DOFormTarget dft = new DOFormTarget();
			dft.setFormModel(fTStore2);
			dft.setTargetGridModelUid(gridM.getObjUid());
			DAOUtil.INSTANCE().store(dft);

			/**
			 * GridTarget 目标表格
			 */
			dft = new DOFormTarget();
			dft.setFormModel(fTStore);
			dft.setTargetGridModelUid(gridM.getObjUid());
			DAOUtil.INSTANCE().store(dft);

			/**
			 * GridTarget 目标表格
			 */
			dft = new DOFormTarget();
			dft.setFormModel(fTStore);
			dft.setTargetGridModelUid(parentGrid.getObjUid());
			DAOUtil.INSTANCE().store(dft);

			/**
			 * 业务对象关联
			 */
			DOFormLinks dfls = new DOFormLinks();
			dfls.setForm(fTStore);
			dfls.setLinkform(fTStore2);
			DAOUtil.INSTANCE().store(dfls);

		}

	}

	public DOService copyService(DOService dos, String newInsertServiceName) {

		BOInstance biService = new BOInstance();
		biService.fromObject(dos);

		DOService thisService = DOService.getService("DO_Service_copy");

		Transaction t = thisService.currentTransaction();
		try {
			t.begin();
			DOBO boServicePara = DOBO.getDOBOByName("DO_Parameter_Service");
			DOBO boServiceRule = DOBO.getDOBOByName("DO_Service_Rule");
			biService.setUid(null);
			biService.putValue("objuid", null);
			biService.putValue("name", newInsertServiceName);
			biService.putValue("l10n", newInsertServiceName);
			biService.putValue("bouid", this.childCategory.getObjUid());
			BOInstance biNewService = thisService.invokeUpdate(biService);

			for (Iterator<DOParameterService> it = dos
					.retrieveParaServiceLinks().iterator(); it.hasNext();) {
				DOParameterService dps = it.next();
				BOInstance newServiceParas = boServicePara.getInstance(dps
						.getObjUid());

				newServiceParas.setUid(null);
				newServiceParas.putValue(dos.getBo().getKeyCol(), "");

				System.out.println("newServiceParas::" + newServiceParas);
				if (dps.getDop().getName().equalsIgnoreCase(this.linkCol)) {

					DOBOProperty property = DOBOProperty.getDOBOPropertyByName(
							this.geneATable, dos.getBo().getKeyCol());
					DOParameter parentCurrent = DOParameter
							.getParameterByProperty(property,
									DOParameter.TYPE_CURRENT);
					newServiceParas.putValue("parameteruid",
							parentCurrent.getObjUid());
				}
				boServicePara.getDInsertService().invokeUpdate(newServiceParas);
			}
			for (Iterator<DOServiceRule> it = dos.retrieveServiceRules()
					.iterator(); it.hasNext();) {
				DOServiceRule dsr = it.next();
				BOInstance bi = boServiceRule.getInstance(dsr.getObjUid());
				bi.setUid(null);
				boServiceRule.getDInsertService().invokeUpdate(bi);
			}
			t.end();

			return DOService.getServiceByID(biNewService.getUid());
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return null;
		}
	}

	public DORule geneServiceToRule(DOService dos) throws ExedoException {

		BOInstance biService = new BOInstance();
		biService.fromObject(dos);

		BOInstance paras = new BOInstance();
		paras.putValue("serviceUid", biService.getUid());
		paras.putValue("onlyRun", "1");
		paras.putValue("condition", "true");
		paras.putValue("conditionType", "1");// 1 代表script
		paras.putValue("name", "Rule_" + biService.getValue("name"));
		paras.putValue("l10n", "Rule_" + biService.getValue("l10n"));
		paras.putValue("salience", "5");
		paras.putValue("bouid", biService.getValue("bouid"));

		DOService insertRule = DOService.getService("DO_Rule_Insert");
		BOInstance biRule = insertRule.invokeUpdate(paras);

		return DORule.getDORuleByID(biRule.getUid());
	}

	public static void main(String[] args) {

		CacheFactory.getCacheData().fromSerialObject();
		DOActionConfig doc = DOActionConfig
				.getActionConfig("com.exedosoft.plat.action.CoreSaveEditAction");
		System.out.println("DOActionConfig:::" + doc);
	}

	// private static void geneSaveButtonForm(DOService aService, String aName,
	// DOGridModel gridM,String l10n) throws ExedoException {
	//
	// DOFormModel formM = new DOFormModel();
	// formM.setL10n(l10n);
	//
	//
	//
	// DOService linkService = DOService.getService(aService.getBo().getName()
	// + uName);
	// formM.setLinkService(linkService);
	// formM.setIsNewLine(1);
	// formM.setNameColspan(Integer.valueOf(0));
	// formM.setIsOutGridAction(DOFormModel.OUTGRID_BOTTOM);
	//
	// formM.setAlign("center");
	//
	// DOPaneModel pm = DOPaneModel.getPaneModelByName("PM_"
	// + aService.getBo().getName() + "_list");
	// formM.setGridModel(gridM);
	// formM.setOrderNum(Integer.valueOf(1000));
	// formM.setController(formServiceUf);
	// formM.setLinkPaneModel(pm);
	// formM.setTargetPaneModel(pm);
	// DAOUtil.INSTANCE().store(formM);
	//
	// }

	// public DOService getBatchInsertChildService() throws ExedoException {
	// DOService insertService = DOService.getService(childATable + "_insert");
	// System.out.println("Insert Service:::" + insertService);
	// DOService newService = copyService(insertService);
	// DOActionConfig doc = DOActionConfig
	// .getActionConfig("com.exedosoft.plat.action.CoreSaveEditAction");
	//
	// newService.setL10n(insertService.getName() + "_batch");
	// newService.setName(insertService.getName() + "_batch");
	// newService.setActionConfig(doc);
	// newService.setBo(childCategory);
	// DAOUtil.INSTANCE().store(newService);
	// return newService;
	// }

}
