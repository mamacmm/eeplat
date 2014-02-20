package com.exedosoft.plat.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.exedosoft.plat.util.ASCII2NATIVE;

public class SendSMS {
	
	//13911506501  18301587258 13681070800  13522697157
	//private static String phones = "13701252599,13910185842,13901092228,13911661322,13810297407,13466682396,13466278577,13146256166,15801324912,13811011158,13811152023,13671263660,18710168006,13811183136,13811183135,13641174917,13810454270,13716869028,13466662380,13301265105,13901393704,13911390426,13910317369,13161518507,13911169920,13511019429,13522026471,13691198866,13811749186,13693681924,15901033692,18721725652,13146950037,15201080577,15311330397,13522570964,18701652675";
	private static String phones = "13810536950,18201173713";

	public static void main(String[] args) throws IOException {
		HttpClient client = new HttpClient();
		// 如果发送的内容是中文，请使用 java.net.URLEncoder.encode(content, "utf-8"); 进行编码,18201173713,18643095161,13910718125
		
//		尊敬的家长，您好：(.*)将于本学期开始试用“知了树”手机交互软件，“知了树”将提高家园沟通效率，丰富沟通内容，为家园共育提供了更好的支持。请您百忙中抽时间登录 http://www.zhiliaoshu.com 下载相关的客户端软件，以自己手机号进行登录，默认密码六个1，进行初步体验。欢迎您提出意见和建议，可以发送邮件：contact@zhiliaoshu.com，或拨打技术支持电话 18201173713 18501370925。谢谢
		HttpMethod method = new GetMethod(
				"http://www.tui3.com/api/send/?k=30763b50d28165eed972c95f3a838fca&p=1&t=" +
				phones		
				+ "&c="
//				+  java.net.URLEncoder.encode("尊敬的家长您好，幼儿园老师已经发送了您孩子的今日表现，您只有安装知了树才可以收到： ","utf-8")
//				+ "http://t.cn/zQay7Lz" 
//				+ java.net.URLEncoder.encode(" 。","utf-8"));
				+ java.net.URLEncoder.encode("尊敬的家长，您好：幸福摇篮双语幼儿园将于本学期开始试用“知了树”手机交互软件，“知了树”将提高家园沟通效率，丰富沟通内容，为家园共育提供了更好的支持。请您百忙中抽时间登录 http://www.zhiliaoshu.com 下载相关的客户端软件，以自己手机号进行登录，默认密码六个1，进行初步体验。欢迎您提出意见和建议，可以发送邮件：contact@zhiliaoshu.com，技术支持电话 18201173713 18501370925。谢谢！","utf-8"));
		//http://www.zhiliaoshu.com/zls.apk
		client.executeMethod(method);
		
		
		InputStream in = method.getResponseBodyAsStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in,
				"utf-8"));
		String tmp;
		while ((tmp = br.readLine()) != null) {
			System.out.println(ASCII2NATIVE.ascii2Native(tmp)); // 这里打印出来的内容是 utf8格式，如果在windows中输出，请自行处理
		}
		method.releaseConnection();
	}
	
	
}
