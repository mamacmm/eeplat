package com.exedosoft.plat.action.customize.tools.appshare;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.exedosoft.plat.CacheFactory;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOApplication;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.storage.oss.OSSUpload;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.plat.util.RestUtil;
import com.exedosoft.tenant.TenancyValues;

public class DOInstallApplicationSQL extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 568992871873045123L;

	@Override
	public String excute() throws ExedoException {

		
		String shareid = DOGlobals.getInstance().getSessoinContext()
				.getGlobal().getValue("ShareID");
		String sharesecret = DOGlobals.getInstance().getSessoinContext()
				.getGlobal().getValue("ShareSecret");
		
		BOInstance shareUser = (BOInstance)DOGlobals.getInstance().getSessoinContext().getGlobal().getObjectValue("shareUser");
		if (shareid == null
				|| sharesecret == null) {

			this.setEchoValue(I18n.instance().get(
					"Need  ShareID and ShareSecret!"));
			return NO_FORWARD;

		}

		String json = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("json");
		

		try {
			BOInstance bi = BOInstance.fromJSONString(json);
			DOApplication doa =  DOApplication.getApplicationByID(bi.getValue("share_app_id"));
			if(doa != null ){
				this.setEchoValue(I18n.instance().get("已经安装成功,请不要重复安装！"));
				return NO_FORWARD;
			}
			
			String xmlPath = bi.getValue("xml_path");
			String providerTenantId = bi.getValue("auth_user_name");

			
			/**
			 * 在云环境下要改为Temp File 
			 */
			String fileName = getFileName(xmlPath);
			
			OSSUpload.downloadFile("appsmarket", xmlPath, fileName);

			DOImportSQL.importSQL(fileName);

		

			String token = null;
			try {
				token = RestUtil.getAccessToken(shareid, sharesecret);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			////安装记录
			String callUpdate = "callType=uf&contextServiceName=eeplat_appshareinstall_insert_ws&access_token="
					+ token;

			Map paras = new HashMap();
			paras.put("appshare_id", bi.getUid());
			paras.put("use_start_date", new java.sql.Date(System.currentTimeMillis()).toLocaleString());
			if (shareUser != null) {
				paras.put("use_user_id",  shareUser.getValue("objuid"));
				paras.put("use_user_name", shareUser.getValue("user_name"));
			}

			JSONArray jsonArray = null;
			List listData = new ArrayList();
			try {
				RestUtil.call(callUpdate, paras);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			CacheFactory.getCacheData().clear();
			CacheFactory.getCacheRelation().getData().clear();
			CacheFactory.getCacheData().fromSerialObject();

			this.setEchoValue(I18n.instance().get("安装成功"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return DEFAULT_FORWARD;

	}

	public static String getFileName(String xmlPath) {
		
		/**
		 * 云环境下的地址
		 */
		if(DOGlobals.getInstance().getValue("cloud.env")!=null){
			return DOGlobals.UPLOAD_TEMP+  xmlPath;
		}

		URL url = DODataSource.class.getResource("/globals.xml");
		String s = url.getPath();
		String s2 = s;
		s = s.substring(0, s2.toLowerCase().indexOf("web-inf"));

		StringBuffer fileName = new StringBuffer(s).append("appshare/").append(
				xmlPath);
		

		return fileName.toString();
	}

	public boolean installXmlFile(String providerTenantId, String fileName) {

		DOService insertInstallRecord = DOService
				.getService("multi_appshareinstall_install");

		Transaction t = insertInstallRecord.currentTransaction();
		t.begin();

		try {
			TenancyValues tv = (TenancyValues) DOGlobals.getInstance()
					.getSessoinContext().getTenancyValues();

			if (tv.getTenant().getUid().equals(providerTenantId)) {
				this.setEchoValue(I18n.instance().get("应用已经存在！"));

				return false;
			}

			Map paras = new HashMap();
			paras.put("use_tenant_id", tv.getTenant().getUid());
			paras.put("use_tenant_name", tv.getTenant().getValue("l10n"));
			insertInstallRecord.invokeUpdate(paras);
			DOImportSQL.importSQL(fileName.toString());

		} catch (Exception e) {

			t.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			t.end();
		}
		this.setEchoValue(I18n.instance().get("安装成功"));
		return true;
	}

	public static void main(String[] args) {

		File tenantFile = new File("c:/" + "appshare/" + "caf");
		tenantFile.mkdir();
		System.out.println("SSSSSSSSSSSS::" + tenantFile.getAbsolutePath());
	}

}
