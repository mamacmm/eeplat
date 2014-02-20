package com.exedosoft.plat.action.customize.tools.appshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.StringEscapeUtils;

import com.exedosoft.plat.CacheFactory;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOBOProperty;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.GeneDBFromConfig;
import com.exedosoft.plat.util.I18n;

public class DOExportSQL extends DOAbstractAction {

	private static final long serialVersionUID = 568992871873045123L;

	@Override
	public String excute() throws ExedoException {

		if (this.service == null || this.service.getTempSql() == null) {
			this.setEchoValue(I18n.instance().get("未配置SQL 语句"));
			return NO_FORWARD;
		}

		StringBuilder sb = new StringBuilder("");
		DOBO bo = DOBO.getDOBOByName("do_bo");

		BOInstance selectBI = bo.getCorrInstance();
		Transaction t = this.service.currentTransaction();
		List<String> allIDs = new ArrayList<String>();
		try {
			t.begin();
			exportBO(sb, selectBI);
			t.end();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		this.setEchoValue(sb.toString());
		return DEFAULT_FORWARD;

	}

	void exportBO(StringBuilder sb, BOInstance selectBI) {
		DOBO boGrid = DOBO.getDOBOByName("DO_UI_GridModel");

		sb.append("<!--******************************Object*******************************-->\n");

		DOGlobals.getInstance().getSessoinContext().getGlobal()
				.putValue("selectBI", selectBI);

		this.appendBOInstance(sb, selectBI);

		// sb.append("\n<tenant>").append(biTenancy.getValue("name"))
		// .append("</tenant>\n");
		DODataSource dss = null;
		// /多租户情况`

		if ("true".equals(DOGlobals.getValue("multi.tenancy"))) {
			dss = DOGlobals.getInstance().getSessoinContext()
					.getTenancyValues().getDataDDS();
		} else {// 单租户情况
			DOBO bo = DOBO.getDOBOByName("do_authorization");
			dss = bo.getDataBase();
		}

		Connection con = null;
		try {


		///这个其实是只支持mysql的节奏。
			if ("com.mysql.jdbc.Driver".equals(dss.getDriverClass())) {

				con = dss.getConnection();

				String sql = "show create table " + selectBI.getValue("sqlstr");

				PreparedStatement pstmt = con.prepareStatement(sql);

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					sb.append("\n<create_table_sql>")
							.append(replaceShareUserId(rs.getString(2)))
							.append("</create_table_sql>\n");
				}

				pstmt.close();
			} else {

				DOBO oneBO = DOBO.getDOBOByID(selectBI.getUid());
				sb.append("\n<create_table_sql>")
						.append(replaceShareUserId(GeneDBFromConfig
								.getCreateOneTable(oneBO)))
						.append("</create_table_sql>\n");
			}

			sb.append("\n<create_bo_name>")
					.append(replaceShareUserId(selectBI.getName()))
					.append("</create_bo_name>\n");

		} catch (Exception ex) {
			this.setEchoValue(ex.getMessage());

		} finally {
//			try {
//				if (!con.isClosed()) {
//					con.close();
//				}
//			} catch (SQLException ex1) {
//				this.setEchoValue(ex1.getMessage());
//
//			}

		}

		// /属性
		DOService servProperties = DOService
				.getService("DO_BO_Property_findbybouid");
		appendJSONS(sb, "property", servProperties);
		// /参数
		DOService servParameters = DOService
				.getService("DO_Parameter_findbybouid");
		appendJSONS(sb, "parameter", servParameters);
		// 规则
		DOService servRules = DOService.getService("DO_Rule_findbybouid");
		appendJSONS(sb, "rule", servRules);

		// 业务对象下面的服务
		DOService servServices = DOService
				.getService("DO_Service_Browse_findbybouid");
		List services = appendJSONS(sb, "service", servServices);

		// 服务下面的参数
		DOService servParaService = DOService
				.getService("DO_Parameter_Service_findbyserviceUid");
		sb.append("<!--************************parameter_service*****************************-->\n");
		for (Iterator it = services.iterator(); it.hasNext();) {
			BOInstance bi = (BOInstance) it.next();
			List paraServices = servParaService.invokeSelect(bi.getUid());
			appendLi(sb, paraServices);
		}
		sb.append("<!--***********************parameter_service*****************************-->\n");

		// 服务下面的规则
		DOService servRuleService = DOService
				.getService("DO_Service_Rule_findbyserviceuid_4_copy");
		sb.append("<!--**********************rule_service***********************************-->\n");
		for (Iterator it = services.iterator(); it.hasNext();) {
			BOInstance bi = (BOInstance) it.next();
			List ruleServices = servRuleService.invokeSelect(bi.getUid());
			appendLi(sb, ruleServices);
		}
		
		
		
		sb.append("<!--***********************rule_service**********************************-->\n");

		// 业务对象下面的面板
		DOService servPanes = DOService
				.getService("DO_UI_PaneModel_selectbyboduid");
		List panes = appendJSONS(sb, "pane", servPanes);

		DOService servPaneLinks = DOService
				.getService("DO_UI_PaneLinks_findbyparentPaneUid");
		sb.append("<!--***********************pane_links**************************************-->\n");
		List<BOInstance> listGridOfPane = new ArrayList();
		for (Iterator it = panes.iterator(); it.hasNext();) {
			BOInstance aPane = (BOInstance) it.next();
			if (aPane != null
					&& aPane.getValue("linkType") != null
					&& aPane.getValue("linkType").equals(
							DOPaneModel.LINKTYPE_GRIDMODEL)) {
				BOInstance aGrid = boGrid
						.getInstance(aPane.getValue("linkUID"));
				if (aGrid != null) {
					listGridOfPane.add(aGrid);
				}
			}
			List list = servPaneLinks.invokeSelect(aPane.getUid());
			appendLi(sb, list);
		}
		sb.append("<!--**********************end pane_links************************************-->\n");

		// /业务对象下面的表格 应该用面板下面表格比较好
		DOService servGrids = DOService
				.getService("DO_UI_GridModel_findbycategoryUid");

		List<BOInstance> grids = servGrids.invokeSelect();
		listGridOfPane.removeAll(grids);
		grids.addAll(listGridOfPane);

		sb.append("<!--*******************************grid***********************************-->\n");
		appendLi(sb, grids);
		sb.append("<!--******************************end grid************************************-->\n");

		// /表格下面的表格元素
		DOService servForms = DOService
				.getService("DO_UI_FormModel_findbyGridModelUid");
		// /表格元素连接的表格元素
		DOService servFormRelations = DOService
				.getService("DO_UI_FormLinks_findbyformuid");

		DOService servFormTargets = DOService
				.getService("DO_UI_FormTargets_findbyformUid");

		sb.append("<!--*********************************form*********************************-->\n");
		StringBuilder sbRelations = new StringBuilder(
				"<!--*****************************************form_relation*************************************-->\n");
		StringBuilder sbTargets = new StringBuilder(
				"<!--*******************************************form_target***************************************-->\n");

		for (Iterator it = grids.iterator(); it.hasNext();) {
			BOInstance bi = (BOInstance) it.next();
			List forms = servForms.invokeSelect(bi.getUid());
			appendLi(sb, forms);
			for (Iterator itForm = forms.iterator(); itForm.hasNext();) {
				BOInstance biForm = (BOInstance) itForm.next();
				List formRelations = servFormRelations.invokeSelect(biForm
						.getUid());
				appendLi(sbRelations, formRelations);
				List formTargets = servFormTargets
						.invokeSelect(biForm.getUid());
				appendLi(sbTargets, formTargets);
			}
		}
		sbRelations
				.append("<!--**************************end form_relation*************************************-->\n");
		sbTargets
				.append("<!--****************************end form_target***************************************-->\n");
		sb.append("<!--******************************end form***********************************-->\n");
		sb.append(sbRelations);
		sb.append(sbTargets);

		// 菜单
		sb.append("<!--*****************************menu************************************-->\n");
		DOService servMenus = DOService
				.getService("DO_UI_MenuModel_findbycategoryUid_top");
		DOService servChildMenu = DOService
				.getService("DO_UI_MenuModel_selflink");

		List allMenus = new ArrayList();
		List topMenus = servMenus.invokeSelect();
		allMenus.addAll(topMenus);
		for (Iterator it = topMenus.iterator(); it.hasNext();) {
			BOInstance itMenu = (BOInstance) it.next();
			getChildBIs(allMenus, itMenu, servChildMenu);
		}
		appendLi(sb, allMenus);
		sb.append("<!--**************************end menu******************************-->\n");

		// 树
		sb.append("<!--**************************tree******************************-->\n");
		DOService servTrees = DOService
				.getService("DO_UI_TreeModel_findbycategoryUid");
		DOService servChildTree = DOService
				.getService("DO_UI_TreeModel_findbyparentuid");

		List allTrees = new ArrayList();
		List topTrees = servTrees.invokeSelect();
		allTrees.addAll(topTrees);

		for (Iterator it = topTrees.iterator(); it.hasNext();) {
			BOInstance itTree = (BOInstance) it.next();
			getChildBIs(allTrees, itTree, servChildTree);
		}
		appendLi(sb, allTrees);
		sb.append("<!--*************************end tree*******************************-->\n");

	}

