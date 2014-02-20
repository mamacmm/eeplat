package com.exedosoft.plat.action.customize.tools;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class DORemoveConfigApplication extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8093868865224942852L;
	private static Log log = LogFactory.getLog(DORemoveConfigApplication.class);

	public String excute() {

		// DOService DO_BusiPackage_deletebyapplicationuid =
		// DOService.getService("DO_BusiPackage_deletebyapplicationuid");

		DOBO bo = DOBO.getDOBOByName("DO_Application");
		BOInstance<?> biApp = bo.getCorrInstance();
		String applicationId = null;

		if (biApp != null) {
			applicationId = biApp.getUid();
		}

		else if (biApp == null) {

			DOBO boAppshare = DOBO.getDOBOByName("multi_appshare");
			BOInstance<?> biAppShare = boAppshare.getCorrInstance();
			if (biAppShare != null) {
				applicationId = biAppShare.getValue("share_app_id");
			}
		}

		if (applicationId == null) {
			this.setEchoValue("The application is not found!");
			return NO_FORWARD;
		}

		DOService deleteApp = DOService.getService("DO_Application_Delete");
		DOService deletePackages = DOService
				.getService("DO_BusiPackage_deletebyapplicationuid");
		
		DOService deletePackageRubbish = DOService.getService("DO_BusiPackage_delete_rubbish");
		DOService findBOByPackage = DOService.getService("DO_BO_FindByBPUid");

		Transaction t = deleteApp.currentTransaction();
		t.begin();
		try {
		
			/**
			 * 删除包
			 */
			deletePackages.invokeUpdate(applicationId);
			deletePackageRubbish.invokeUpdate();

			/**
			 * 删除没有对应包的业务对象
			 */
			DOService findRubbishBOs = DOService.getService("DO_BO_List_rubbish");
			List<BOInstance<?>> listBO = findRubbishBOs
					.invokeSelect();
			for (BOInstance<?> biBO : listBO) {
				DORemoveConfigDOBO.removeBO(biBO);
			}

			// /delete packages

			// /delete App
			deleteApp.invokeUpdate(applicationId);

			
			
//			List<BOInstance<?>> listP = service.invokeSelect(applicationId);
//			for (BOInstance<?> biPackage : listP) {
//				List<BOInstance<?>> listBO = findBOByPackage
//						.invokeSelect(biPackage.getUid());
//				for (BOInstance<?> biBO : listBO) {
//					DORemoveConfigDOBO.removeBO(biBO);
//				}
//			}
			
			

		} catch (ExedoException e) {
			t.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.end();

		// DO_BusiPackage_deletebyapplicationuid

		// DO_BusiPackage_ofapplicationuid

		// 如果这个是安装的共享应用则把共享标示也删掉

		if ("true".equals(DOGlobals.getValue("multi.tenancy"))
				&& DOGlobals.getInstance().getSessoinContext()
						.getTenancyValues() != null
				&& DOGlobals.getInstance().getSessoinContext()
						.getTenancyValues().getTenant() != null) {

			String tenantId = DOGlobals.getInstance().getSessoinContext()
					.getTenancyValues().getTenant().getUid();
			DOService findShare = DOService
					.getService("multi_appshare_findbyshareappid");
			BOInstance shareApp = findShare.getInstance(applicationId);
			if (shareApp != null) {
				DOService deleteAppShare = DOService
						.getService("multi_appshareinstall_delete_bytenantid");
				try {
					deleteAppShare.invokeUpdate(shareApp.getUid(), tenantId);
				} catch (ExedoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return DEFAULT_FORWARD;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
