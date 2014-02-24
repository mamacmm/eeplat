package com.exedosoft.plat.util.cloud;

import com.exedosoft.plat.util.DOGlobals;

public class CloudEnvFactory {

	private static CloudEnvFactory anInstance = new CloudEnvFactory();

	private static CloudEnv cacheCloudEnv = null;

	private CloudEnvFactory() {

	}

	public static CloudEnvFactory getInstance() {
		return anInstance;
	}

	public static CloudEnv getCloudEnv() {

		if (cacheCloudEnv != null) {
			return cacheCloudEnv;
		}
		/**
		 * 不加同步锁，不影响最终的结果
		 */
		try {
			if ("sae".equals(DOGlobals.getValue("cloud.env"))) {
				cacheCloudEnv = new CloudEnvSAE();
			}
			else if ("jae".equals(DOGlobals.getValue("cloud.env"))) {
				cacheCloudEnv = new CloudEnvJAE();
			}
			else if ("mopaas".equals(DOGlobals.getValue("cloud.env"))) {
				cacheCloudEnv = new CloudEnvMoPaaS();
			}

		} catch (Exception e) {

		}
		if (cacheCloudEnv == null) {
			cacheCloudEnv = new CloudEnvNormal();
		}
		return cacheCloudEnv;

	}

	/**
	 * 没有SAE的 jar包。
	 * 
	 * @return
	 */
	// public static CloudEnv getCloudEnv() {
	//
	// if (cacheCloudEnv != null) {
	// return cacheCloudEnv;
	// }
	//
	// cacheCloudEnv = new CloudEnvNormal();
	// return cacheCloudEnv;
	//
	// }

}
