package com.exedosoft.plat.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import com.exedosoft.plat.bo.BOInstance;

/**
 * 调用平台restful webservice
 * 
 * 平台的服务可以通过restful(like) web service 的方式供外部访问。 restful web service 是一种轻量级的web
 * service 的实现方式，简单而且高效。
 * 
 * 
 * 如果一个服务需要被外部访问那么必须设置服务的可见性，可见性为public 或 protected的服务。
 * 
 * public类型的服务: 可以通过匿名访问。 protected类型的服务：需要"调用方"获得平台提供的用户名密码才可以访问。
 * 
 * @author Administrator
 * 
 */

public class RestUtil {

	public final static String baseCallUrl = DOGlobals.getValue("eeplatshare") + "servicecontroller?client=" 
	+ (DOGlobals.getValue("cloud.env")==null?"local":DOGlobals.getValue("cloud.env")) + "&";

	
	public static String getAccessToken(String userName, String pwd) throws IOException, JSONException {
		return getAccessTokenTenant(null, userName,pwd);
	}
	
	public static String getAccessTokenTenant(String tenantId, String userName, String pwd) throws IOException, JSONException {

		StringBuffer callToken = new StringBuffer(baseCallUrl)
				.append("callType=get_token");
		
		
		if(tenantId!=null){
			callToken.append("&tenantId=");
			callToken.append(tenantId);
		}

		callToken.append("&userName=")
				.append(userName).append("&pwd=")
				.append(pwd);
		StringBuffer buffer = callRest(callToken.toString());
		
		JSONObject jobject = null;
		try {
			jobject = new JSONObject(buffer.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(jobject);
		return jobject.getString("token");
	}

	/**
	 * 对汉字进行编码
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeString(String str) {

		if(str==null){
			return null;
		}
		try {
			return URLEncoder.encode(Escape.escape(str), "utf-8");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	/**
	 * 处理主键的调用字符串
	 * 
	 * @param str
	 * @return
	 */

	public static String dealKeyStr(String object, String uid) {

		return new StringBuffer("dataBus=setContext&contextKey=")
				.append(object).append("&contextValue=").append(uid).toString();
	}

	/**
	 * 如果调用 平台的修改类型的服务
	 * 
	 * @return
	 * @throws IOException
	 */
	private static JSONObject restUpdate() throws IOException {
		// /////////////基本URL
		StringBuffer callUpdate = new StringBuffer(baseCallUrl)
				// ///////uf 为修改，租户为zhiliaoshu 服务名称为kids_kindergarten_update
				.append("?callType=uf&&tenantId=zhiliaoshu&contextServiceName=kids_kindergarten_update&kgname=")
				// /传递参数中文要编码
				.append(encodeString("光明幼儿园22")).append("&kgmanager=")
				.append(encodeString("李娜"))
				.append("&kgstatus=1")
				.append("&kgaddress=")
				.append(encodeString("北京市海淀区知春路1号学院国际大厦1501"))
				.append("&kgcity=")
				.append(encodeString("北京"))
				.append("&kgaera=")
				.append(encodeString("海淀区"))
				.append("&kgzipcode=10083")
				.append("&kglinkphone=01082337866")
				.append("&note=")
				.append(encodeString("创造奇迹"))
				.append("&")
				// /修改的情况需要传递表名和主键名称 。
				.append(dealKeyStr("kids_kindergarten",
						"402881f23c0429a0013c042b2064000b"));

		// /////////修改完后会把修改后的记录传递过来
		StringBuffer buffer = callRest(callUpdate.toString());
		JSONObject jobject = null;
		try {
			jobject = new JSONObject(buffer.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobject;
	}

	/**
	 * 如果调用 平台的新增类型的服务
	 * 
	 * @return
	 * @throws IOException
	 */
	private static JSONObject restInsert() throws IOException {

		StringBuffer callInsert = new StringBuffer(baseCallUrl)
				.append("?callType=uf&&tenantId=zhiliaoshu&contextServiceName=kids_kindergarten_insert&kgname=")
				.append(encodeString("测试幼儿园")).append("&kgmanager=")
				.append(encodeString("庞丽娜")).append("&kgstatus=1")
				.append("&kgaddress=")
				.append(encodeString("北京市海淀区知春路1号学院国际大厦1501"))
				.append("&kgcity=").append(encodeString("北京"))
				.append("&kgaera=").append(encodeString("海淀区"))
				.append("&kgzipcode=10083").append("&kglinkphone=01082337866")
				.append("&note=").append(encodeString("创造奇迹YES"));
		StringBuffer buffer = callRest(callInsert.toString());
		JSONObject jobject = null;
		try {
			jobject = new JSONObject(buffer.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobject;
	}

	/**
	 * 如果调用 平台的新增类型的服务
	 * 
	 * @return
	 * @throws IOException
	 */
	private static JSONObject restInsertCRMAu(String token) throws IOException {

		StringBuffer callInsert = new StringBuffer(baseCallUrl)
				.append("?callType=uf&&tenantId=Recruiting&contextServiceName=crmaustralia_insert")
				.append("&access_token=" + token).append("&customerid=jack")
				.append("&pageurl=http://www.jack.com")
				.append("&visiteddatetime=08/05/2003");

		StringBuffer buffer = callRest(callInsert.toString());
		JSONObject jobject = null;
		try {
			jobject = new JSONObject(buffer.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobject;
	}

	/**
	 * 如果调用 平台的新增类型的服务
	 * 
	 * @return
	 * @throws IOException
	 */
	private static JSONObject restInsertPost(String token) throws IOException {

		StringBuffer callInsert = new StringBuffer(baseCallUrl)
				.append("?callType=uf&&tenantId=zhiliaoshu&contextServiceName=kids_kindergarten_insert")
				.append("&access_token=" + token);

		StringBuffer paras = new StringBuffer("kgname=")
				.append(encodeString("jack")).append("&kgmanager=")
				.append(encodeString("jack")).append("&kgstatus=1")
				.append("&kgaddress=").append(encodeString("澳大利亚"))
				.append("&kgcity=").append(encodeString("北京"))
				.append("&kgaera=").append(encodeString("海淀区"))
				.append("&kgzipcode=10083").append("&kglinkphone=01082337866")
				.append("&note=").append(encodeString("创造奇迹YESPost"));

		StringBuffer buffer = callRestPost(callInsert.toString(),
				paras.toString());
		JSONObject jobject = null;
		try {
			jobject = new JSONObject(buffer.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobject;
	}

	/**
	 * 如果调用 平台的查询类型的服务
	 * 
	 * @return
	 * @throws IOException
	 */
	private static JSONArray restSelect() throws IOException {

		String callSelect = baseCallUrl
				+ "?callType=sa&tenantId=zhiliaoshu&contextServiceName=kids_baby_findbyclassid&classuid=402881e93c17f5b1013c18e35310030a";

		StringBuffer buffer = callRest(callSelect);

		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(buffer.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}

	/**
	 * 如果调用 平台的查询类型的服务
	 * 
	 * @return
	 * @throws IOException
	 */
	private static JSONArray restSelectPost(String token) throws IOException {

		String callSelect = baseCallUrl
				+ "?callType=sa&tenantId=zhiliaoshu&contextServiceName=kids_baby_findbyclassid";

		String paras = "classuid=402881e93c17f5b1013c18e35310030a&access_token="
				+ token;
		StringBuffer buffer = callRestPost(callSelect, paras);

		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(buffer.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}

	/**
	 * 调用 restful webservice
	 * 
	 * @param strUrl
	 * @return
	 * @throws IOException
	 */
	public static StringBuffer callRestPost(String strUrl, String params)
			throws IOException {

		StringBuffer buffer = new StringBuffer();

		URL url = new URL(strUrl);
		HttpURLConnection uc = (HttpURLConnection) url.openConnection();
		uc.setReadTimeout(1000 * 30);
		uc.setRequestMethod("POST");

		uc.setDoOutput(true);
		byte[] b = params.toString().getBytes();
		uc.getOutputStream().write(b, 0, b.length);
		uc.getOutputStream().flush();
		uc.getOutputStream().close();

		InputStream is = uc.getInputStream();
		String line = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(is,
				"utf-8"));
		while ((line = in.readLine()) != null) {
			buffer.append(line).append("\n");
		}

		is.close();

		return buffer;
	}
	
	
	public static StringBuffer call(String urlparas) throws IOException {
		
		return callRest(baseCallUrl  + urlparas);
	}
	
	public static BOInstance selectOne(String urlparas)throws IOException, JSONException {
		
		StringBuffer buffer = call(urlparas);
		BOInstance bi =  null;
		if(buffer!=null && buffer.charAt(0) =='['){
			JSONArray jsonArray = null;
			try {
				jsonArray = new JSONArray(buffer.toString());
				if(jsonArray.length() > 0){
					bi = BOInstance.fromJSONObject(jsonArray.getJSONObject(0));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			 bi = BOInstance.fromJSONString(buffer.toString());
		}
		
		return bi;
	}
	
	
	public static BOInstance selectOne(String urlparas,Map mapParas)throws IOException, JSONException {
		
		StringBuffer buffer = call(urlparas,mapParas);
		
		
		System.out.println("Buffer:::" + buffer);
		
		BOInstance bi = BOInstance.fromJSONString(buffer.toString());
		
		return bi;
	}
	
	
	public static StringBuffer call(String urlparas,Map mapParas) throws IOException {
		
		StringBuffer  paras = new StringBuffer();
		if(mapParas!=null){
			for(Iterator<Entry<String,String>> it = mapParas.entrySet().iterator(); it.hasNext();){

				Entry<String,String> e = it.next();
				paras.append("&")
				.append(e.getKey())
				.append("=")
				.append(encodeString(e.getValue()));
			}
		}
		
		return callRest(baseCallUrl  + urlparas + paras);
	}

	/**
	 * 调用 restful webservice
	 * 
	 * @param strUrl
	 * @return
	 * @throws IOException
	 */
	public static StringBuffer callRest(String strUrl) throws IOException {

		StringBuffer buffer = new StringBuffer();

		SSLContext sc = null;

		if (strUrl.startsWith("https://")) {
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs,
						String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs,
						String authType) {
				}
			} };
			try {
				sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		URL url = new URL(strUrl);
		URLConnection uc = url.openConnection();
		if (sc != null) {
			((HttpsURLConnection) uc)
					.setSSLSocketFactory(sc.getSocketFactory());
		}

		uc.setReadTimeout(1000 * 30);

		InputStream is = uc.getInputStream();
		

		String line = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(is,
				"utf-8"));
		while ((line = in.readLine()) != null) {
			buffer.append(line).append("\n");
		}

		is.close();

		return buffer;

	}
	
	
	public static void main(String[] args) throws IOException{

		
		
		
		System.out.println(RestUtil.baseCallUrl);
		
		String callSelect =  "callType=sa&contextServiceName=eeplat_appshare_list_ws";


		StringBuffer buffer = RestUtil.call(callSelect);
		
		
		//System.out.println("BUFFER::::" + buffer);

		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(buffer.toString());
			
			System.out.println("jsonArray::::" + jsonArray);

			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

//	public static void main(String[] args) throws IOException, JSONException {
//
//		String token = getAccessToken();
//
//		System.out.println("Token ::" + token);
//
//		//
//		// JSONArray ja = restSelectPost(token);
//		// System.out.println("Test Select::" + ja);
//		// //
//		// // JSONObject jo = restUpdate();
//		// // System.out.println("Test Update::" + jo);
//		// //
//		//
//		JSONObject jo2 = restInsertCRMAu(token);
//		System.out.println("Test Insert::" + jo2);
//
//	}

}
