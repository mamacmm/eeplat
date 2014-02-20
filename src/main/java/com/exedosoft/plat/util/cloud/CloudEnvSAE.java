package com.exedosoft.plat.util.cloud;

import com.sina.sae.util.SaeUserInfo;

public class CloudEnvSAE implements CloudEnv {

	protected CloudEnvSAE() {

		// try {
		// Class c = Class.forName("com.sina.sae.util.SaeUserInfo");
		// Object o = c.newInstance();
		// o.
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
	}

	public String getAppName() {
		return SaeUserInfo.getAppName();
	}

	public String getAccessKey() {
		return SaeUserInfo.getAccessKey();
	}

	public String getSecretKey() {

		return SaeUserInfo.getSecretKey();
	}

	@Override
	public String getDBName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDBHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDBPort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDBUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDBPassword() {
		// TODO Auto-generated method stub
		return null;
	}

}
