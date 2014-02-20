package com.exedosoft.plat.storage.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;

import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.GetObjectRequest;
import com.aliyun.openservices.oss.model.ObjectMetadata;

public class OSSUpload {

	private static final String ACCESS_ID = "IhSzDz3fBlI9SPKY";
	private static final String ACCESS_KEY = "yPlMtVZkp0LZq4DdxemTKUPv3S3bUS";
	private static final String OSS_ENDPOINT = "http://oss.aliyuncs.com/";

	/**
	 * @param args
	 */
	public static void upload(String key, FileItem aFileItem) throws Exception {

		String bucketName = "eeplatfile";

		// String downloadFilePath = "d:/temp/网站首页2.png";

		// 可以使用ClientConfiguration对象设置代理服务器、最大重试次数等参数。
		ClientConfiguration config = new ClientConfiguration();
		OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY,
				config);

		try {

			System.out.println("正在上传...");
			uploadFile(client, bucketName, key, aFileItem);

			System.out.println("上传成功");

			// ///保存到数据中的路径，这个路径可以直接在浏览器中访问
			// String lujing = OSS_ENDPOINT + "/" + bucketName + "/" + key;
			//
			//
			// System.out.println("正在下载...");
			// downloadFile(client, bucketName, key, downloadFilePath);
		} finally {
			// deleteBucket(client, bucketName);
		}
	}

	// 上传文件
	public static void uploadFile(String bucketName,
			String key,String contentType, int size, InputStream is) throws OSSException,
			ClientException, IOException {
		
		ClientConfiguration config = new ClientConfiguration();
		OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY,
				config);

		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(size);
		// 可以在metadata中标记文件类型
		if(contentType!=null){
			objectMeta.setContentType(contentType);
		}
		client.putObject(bucketName, key,is, objectMeta);

	}
	
	
	// 上传文件
	public static void uploadFile(String bucketName,
			String key,String contentType, File aFile) throws OSSException,
			ClientException, IOException {
		
		ClientConfiguration config = new ClientConfiguration();
		OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY,
				config);

		ObjectMetadata objectMeta = new ObjectMetadata();
		
		objectMeta.setContentLength(aFile.length());
		// 可以在metadata中标记文件类型
		if(contentType!=null){
			objectMeta.setContentType(contentType);
		}
		client.putObject(bucketName, key, new FileInputStream(aFile), objectMeta);

	}

	// 上传文件
	private static void uploadFile(OSSClient client, String bucketName,
			String key, FileItem fileItem) throws OSSException,
			ClientException, IOException {

		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(fileItem.getSize());
		// 可以在metadata中标记文件类型
		// objectMeta.setContentType("image/jpeg");
		client.putObject(bucketName, key, fileItem.getInputStream(), objectMeta);
	}
	
	
	
	// 下载文件
	private static void downloadFile(OSSClient client, String bucketName,
			String key, String filename) throws OSSException, ClientException {
		client.getObject(new GetObjectRequest(bucketName, key), new File(
				filename));
	}
	
	// 下载文件
	public static void downloadFile( String bucketName,
			String key, String filename) throws OSSException, ClientException {
		
		ClientConfiguration config = new ClientConfiguration();
		OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY,
				config);
		client.getObject(new GetObjectRequest(bucketName, key), new File(
				filename));
	}
}
