package com.exedosoft.plat.action.customize.tools;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.wf.pt.PTNode;
import com.exedosoft.wf.pt.PTVar;
import com.exedosoft.wf.pt.ProcessTemplate;

public class CopyProcessTemplateDeep extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5689928718730452223L;

	@Override
	public String excute() throws ExedoException {

		if (this.service == null || this.service.getTempSql() == null) {
			setEchoValue(I18n.instance().get("未配置SQL 语句"));
			return NO_FORWARD;
		}

		Transaction t = this.service.currentTransaction();
		try {
			t.begin();
			DOBO boPT = DOBO.getDOBOByName("do_pt_processtemplate");
			DOBO boPN = DOBO.getDOBOByName("do_pt_node");
			DOBO boPVar = DOBO.getDOBOByName("do_pt_var");

			// 工作流模板
			
			Random r = new Random();
			int randInt = r.nextInt(100);
			BOInstance biPT = boPT.getCorrInstance();
			if (biPT != null) {
				ProcessTemplate pt = ProcessTemplate.getPTByID(biPT.getUid());

				DOService insertPt = DOService
						.getService("do_pt_processtemplate_insert");
				DOService insertPtNode = DOService
						.getService("do_pt_node_insert_copy");
				DOService insertPtNodeD = DOService
						.getService("do_pt_node_denpendency_insert");
				DOService insertPtVar = DOService
						.getService("do_pt_var_insert_copy");

				DOService findDenpendency = DOService
						.getService("do_pt_node_denpendency_of_pt");

				if (pt != null) {

					// 保存工作流模板
					biPT.putValue("objuid", null);
					biPT.putValue("pt_name", biPT.getValue("pt_name") + "_" + randInt);
					BOInstance biNewPt = insertPt.invokeUpdate(biPT);

					// 保存工作流变量
					List<PTVar> ptVars = pt.retrievePtVars();
					for (Iterator<PTVar> itVars = ptVars.iterator(); itVars
							.hasNext();) {
						PTVar oldVar = itVars.next();
						BOInstance oldBiVar = boPVar.getInstance(oldVar
								.getObjUid());
						oldBiVar.putValue("objuid", null);
						oldBiVar.putValue("varname", oldBiVar.getValue("varname")
								+ "_copy");
						oldBiVar.putValue("pt_uid", biNewPt.getUid());
						insertPtVar.invokeUpdate(oldBiVar);
					}

			
					// 保存工作流节点
					List<PTNode> ptNodes = pt.retrieveNodes();
					for (Iterator<PTNode> itNodes = ptNodes.iterator(); itNodes
							.hasNext();) {
						PTNode oldNode = itNodes.next();
						BOInstance oldBiNode = boPN.getInstance(oldNode
								.getObjUid());
						oldBiNode.putValue("objuid", oldBiNode.getUid().replace(pt.getPtName(),biPT.getValue("pt_name")));
						oldBiNode.putValue("pt_uid", biNewPt.getUid());
						insertPtNode.invokeUpdate(oldBiNode);
					}
					// //保存关联

					List<BOInstance> dependencies = findDenpendency
							.invokeSelect(pt.getObjUid());

					for (Iterator<BOInstance> itBI = dependencies.iterator(); itBI
							.hasNext();) {
						BOInstance biOldDependency = itBI.next();
						biOldDependency.putValue("objuid", biOldDependency.getValue("Pre_N_UID") + biOldDependency.getValue("Post_N_UID") + "_" + randInt);
						biOldDependency.putValue("Pre_N_UID",
								biOldDependency.getValue("Pre_N_UID").replace(pt.getPtName(),biPT.getValue("pt_name")));
						biOldDependency.putValue("Post_N_UID",
								biOldDependency.getValue("Post_N_UID").replace(pt.getPtName(),biPT.getValue("pt_name")));
						insertPtNodeD.invokeUpdate(biOldDependency);
					}

				}
			}
			t.end();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		setEchoValue(I18n.instance().get("复制成功!"));

		return DEFAULT_FORWARD;

	}

	public static void main(String[] args) {

		Random r = new Random();
		int randInt = r.nextInt(100);
		System.out.println("randInt:::" + randInt);

	}
}
