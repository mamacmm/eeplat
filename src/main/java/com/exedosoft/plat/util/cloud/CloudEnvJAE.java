package com.exedosoft.plat.util.cloud;



public class CloudEnvJAE implements CloudEnv {

	protected CloudEnvJAE() {

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
		

//System.getenv("JAE_MYSQL_ENCODING");
		return "";
	}

	public String getAccessKey() {
		return "";
	}

	public String getSecretKey() {

		return "";
	}

	@Override
	public String getDBName() {
		
		return System.getenv("JAE_MYSQL_DBNAME");
	}

	@Override
	public String getDBHost() {
		
		return System.getenv("JAE_MYSQL_IP");
	}

	@Override
	public String getDBPort() {
		return System.getenv("JAE_MYSQL_PORT");
	}

	@Override
	public String getDBUserName() {
		return System.getenv("JAE_MYSQL_USERNAME");
	}

	@Override
	public String getDBPassword() {
		// TODO Auto-generated method stub
		return System.getenv("JAE_MYSQL_PASSWORD");
	}

}