	private String replaceShareUserId(String oldSrc) {

		if (oldSrc == null) {
			return oldSrc;
		}

		try {
			BOInstance shareUser = (BOInstance) DOGlobals.getInstance()
					.getSessoinContext().getGlobal()
					.getObjectValue("shareUser");

			if (shareUser != null) {
				String userID = shareUser.getValue("user_id");
				List<String> allBO = (List<String>) DOGlobals.getInstance()
						.getSessoinContext().getGlobal()
						.getObjectValue("allbo4application");
				if (allBO == null || allBO.size() == 0) {
					return oldSrc;
				}
				for (String tableName : allBO) {
					oldSrc = oldSrc
							.replace(tableName, userID + "_" + tableName);
				}
			}
			return oldSrc;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return oldSrc;
		}
	}

	public void appendGlobalsConfig(StringBuilder sb,
			DOService allControllerConfigs, String title, List baseData) {
		List<BOInstance> list = allControllerConfigs.invokeSelect();
		sb.append("<!--*************************" + title
				+ "*******************************-->\n");
		List<BOInstance> adds = new ArrayList<BOInstance>();
		for (BOInstance bi : list) {
			if (!baseData.contains(bi.getUid())) {
				adds.add(bi);
			}
		}
		appendGlobalLi(sb, adds);
	}
	
	
	
