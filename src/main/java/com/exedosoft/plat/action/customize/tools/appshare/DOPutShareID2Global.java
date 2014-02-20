package com.exedosoft.plat.action.customize.tools.appshare;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.plat.util.RestUtil;

public class DOPutShareID2Global extends DOExportSQL {
	private static final long serialVersionUID = 568992871873045123L;


	@Override
	public String excute() throws ExedoException {

		/**
		 * 首先检查用户名 密码 是否正确。
		 */

		if (DOGlobals.getInstance().getSessoinContext().getFormInstance() == null) {
			this.setEchoValue(I18n.instance().get("No Form Data!"));
			return NO_FORWARD;
		}

		String callSelectAuth = "callType=sa&contextServiceName=eeplat_user_shareid_ws";

		String shareid = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("name");
		String sharesecret = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("password");

		if (shareid == null || sharesecret == null) {

			this.setEchoValue(I18n.instance().get(
					"Need  ShareID and ShareSecret!"));
			return NO_FORWARD;
		}

		
		BOInstance shareUser = null;
		
		try {
			shareUser = RestUtil.selectOne(callSelectAuth + "&shareid="
					+ shareid + "&sharesecret=" + sharesecret);
			
			System.out.println("ShareUser:::::" + shareUser);
			
			if (shareUser == null) {
				this.setEchoValue(I18n.instance().get(
						"ShareID 或者 ShareSecret 不正确!"));
				return NO_FORWARD;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setEchoValue(I18n.instance()
					.get("ShareID 或者 ShareSecret 不正确!"));
			return NO_FORWARD;
		}
		
		DOGlobals.getInstance().getSessoinContext().getGlobal().putValue("ShareID", shareid);
		DOGlobals.getInstance().getSessoinContext().getGlobal().putValue("ShareSecret", sharesecret);
		DOGlobals.getInstance().getSessoinContext().getGlobal().putValue("shareUser", shareUser);
		
		
		
		this.setEchoValue(I18n.instance()
				.get("正确输入，您可以安装AppShare中的应用了!"));
		return DEFAULT_FORWARD;

	}

}
