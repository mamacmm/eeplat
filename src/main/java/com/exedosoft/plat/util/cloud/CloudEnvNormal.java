package com.exedosoft.plat.util.cloud;

public class CloudEnvNormal implements CloudEnv {

	protected CloudEnvNormal() {

	}

	public String getAppName() {
		return "Normal";
	}

	public String getAccessKey() {
		return "Normal";
	}

	public String getSecretKey() {
		return "Normal";
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