	public void appendGlobalsConfig(StringBuilder sb,
			DOService allControllerConfigs, String title) {
		List<BOInstance> list = allControllerConfigs.invokeSelect();
		sb.append("<!--*************************" + title
				+ "*******************************-->\n");
		List<BOInstance> adds = new ArrayList<BOInstance>();
		for (BOInstance bi : list) {
				adds.add(bi);
		}
		appendGlobalLi(sb, adds);
	}

	protected void appendLi(StringBuilder sb, List list) {

		// / toJSONSTring 需要进行转义
		for (Iterator it = list.iterator(); it.hasNext();) {
			BOInstance bi = (BOInstance) it.next();
			appendBOInstance(sb, bi);
		}
	}

	protected void appendGlobalLi(StringBuilder sb, List list) {

		// / toJSONSTring 需要进行转义
		for (Iterator it = list.iterator(); it.hasNext();) {
			BOInstance bi = (BOInstance) it.next();
			appendGlobalBOInstance(sb, bi);
		}
	}

	protected void getChildBIs(List menus, BOInstance parent,
			DOService servChild) {

		if (parent == null) {
			return;
		}
		for (Iterator it = servChild.invokeSelect(parent.getUid()).iterator(); it
				.hasNext();) {
			BOInstance bi = (BOInstance) it.next();
			menus.add(bi);
			getChildBIs(menus, bi, servChild);
		}
	}

	protected List appendJSONS(StringBuilder sb, String label, DOService service) {

		List list = service.invokeSelect();
		sb.append("<!--*********").append(label).append("***********-->\n");
		appendLi(sb, list);
		sb.append("<!--*********end ").append(label).append("**********-->\n");
		return list;
	}

	protected void appendGlobalBOInstance(StringBuilder sb, BOInstance bi) {

		appendBOInstanceHelper(sb, bi, "global");
	}

	protected void appendBOInstance(StringBuilder sb, BOInstance bi) {
		appendBOInstanceHelper(sb, bi, "insert");

	}

