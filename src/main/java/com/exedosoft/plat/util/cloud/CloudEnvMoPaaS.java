package com.exedosoft.plat.util.cloud;

public class CloudEnvMoPaaS implements CloudEnv {

	protected CloudEnvMoPaaS() {

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
		return System.getenv("MOPAAS_MYSQL891_NAME");// 得到MySQL数据库名字；

	}

	@Override
	public String getDBHost() {

		return System.getenv("MOPAAS_MYSQL891_HOST");// 得到MySQL数据库所在的机器；
	}

	@Override
	public String getDBPort() {

		return System.getenv("MOPAAS_MYSQL891_PORT");// 得到MySQL数据库所在机器的端口号；
	}

	@Override
	public String getDBUserName() {
		return System.getenv("MOPAAS_MYSQL891_USERNAME");// 得到用户连接MySQL数据库时所用的用户名；
	}

	@Override
	public String getDBPassword() {
		return System.getenv("MOPAAS_MYSQL891_PASSWORD");// 得到用户连接MySQL数据库时所用的密码。
	}

}