	private void appendBOInstanceHelper(StringBuilder sb, BOInstance bi,
			String tag) {

		if ("global".equals(tag)) {
			sb.append("<global>");
		} else {
			sb.append("<insert>");
		}

		boolean isReplace = false;

		BOInstance selectBI = (BOInstance) DOGlobals.getInstance()
				.getSessoinContext().getGlobal().getObjectValue("selectBI");

		if (selectBI != null
				&& ("do_bo".equalsIgnoreCase(bi.getBo().getSqlStr())
						|| "do_bo_icon"
								.equalsIgnoreCase(bi.getBo().getSqlStr())
						|| "do_service"
								.equalsIgnoreCase(bi.getBo().getSqlStr())
						|| "do_ui_panemodel".equalsIgnoreCase(bi.getBo()
								.getSqlStr())
						|| "do_ui_gridmodel".equalsIgnoreCase(bi.getBo()
								.getSqlStr()) || "do_rule".equalsIgnoreCase(bi
						.getBo().getSqlStr()))) {

			isReplace = true;
		}

		sb.append("insert into ").append(bi.getBo().getSqlStr()).append(" (");
		StringBuffer sbValues = new StringBuffer();

		List<DOBOProperty> listProps = bi.getBo().retrieveProperties();
		List<String> listPropstr = new ArrayList<String>();

		for (DOBOProperty boProp : listProps) {
			listPropstr.add(boProp.getColName());
		}

		for (Iterator<Entry<String, Object>> itEntry = bi.getMap().entrySet()
				.iterator(); itEntry.hasNext();) {
			Entry<String, Object> e = itEntry.next();
			if (!listPropstr.contains(e.getKey())) {
				continue;
			}
			sb.append(e.getKey());

			String value = StringEscapeUtils.escapeXml(StringEscapeUtils
					.escapeSql(e.getValue().toString()));

			if (isReplace) {
				sbValues.append("'").append(replaceShareUserId(
				value)).append("'");
			} else {
				sbValues.append("'").append(value).append("'");

			}
			if (itEntry.hasNext()) {
				sb.append(",");
				sbValues.append(",");
			}
		}
		sb.append(" ) values (").append(sbValues).append(")");
		if ("global".equals(tag)) {
			sb.append("</global>\n");
		} else {
			sb.append("</insert>\n");
		}

	}

	public static void main(String[] args) {

		
		CacheFactory.getCacheData().fromSerialObject();
		
		DOExportSQL  a = new DOExportSQL();
		StringBuilder sb = new StringBuilder();
		
		DOService servRuleService = DOService
				.getService("DO_Service_Rule_findbyserviceuid");
		sb.append("<!--**********************rule_service***********************************-->\n");
	

			List ruleServices = servRuleService.invokeSelect("ff8080813af2be9e013af32668830079");
			a.appendLi(sb, ruleServices);

			
			System.out.println("SB:::" + sb);
		
//		// /////////////控制器
//		DOService allControllerConfigs = DOService
//				.getService("DO_UI_Controller_List");
//		List<BOInstance> list = allControllerConfigs.invokeSelect();
//		List<String> listStrs = new ArrayList<String>();
//		for (BOInstance bi : list) {
//			listStrs.add("\"" + bi.getUid() + "\"");
//		}
//		System.out.println("allControllerConfigs::" + listStrs);
//
//		// ////////////自定义动作
//		DOService allActionConfigs = DOService
//				.getService("DO_ActionConfig_List");
//
//		list = allActionConfigs.invokeSelect();
//		listStrs = new ArrayList<String>();
//		for (BOInstance bi : list) {
//			listStrs.add("\"" + bi.getUid() + "\"");
//		}
//		System.out.println("allActionConfigs::" + listStrs);
//
//		// ////////////////////资源
//		DOService allResources = DOService.getService("do_resource_list");
//
//		list = allResources.invokeSelect();
//		listStrs = new ArrayList<String>();
//		for (BOInstance bi : list) {
//			listStrs.add("\"" + bi.getUid() + "\"");
//		}
//		System.out.println("allResources::" + listStrs);
//
//		// //////////////////自定义脚本。
//		DOService allScript = DOService.getService("DO_BO_Icon_List");
//
//		list = allScript.invokeSelect();
//		listStrs = new ArrayList<String>();
//		for (BOInstance bi : list) {
//			listStrs.add("\"" + bi.getUid() + "\"");
//		}
//		System.out.println("allScript::" + listStrs);

	}

}
